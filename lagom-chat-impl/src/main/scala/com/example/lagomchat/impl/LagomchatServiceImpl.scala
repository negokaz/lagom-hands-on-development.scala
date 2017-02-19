package com.example.lagomchat.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.example.lagomchat.api.LagomchatService

/**
  * Implementation of the LagomchatService.
  */
class LagomchatServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends LagomchatService {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the lagom-chat entity for the given ID.
    val ref = persistentEntityRegistry.refFor[LagomchatEntity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id, None))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the lagom-chat entity for the given ID.
    val ref = persistentEntityRegistry.refFor[LagomchatEntity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.body))
  }
}
