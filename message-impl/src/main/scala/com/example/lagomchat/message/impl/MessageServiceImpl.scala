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

  // TODO: メッセージを配信するための Topic を作成

  // TODO: Entity の参照を取得

  override def sendMessage(userId: String) = ServiceCall { requestMessage =>
    // TODO: メッセージを PubSub に publish する
    // TODO: メッセージを Entity に送る
    ???
  }

  override def messageStream() = ServiceCall { _ =>
    ??? // TODO: PubSub で subscribe したメッセージを流す
  }

  override def messages(): ServiceCall[NotUsed, Seq[Message]] = ServiceCall { _ =>
    ??? // TODO: メッセージの一覧を返す
  }
}
