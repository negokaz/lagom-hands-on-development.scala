package com.example.lagomchat.message.impl

import com.example.lagomchat.message.api.MessageService
import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.pubsub.PubSubComponents
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.persistence.ReadSidePersistenceComponents
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.softwaremill.macwire._

class MessageApplicationLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new MessageApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new MessageApplication(context) with LagomDevModeComponents
}

abstract class MessageApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with PubSubComponents
    with AhcWSComponents
    with CassandraPersistenceComponents
    with ReadSidePersistenceComponents {

  // Bind the services that this server provides
  override lazy val lagomServer = LagomServer.forServices(
    bindService[MessageService].to(wire[MessageServiceImpl])
  )

  override lazy val jsonSerializerRegistry = RoomSerializerRegistry

  persistentEntityRegistry.register(wire[RoomEntity])
  readSide.register[RoomEvent](wire[RoomEventProcessor])
}
