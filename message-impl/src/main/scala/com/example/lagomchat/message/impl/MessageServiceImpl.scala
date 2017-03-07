package com.example.lagomchat.message.impl

import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.lagomchat.message.api.{Message, MessageService}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraSession
import com.lightbend.lagom.scaladsl.pubsub.PubSubRegistry
import com.lightbend.lagom.scaladsl.pubsub.TopicId
import org.joda.time.DateTime

import scala.concurrent.Future

class MessageServiceImpl(pubSub: PubSubRegistry,
                         registry: PersistentEntityRegistry,
                         cassandra: CassandraSession,
                         system: ActorSystem)
                        (implicit materializer: Materializer) extends MessageService {

  implicit val dispatcher = system.dispatcher

  val topic = pubSub.refFor(TopicId[Message])

  val entity = registry.refFor[RoomEntity]("global")

  override def sendMessage(id: String) = ServiceCall { requestMessage =>
    val message = Message(requestMessage.body, id, DateTime.now())
    entity.ask(PostMessage(message.body, message.user, message.timestamp)).map { _ =>
      topic.publish(message)
      Done
    }
  }

  override def messageStream() = ServiceCall { _ =>
    Future.successful(topic.subscriber)
  }

  override def messages(): ServiceCall[NotUsed, Seq[Message]] = ServiceCall { _ =>
    cassandra
      .select(
        """
          | SELECT
          |   message,
          |   user,
          |   timestamp
          | FROM message
          | WHERE roomId = ?
          | ORDER BY timestamp ASC
        """.stripMargin, "room")
      .map { row =>
        Message(
          body = row.getString("message"),
          user = row.getString("user"),
          timestamp = new DateTime(row.getTimestamp("timestamp"))
        )
      }
      .runFold(Seq.empty[Message])((acc, e) => acc :+ e)
  }
}
