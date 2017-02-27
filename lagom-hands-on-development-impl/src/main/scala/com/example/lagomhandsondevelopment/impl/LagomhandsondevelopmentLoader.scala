package com.example.lagomhandsondevelopment.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.lagomhandsondevelopment.api.LagomhandsondevelopmentService
import com.softwaremill.macwire._

class LagomhandsondevelopmentLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new LagomhandsondevelopmentApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new LagomhandsondevelopmentApplication(context) with LagomDevModeComponents

  override def describeServices = List(
    readDescriptor[LagomhandsondevelopmentService]
  )
}

abstract class LagomhandsondevelopmentApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with AhcWSComponents {

  // Bind the services that this server provides
  override lazy val lagomServer = LagomServer.forServices(
    bindService[LagomhandsondevelopmentService].to(wire[LagomhandsondevelopmentServiceImpl])
  )

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry = LagomhandsondevelopmentSerializerRegistry

  // Register the lagom-hands-on-development persistent entity
  persistentEntityRegistry.register(wire[LagomhandsondevelopmentEntity])
}
