package com.example.lagomchat.user.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json._
import julienrf.json.derived


trait UserService extends Service {

  def createUser: ServiceCall[CreateUser, User]

  def getUser(name: String): ServiceCall[NotUsed, User]

  def getUsers: ServiceCall[NotUsed, Seq[User]]

  def userEvents(): ServiceCall[NotUsed, Source[UserEvent, NotUsed]]

  override def descriptor = {
    import Service._
    named("user").withCalls(
      pathCall("/api/user", createUser),
      pathCall("/api/user/:name", getUser _),
      pathCall("/api/user", getUsers),
      pathCall("/api/user/events/", userEvents)
    )
  }
}


case class User(name: String)

object User {
  implicit val format: Format[User] = Json.format
}


case class CreateUser(name: String)

object CreateUser {
  implicit val format: Format[CreateUser] = Json.format
}

sealed trait UserEvent


case class UserCreated(user: User) extends UserEvent

object UserCreated {
  implicit val format: Format[CreateUser] = Json.format
}

object UserEvent {
  implicit val format: Format[UserEvent] =
    derived.flat.oformat((__ \ "type").format[String])
}