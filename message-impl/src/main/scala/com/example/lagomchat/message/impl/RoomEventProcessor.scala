package com.example.lagomchat.message.impl

import java.util.UUID

import akka.Done
import akka.actor.ActorSystem
import com.datastax.driver.core.{BoundStatement, PreparedStatement}
import com.lightbend.lagom.scaladsl.persistence.ReadSideProcessor.ReadSideHandler
import com.lightbend.lagom.scaladsl.persistence.{AggregateEventTag, EventStreamElement, ReadSideProcessor}
import com.lightbend.lagom.scaladsl.persistence.cassandra.{CassandraReadSide, CassandraSession}

import scala.concurrent.{Future, Promise}

class RoomEventProcessor(session: CassandraSession, readSide: CassandraReadSide, system: ActorSystem) extends ReadSideProcessor[RoomEvent] {

  implicit val dispatcher = system.dispatcher

  override def buildHandler(): ReadSideHandler[RoomEvent] = {
    val builder = readSide.builder[RoomEvent]("roomoffset")
    builder.setGlobalPrepare(createTable)
    builder.setPrepare(_ => prepareWriteUser())
    // TODO: Entity で起きたイベントを Read モデルに反映する
    builder.build()
  }

  override def aggregateTags: Set[AggregateEventTag[RoomEvent]] = Set(RoomEvent.RoomEventTag)

  private def createTable(): Future[Done] = {
    session.executeCreateTable(
      """
        | CREATE TABLE IF NOT EXISTS message (
        |   roomId TEXT,
        |   id UUID,
        |   message TEXT,
        |   user TEXT,
        |   timestamp TIMESTAMP,
        |   PRIMARY KEY (roomId, timestamp, id)
        | )
      """.stripMargin)
  }

  private val writeMessage = Promise[PreparedStatement]

  private def prepareWriteUser(): Future[Done] = {
    val prepared = session.prepare(
      """
        | INSERT INTO message (roomId, id, message, user, timestamp) VALUES (?, ?, ?, ?, ?)
      """.stripMargin)
    writeMessage.completeWith(prepared)
    prepared.map(_ => Done)
  }

  private def processMessagePosted(e: EventStreamElement[MessagePosted]): Future[List[BoundStatement]] = {
    writeMessage.future.map { prepareStatement =>
      val bind = prepareStatement.bind()
      bind.setString("roomId", e.event.roomId)
      bind.setUUID("id", e.event.id)
      bind.setString("message", e.event.message)
      bind.setString("user", e.event.user)
      bind.setTimestamp("timestamp", e.event.timestamp.toDate)
      List(bind)
    }
  }
}
