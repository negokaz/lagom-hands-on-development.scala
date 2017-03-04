package com.example.lagomchat.user.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}


trait UserService extends Service {

  def createUser: ServiceCall[CreateUser, User]

  def getUser(name: String): ServiceCall[NotUsed, User]

  def getUsers: ServiceCall[NotUsed, Seq[User]]

  override def descriptor = {
    import Service._
    named("user").withCalls(
      pathCall("/api/user", createUser),
      pathCall("/api/user/:name", getUser _),
      pathCall("/api/user", getUsers)
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