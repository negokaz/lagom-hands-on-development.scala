package com.example.lagomchat.message.impl

import java.util.UUID

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag, PersistentEntity}
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import org.joda.time.DateTime
import play.api.libs.json.{Format, Json}

import scala.collection.immutable.Seq

object RoomEntity {
  /**
    * 全員が使える Room の ID
    *
    * ※ このハンズオンでは簡単にするため Room の数は 1 つだけに限定する
    */
  val RoomId = "global-room"
}

/**
  * Room の Entity
  *
  * - Room には 1000 通までのメッセージを投稿できる
  * - 1001 通目以降のメッセージは全て Reject し、イベントも残さない
  */
class RoomEntity extends PersistentEntity {
  override type Command = RoomCommand
  override type Event = RoomEvent
  override type State = RoomState

  override def initialState = RoomState(roomId = RoomEntity.RoomId, countOfMessage = 0)

  override def behavior = {

    case RoomState(_, countOfMessage) if countOfMessage > 1000 =>
      Actions()
        .onCommand[PostMessage, Done] {
          case (_, ctx, _) =>
            // TODO: もうメッセージが投稿できないのでエラーの応答を返す
            ???
        }

    case RoomState(_, _) =>
      Actions()
        .onCommand[PostMessage, Done] {
          case (msg: PostMessage, ctx, state) =>
            // MessagePosted を永続化する
            val msgId = UUID.randomUUID()
            val event = MessagePosted(msgId, state.roomId, msg.message, msg.user, msg.timestamp)
            ctx.thenPersist(event)(_ => ctx.reply(Done))
        }
        .onEvent {
          case (_: MessagePosted, state) =>
            // state の countOfMessage をインクリメントする
            state.incrementsMessages
        }
  }
}

sealed trait RoomCommand

/**
  * Room (Entity) に対してメッセージを投稿するコマンド
  *
  * @param message メッセージ本文
  * @param user メッセージを投稿したユーザーの ID
  * @param timestamp メッセージを投稿した時間
  */
case class PostMessage(message: String, user: String, timestamp: DateTime) extends RoomCommand with ReplyType[Done]

object PostMessage {
  implicit val format: Format[PostMessage] = Json.format
}

sealed trait RoomEvent extends AggregateEvent[RoomEvent] {
  override def aggregateTag =  RoomEvent.RoomEventTag
}

object RoomEvent {
  val RoomEventTag = AggregateEventTag[RoomEvent]
}

/**
  * Room (Entity) に対してメッセージが投稿されたイベント
  *
  * @param id イベントの ID
  * @param roomId 投稿された Room の ID
  * @param message メッセージの本文
  * @param user メッセージを投稿したユーザーの ID
  * @param timestamp メッセージを投稿した時間
  */
case class MessagePosted(id: UUID, roomId: String, message: String, user: String, timestamp: DateTime) extends RoomEvent

object MessagePosted {
  implicit val format: Format[MessagePosted] = Json.format
}

/**
  * Room (Entity) の状態
  *
  * @param roomId Room の ID
  * @param countOfMessage Room に投稿されたメッセージの数
  */
case class RoomState(roomId: String, countOfMessage: Int) {
  def incrementsMessages: RoomState = copy(countOfMessage = countOfMessage + 1)
}

object RoomSerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = List(
    JsonSerializer[PostMessage]
  )
}
