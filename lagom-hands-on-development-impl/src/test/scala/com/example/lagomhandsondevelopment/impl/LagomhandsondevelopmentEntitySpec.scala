package com.example.lagomhandsondevelopment.impl

import akka.actor.ActorSystem
import akka.testkit.TestKit
import com.lightbend.lagom.scaladsl.testkit.PersistentEntityTestDriver
import com.lightbend.lagom.scaladsl.playjson.JsonSerializerRegistry
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

class LagomhandsondevelopmentEntitySpec extends WordSpec with Matchers with BeforeAndAfterAll {

  private val system = ActorSystem("LagomhandsondevelopmentEntitySpec",
    JsonSerializerRegistry.actorSystemSetupFor(LagomhandsondevelopmentSerializerRegistry))

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  private def withTestDriver(block: PersistentEntityTestDriver[LagomhandsondevelopmentCommand[_], LagomhandsondevelopmentEvent, LagomhandsondevelopmentState] => Unit): Unit = {
    val driver = new PersistentEntityTestDriver(system, new LagomhandsondevelopmentEntity, "lagom-hands-on-development-1")
    block(driver)
    driver.getAllIssues should have size 0
  }

  "lagom-hands-on-development entity" should {

    "say hello by default" in withTestDriver { driver =>
      val outcome = driver.run(Hello("Alice", None))
      outcome.replies should contain only "Hello, Alice!"
    }

    "allow updating the greeting message" in withTestDriver { driver =>
      val outcome1 = driver.run(UseGreetingMessage("Hi"))
      outcome1.events should contain only GreetingMessageChanged("Hi")
      val outcome2 = driver.run(Hello("Alice", None))
      outcome2.replies should contain only "Hi, Alice!"
    }

  }
}
