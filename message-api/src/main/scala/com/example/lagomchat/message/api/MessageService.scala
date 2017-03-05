package com.example.lagomchat.message.api

import akka.stream.scaladsl.Source
import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import org.joda.time.DateTime
import play.api.libs.json.{Format, Json}

trait MessageService extends Service {

  def sendMessage(id: String): ServiceCall[RequestMessage, Done]

  def messageStream(): ServiceCall[NotUsed, Source[Message, NotUsed]]

  override final def descriptor = {
    import Service._
    named("message").withCalls(
      pathCall("/api/messages/:id", sendMessage _),
      pathCall("/api/messages", messageStream _)
    ).withAutoAcl(true)
  }
}

case class RequestMessage(body: String)

object RequestMessage {

  implicit val format: Format[RequestMessage] = Json.format[RequestMessage]
}

case class Message(body: String, user: String, timestamp: DateTime)

object Message {

  implicit val format: Format[Message] = Json.format[Message]
}