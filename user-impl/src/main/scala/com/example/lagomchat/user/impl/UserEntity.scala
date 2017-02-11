package com.example.lagomchat.user.impl

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag, PersistentEntity}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import play.api.libs.json.{Format, Json}

class UserEntity extends PersistentEntity {
  override type Command = UserCommand
  override type Event = UserEvent
  override type State = Option[User]

  override def initialState = None

  override def behavior = {
    case Some(userState) =>
      Actions()
        .onReadOnlyCommand[GetUser.type, Option[User]] {
          case (_, ctx, state) => ctx.reply(state)
        }
        .onReadOnlyCommand[CreateUser, Done] {
          case (CreateUser(name), ctx, _) => ctx.invalidCommand(s"User ${name} already exists")
        }
    case None =>
      Actions()
        .onReadOnlyCommand[GetUser.type, Option[User]] {
          case (_, ctx, state) => ctx.reply(state)
        }
        .onCommand[CreateUser, Done] {
          case (CreateUser(name), ctx, _) =>
            ctx.thenPersist(UserCreated(name))(_ => ctx.reply(Done))
        }
        .onEvent {
          case (UserCreated(name), _) => Some(User(name))
        }
  }
}

case class User(name: String)

object User {
  implicit val format: Format[User] = Json.format
}

object UserEvent {
  val UserEventTag = AggregateEventTag[UserEvent]
}

sealed trait UserEvent extends AggregateEvent[UserEvent] {
  override def aggregateTag = UserEvent.UserEventTag
}

case class UserCreated(name: String) extends UserEvent

object UserCreated {
  implicit val format: Format[UserCreated] = Json.format
}

sealed trait UserCommand

case class CreateUser(name: String) extends UserCommand with ReplyType[Done]

object CreateUser {
  implicit val format: Format[CreateUser] = Json.format
}

case object GetUser extends UserCommand with ReplyType[Option[User]]

object UserSerializerRegistry extends JsonSerializerRegistry {
  override def serializers = List(
    JsonSerializer[User],
    JsonSerializer[CreateUser]
  )
}