package com.example.lagomchat.user.impl

import akka.Done
import akka.actor.ActorSystem
import com.datastax.driver.core.{BoundStatement, PreparedStatement}
import com.lightbend.lagom.scaladsl.persistence.ReadSideProcessor.ReadSideHandler
import com.lightbend.lagom.scaladsl.persistence.cassandra.{CassandraReadSide, CassandraSession}
import com.lightbend.lagom.scaladsl.persistence.{AggregateEventTag, EventStreamElement, ReadSideProcessor}

import scala.concurrent.{Future, Promise}

class UserEventProcessor(session: CassandraSession, readSide: CassandraReadSide, system: ActorSystem) extends ReadSideProcessor[UserEvent] {

  implicit val dispatcher = system.dispatcher

  override def buildHandler(): ReadSideHandler[UserEvent] = {
    val builder = readSide.builder[UserEvent]("usersummaryoffset")
    builder.setGlobalPrepare(createTable)
    builder.setPrepare(_ => prepareWriteUser())
    // TODO: Entity で起きたイベントを Read モデルに反映する
    builder.build()
  }

  override def aggregateTags: Set[AggregateEventTag[UserEvent]] = Set(UserEvent.UserEventTag)

  private def createTable(): Future[Done] = {
    session.executeCreateTable(
      """
        |CREATE TABLE IF NOT EXISTS user (
        |  name TEXT, PRIMARY KEY (name)
        |)
      """.stripMargin)
  }

  private val writeUser = Promise[PreparedStatement]

  private def prepareWriteUser(): Future[Done] = {
    val prepared = session.prepare(
      """
        |INSERT INTO user (name) VALUES (?)
      """.stripMargin)
    writeUser.completeWith(prepared)
    prepared.map(_ => Done)
  }

  private def processUserAdded(event: EventStreamElement[UserCreated]): Future[List[BoundStatement]] = {
    writeUser.future.map { prepareStatement =>
      val bind = prepareStatement.bind()
      bind.setString("name", event.event.name)
      List(bind)
    }
  }
}
