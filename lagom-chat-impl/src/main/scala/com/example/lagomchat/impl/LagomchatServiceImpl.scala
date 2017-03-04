package com.example.lagomchat.impl

import akka.{Done}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.lagomchat.api.{LagomchatService, Message}
import com.lightbend.lagom.scaladsl.pubsub.PubSubRegistry
import com.lightbend.lagom.scaladsl.pubsub.TopicId
import org.joda.time.DateTime

import scala.concurrent.Future

class LagomchatServiceImpl(pubSub: PubSubRegistry) extends LagomchatService {

  val topic = pubSub.refFor(TopicId[Message])

  override def sendMessage(id: String) = ServiceCall { requestMessage =>
    val message = Message(requestMessage.body, id, DateTime.now())
    topic.publish(message)
    Future.successful(Done)
  }

  override def messageStream() = ServiceCall { _ =>
    Future.successful(topic.subscriber)
  }
}
