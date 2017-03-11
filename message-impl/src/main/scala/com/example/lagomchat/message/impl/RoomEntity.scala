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
  val RoomId = "global-room"
}

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
            ??? // TODO: もうメッセージが投稿できないのでエラーの応答を返す
        }

    case RoomState(_, _) =>
      Actions()
        .onCommand[PostMessage, Done] {
          case (msg: PostMessage, ctx, state) =>
            ??? // TODO: MessagePosted を永続化する
        }
        .onEvent {
          case (_: MessagePosted, state) =>
            ??? // TODO: state の countOfMessage をインクリメントする
        }
  }
}

sealed trait RoomCommand

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

case class MessagePosted(id: UUID, roomId: String, message: String, user: String, timestamp: DateTime) extends RoomEvent

object MessagePosted {
  implicit val format: Format[MessagePosted] = Json.format
}

case class RoomState(roomId: String, countOfMessage: Int) {
  def incrementsMessages: RoomState = copy(countOfMessage = countOfMessage + 1)
}

object RoomSerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = List(
    JsonSerializer[PostMessage]
  )
}
