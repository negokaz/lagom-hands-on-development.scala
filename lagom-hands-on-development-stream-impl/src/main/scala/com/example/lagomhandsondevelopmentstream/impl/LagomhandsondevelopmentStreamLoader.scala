package com.example.lagomhandsondevelopmentstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.lagomhandsondevelopmentstream.api.LagomhandsondevelopmentStreamService
import com.example.lagomhandsondevelopment.api.LagomhandsondevelopmentService
import com.softwaremill.macwire._

class LagomhandsondevelopmentStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new LagomhandsondevelopmentStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new LagomhandsondevelopmentStreamApplication(context) with LagomDevModeComponents

  override def describeServices = List(
    readDescriptor[LagomhandsondevelopmentStreamService]
  )
}

abstract class LagomhandsondevelopmentStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the services that this server provides
  override lazy val lagomServer = LagomServer.forServices(
    bindService[LagomhandsondevelopmentStreamService].to(wire[LagomhandsondevelopmentStreamServiceImpl])
  )

  // Bind the LagomhandsondevelopmentService client
  lazy val lagomhandsondevelopmentService = serviceClient.implement[LagomhandsondevelopmentService]
}
