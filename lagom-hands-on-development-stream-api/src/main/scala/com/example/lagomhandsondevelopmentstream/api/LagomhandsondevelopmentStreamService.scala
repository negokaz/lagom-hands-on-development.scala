package com.example.lagomhandsondevelopmentstream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

/**
  * The lagom-hands-on-development stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the LagomhandsondevelopmentStream service.
  */
trait LagomhandsondevelopmentStreamService extends Service {

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor = {
    import Service._

    named("lagom-hands-on-development-stream")
      .withCalls(
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}

