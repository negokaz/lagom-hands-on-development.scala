package controllers

import javax.inject._

import akka.NotUsed
import akka.stream.scaladsl.{Flow, Sink}
import com.example.lagomchat.api.{LagomchatService, Message, RequestMessage}
import com.example.lagomchat.user.api.{CreateUser, UserService}
import com.github.mmizutani.playgulp.GulpAssets
import play.api._
import play.api.libs.json._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{Messages, MessagesApi}
import play.api.mvc.Security.AuthenticatedBuilder
import play.api.mvc._
import play.api.routing.{JavaScriptReverseRoute, JavaScriptReverseRouter}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


object ChatController {
  case class User(name: String)
}

class ChatController @Inject()(gulpAssets: GulpAssets,
                               messages: MessagesApi,
                               chatService: LagomchatService,
                               userService: UserService,
                               implicit val executionContext: ExecutionContext) extends Controller {
  import ChatController._

  val usernameKey = "username"

  object Authenticated extends AuthenticatedBuilder(req => req.session.get(usernameKey))

  val loginForm = Form(
    mapping(
      "name" -> text
    )(User.apply)(User.unapply)
  )

  def index = Action { implicit request =>
    implicit val msg = messages.preferred(request)
    Ok(views.html.login(loginForm))
  }

  def login = Action.async { implicit request =>
    implicit val msg = messages.preferred(request)
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(BadRequest(views.html.login(loginForm)))
      },
      user => {
        userService.createUser
          .invoke(CreateUser(user.name))
          .map { user =>
            Redirect("/chat").withSession(request.session + (usernameKey -> user.name))
          }
          .recover {
            case _ =>
              BadRequest(views.html.login(loginForm))
          }
      }
    )
  }

  def users = Authenticated.async { _ =>
    import com.example.lagomchat.user.api.User._
    userService.getUsers
      .invoke()
      .map(users => Ok(Json.toJson(users)))
  }

  def chat = Authenticated.async { request =>
    gulpAssets.at("index.html")(request)
  }

  def receiveMessage = Authenticated.async { implicit request =>
    request.body.asJson.flatMap(_.validate[RequestMessage].asOpt).map { msg =>
      chatService
        .sendMessage(request.user)
        .invoke(msg)
        .map(_ => NoContent)
    }.getOrElse(Future.successful(BadRequest))
  }

  def messageStream = WebSocket.acceptOrResult[JsValue, JsValue] { request =>
    request.session.get("username") match {
      case None =>
        Future.successful(Left(Forbidden))
      case Some(_) =>
        chatService.messageStream().invoke().map { source =>
          val messageSource = source.map(m => Json.toJson(m))
          Right(Flow.fromSinkAndSource(Sink.ignore, messageSource))
        }
    }
  }

  def playRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("playRoutes")(
        routes.javascript.ChatController.messageStream,
        routes.javascript.ChatController.receiveMessage
      )
    ).as("text/javascript")
  }
}
