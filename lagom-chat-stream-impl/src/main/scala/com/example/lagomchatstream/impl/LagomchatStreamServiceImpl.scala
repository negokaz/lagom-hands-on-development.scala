package com.example.lagomchatstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.lagomchatstream.api.LagomchatStreamService
import com.example.lagomchat.api.LagomchatService

import scala.concurrent.Future

/**
  * Implementation of the LagomchatStreamService.
  */
class LagomchatStreamServiceImpl(lagomchatService: LagomchatService) extends LagomchatStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(lagomchatService.hello(_).invoke()))
  }
}
