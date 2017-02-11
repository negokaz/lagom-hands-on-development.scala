package com.example.lagomchatstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.lagomchatstream.api.LagomchatStreamService
import com.example.lagomchat.api.LagomchatService
import com.softwaremill.macwire._

class LagomchatStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new LagomchatStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new LagomchatStreamApplication(context) with LagomDevModeComponents
}

abstract class LagomchatStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the services that this server provides
  override lazy val lagomServer = LagomServer.forServices(
    bindService[LagomchatStreamService].to(wire[LagomchatStreamServiceImpl])
  )

  // Bind the LagomchatService client
  lazy val lagomchatService = serviceClient.implement[LagomchatService]
}
