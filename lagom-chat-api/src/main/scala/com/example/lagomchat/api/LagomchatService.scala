package com.example.lagomchat.api

import java.sql.Timestamp

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

/**
  * The lagom-chat service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the LagomchatService.
  */
trait LagomchatService extends Service {

  /**
    * Example: curl http://localhost:9000/api/hello/Alice
    */
  def hello(id: String): ServiceCall[NotUsed, String]

  /**
    * Example: curl -H "Content-Type: application/json" -X POST -d '{"message":
    * "Hi"}' http://localhost:9000/api/hello/Alice
    */
  def useGreeting(id: String): ServiceCall[Message, Done]

  override final def descriptor = {
    import Service._
    named("lagom-chat").withCalls(
      pathCall("/api/hello/:id", hello _),
      pathCall("/api/hello/:id", useGreeting _)
    ).withAutoAcl(true)
  }
}

/**
  * The greeting message class.
  */
case class Message(body: String, user: String)
object Message {
  /**
    * Format for converting greeting messages to and from JSON.
    *
    * This will be picked up by a Lagom implicit conversion from Play's JSON format to Lagom's message serializer.
    */
  implicit val format: Format[Message] = Json.format[Message]
}
