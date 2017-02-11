package com.example.lagomchat.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.lagomchat.api.LagomchatService
import com.softwaremill.macwire._

class LagomchatLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new LagomchatApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new LagomchatApplication(context) with LagomDevModeComponents
}

abstract class LagomchatApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with AhcWSComponents {

  // Bind the services that this server provides
  override lazy val lagomServer = LagomServer.forServices(
    bindService[LagomchatService].to(wire[LagomchatServiceImpl])
  )

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry = LagomchatSerializerRegistry

  // Register the lagom-chat persistent entity
  persistentEntityRegistry.register(wire[LagomchatEntity])
}
