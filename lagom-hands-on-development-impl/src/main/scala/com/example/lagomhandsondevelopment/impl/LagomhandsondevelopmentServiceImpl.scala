package com.example.lagomhandsondevelopment.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.example.lagomhandsondevelopment.api.LagomhandsondevelopmentService

/**
  * Implementation of the LagomhandsondevelopmentService.
  */
class LagomhandsondevelopmentServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends LagomhandsondevelopmentService {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the lagom-hands-on-development entity for the given ID.
    val ref = persistentEntityRegistry.refFor[LagomhandsondevelopmentEntity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id, None))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the lagom-hands-on-development entity for the given ID.
    val ref = persistentEntityRegistry.refFor[LagomhandsondevelopmentEntity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.message))
  }
}
