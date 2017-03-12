package com.example.lagomchat.message.api

import akka.stream.scaladsl.Source
import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import org.joda.time.DateTime
import play.api.libs.json.{Format, Json}

trait MessageService extends Service {

  /**
    * POST /api/messages/:userId
    *
    * Request:
    *
    *   { body: "わーい！" }
    *
    * Response:
    *
    *   HTTP/1.1 200 OK
    *
    */
  def sendMessage(userId: String): ServiceCall[RequestMessage, Done]

  /**
    * GET /api/messagestream
    *
    * Response (WebSocket):
    *
    *   [
    *     { body: "わーい！", user: "user1", timestamp: 1488866889258 },
    *     { body: "すごーい！", user: "user2", timestamp: 1488866889259 }
    *   ]
    */
  def messageStream(): ServiceCall[NotUsed, Source[Message, NotUsed]]

  /**
    * GET /api/messages
    *
    * Response:
    *
    *   HTTP/1.1 200 OK
    *
    *   [
    *     { body: "わーい！", user: "user1", timestamp: 1488866889258 },
    *     { body: "すごーい！", user: "user2", timestamp: 1488866889259 }
    *   ]
    *
    */
  def messages(): ServiceCall[NotUsed, Seq[Message]]

  override final def descriptor = {
    import Service._
    named("message").withCalls(
      // TODO: パスとメソッドのマッピングを定義
    ).withAutoAcl(true)
  }
}

/**
  * クライアントから POST されるメッセージ
  *
  * @param body メッセージの本文
  */
case class RequestMessage(body: String)

object RequestMessage {

  implicit val format: Format[RequestMessage] = Json.format
}

/**
  * クライアントにレスポンスするメッセージ
  *
  * @param body メッセージの本文
  * @param user メッセージを投稿したユーザーのID
  * @param timestamp メッセージを投稿した時間
  */
case class Message(body: String, user: String, timestamp: DateTime)

object Message {

  implicit val format: Format[Message] = Json.format
}