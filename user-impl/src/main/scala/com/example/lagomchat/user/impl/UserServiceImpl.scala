package com.example.lagomchat.user.impl

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.Materializer
import com.example.lagomchat.user.api
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.transport.NotFound
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraSession

class UserServiceImpl(registry: PersistentEntityRegistry,
                      cassandraSession: CassandraSession,
                      system: ActorSystem)
                     (implicit materializer: Materializer) extends api.UserService {

  implicit val dispatcher = system.dispatcher

  private def refFor(name: String) = registry.refFor[UserEntity](name)

  override def createUser: ServiceCall[api.CreateUser, api.User] = ServiceCall { createUser =>

    val name = createUser.name

    refFor(name).ask(CreateUser(name)).map { _ =>
      api.User(name)
    }
  }

  override def getUser(name: String): ServiceCall[NotUsed, api.User] = ServiceCall { _ =>
    refFor(name).ask(GetUser).map {
      case Some(user) =>
        api.User(user.name)
      case None =>
        throw NotFound(s"User with name $name")
    }
  }

  override def getUsers: ServiceCall[NotUsed, Seq[api.User]] = ServiceCall { _ =>
    cassandraSession
      .select("SELECT name FROM user")
      .map(row => api.User(row.getString("name")))
      .runFold(Seq[api.User]())((acc, e) => acc :+ e)
  }
}
