
import com.example.lagomchat.message.api.MessageService
import com.example.lagomchat.user.api.UserService
import com.github.mmizutani.playgulp.GulpAssets
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.api.{ServiceAcl, ServiceInfo}
import com.lightbend.lagom.scaladsl.client.LagomServiceClientComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.{ApplicationLoader, BuiltInComponentsFromContext, Mode}
import play.api.ApplicationLoader.Context
import play.api.i18n.I18nComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.softwaremill.macwire._
import controllers.{Assets, ChatController}
import router.Routes

import scala.collection.immutable
import scala.concurrent.ExecutionContext

abstract class WebGateway(context: Context) extends BuiltInComponentsFromContext(context)
  with I18nComponents
  with AhcWSComponents
  with LagomServiceClientComponents {

  override lazy val serviceInfo: ServiceInfo = ServiceInfo(
    "web-gateway",
    Map(
      "web-gateway" -> immutable.Seq(ServiceAcl.forPathRegex("(?!/api/).*"))
    )
  )

  override implicit lazy val executionContext: ExecutionContext = actorSystem.dispatcher

  lazy val messageService = serviceClient.implement[MessageService]
  lazy val userService = serviceClient.implement[UserService]

  lazy val controller = wire[ChatController]

  val prefix = "/"
  override lazy val router = wire[Routes]
  lazy val gulpRouter = wire[gulp.Routes]
  lazy val gulpAssets = wire[GulpAssets]
  lazy val assets = wire[Assets]
}

class WebGatewayLoader extends ApplicationLoader {

  override def load(context: Context) = context.environment.mode match {
    case Mode.Dev =>
      (new WebGateway(context) with LagomDevModeComponents).application
    case _ =>
      new WebGateway(context) {
        override def serviceLocator = NoServiceLocator
      }.application
  }
}