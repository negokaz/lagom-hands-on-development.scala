package com.example.lagomhandsondevelopmentstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.lagomhandsondevelopmentstream.api.LagomhandsondevelopmentStreamService
import com.example.lagomhandsondevelopment.api.LagomhandsondevelopmentService

import scala.concurrent.Future

/**
  * Implementation of the LagomhandsondevelopmentStreamService.
  */
class LagomhandsondevelopmentStreamServiceImpl(lagomhandsondevelopmentService: LagomhandsondevelopmentService) extends LagomhandsondevelopmentStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(lagomhandsondevelopmentService.hello(_).invoke()))
  }
}
