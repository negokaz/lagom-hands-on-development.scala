package com.example.lagomchat.user.impl

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.Materializer
import com.example.lagomchat.user.api
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.transport.NotFound
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraSession
import com.lightbend.lagom.scaladsl.pubsub.PubSubRegistry
import com.lightbend.lagom.scaladsl.pubsub.TopicId

import scala.concurrent.Future

class UserServiceImpl(registry: PersistentEntityRegistry,
                      cassandraSession: CassandraSession,
                      system: ActorSystem,
                      pubSub: PubSubRegistry)
                     (implicit materializer: Materializer) extends api.UserService {

  implicit val dispatcher = system.dispatcher

  private def refFor(name: String) = registry.refFor[UserEntity](name)

  val topic = pubSub.refFor(TopicId[api.UserEvent])

  override def createUser: ServiceCall[api.CreateUser, api.User] = ServiceCall { createUser =>

    val name = createUser.name

    refFor(name).ask(CreateUser(name)).map { _ =>
      val user = api.User(name)
      topic.publish(api.UserCreated(user))
      user
    }.map(beSlowIfKilled)
  }

  override def getUser(name: String): ServiceCall[NotUsed, api.User] = ServiceCall { _ =>
    refFor(name).ask(GetUser).map {
      case Some(user) =>
        api.User(user.name)
      case None =>
        throw NotFound(s"User with name $name")
    }.map(beSlowIfKilled)
  }

  override def getUsers: ServiceCall[NotUsed, Seq[api.User]] = ServiceCall { _ =>
    cassandraSession
      .select("SELECT name FROM user")
      .map(row => api.User(row.getString("name")))
      .runFold(Seq[api.User]())((acc, e) => acc :+ e)
      .map(beSlowIfKilled)
  }

  override def userEvents = ServiceCall { _ =>
    Future.successful(topic.subscriber).map(beSlowIfKilled)
  }

  def beSlowIfKilled[T](v: T): T = {
    val kill = system.settings.config.getBoolean("user-service.kill")
    if (kill) { Thread.sleep(10000) }
    v
  }
}
