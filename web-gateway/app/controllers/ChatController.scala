package controllers

import javax.inject._

import akka.stream.scaladsl.{Flow, Sink}
import com.example.lagomchat.message.api.{MessageService, RequestMessage}
import com.example.lagomchat.user.api.{CreateUser, UserService}
import com.github.mmizutani.playgulp.GulpAssets
import play.api._
import play.api.libs.json._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.MessagesApi
import play.api.mvc.Security.AuthenticatedBuilder
import play.api.mvc._
import play.api.routing.JavaScriptReverseRouter

import scala.concurrent.{ExecutionContext, Future}


object ChatController {
  case class User(name: String)
}

class ChatController @Inject()(gulpAssets: GulpAssets,
                               messages: MessagesApi,
                               messageService: MessageService,
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

  def aboutMe = Authenticated.async { request =>
    import com.example.lagomchat.user.api.User._
    userService.getUser(request.user)
        .invoke()
        .map(user => Ok(Json.toJson(user)))
  }

  def otherUsers = Authenticated.async { request =>
    import com.example.lagomchat.user.api.User._
    userService.getUsers
      .invoke()
      // 自分自身は除外
      .map(users => users.filter(user => user.name != request.user))
      .map(users => Ok(Json.toJson(users)))
  }

  def users = Action.async { request =>
    import com.example.lagomchat.user.api.User._
    userService.getUsers
      .invoke()
      .map(users => Ok(Json.toJson(users)))
  }

  def userEvents = WebSocket.acceptOrResult[JsValue, JsValue] { request =>
    request.session.get("username") match {
      case None =>
        Future.successful(Left(Forbidden))
      case Some(_) =>
        userService.userEvents().invoke().map { source =>
          val eventSource = source.map(m => Json.toJson(m))
          Right(Flow.fromSinkAndSource(Sink.ignore, eventSource))
        }
    }
  }

  def chat = Authenticated.async { request =>
    gulpAssets.at("index.html")(request)
  }

  def receiveMessage = Authenticated.async { implicit request =>
    request.body.asJson.flatMap(_.validate[RequestMessage].asOpt).map { msg =>
      messageService
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
        messageService.messageStream().invoke().map { source =>
          val messageSource = source.map(m => Json.toJson(m))
          Right(Flow.fromSinkAndSource(Sink.ignore, messageSource))
        }
    }
  }

  def chatMessages = Authenticated.async { _ =>
    import com.example.lagomchat.message.api.Message._

    messageService.messages()
      .invoke()
      .map(messages => Ok(Json.toJson(messages)))
  }

  def playRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("playRoutes")(
        routes.javascript.ChatController.messageStream,
        routes.javascript.ChatController.receiveMessage,
        routes.javascript.ChatController.chatMessages,
        routes.javascript.ChatController.otherUsers,
        routes.javascript.ChatController.aboutMe,
        routes.javascript.ChatController.userEvents
      )
    ).as("text/javascript")
  }
}
