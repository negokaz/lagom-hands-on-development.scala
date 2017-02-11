package com.example.lagomchat.message.impl

import java.io.File

import akka.cluster.Cluster
import com.lightbend.lagom.scaladsl.server.LocalServiceLocator
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import com.example.lagomchat.api._
import com.example.lagomchat.message.api.RequestMessage

class LagomchatServiceSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {

  private val server = ServiceTest.startServer(
    ServiceTest.defaultSetup
      .withCassandra(true)
  ) { ctx =>
    new MessageApplication(ctx) with LocalServiceLocator
  }

  val client = server.serviceClient.implement[LagomchatService]

  override protected def afterAll() = server.stop()

  "lagom-chat service" should {

    "say hello" in {
      client.hello("Alice").invoke().map { answer =>
        answer should ===("Hello, Alice!")
      }
    }

    "allow responding with a custom message" in {
      for {
        _ <- client.sendMessage("Bob").invoke(RequestMessage("Hi"))
        answer <- client.hello("Bob").invoke()
      } yield {
        answer should ===("Hi, Bob!")
      }
    }
  }
}
