package controllers

import javax.inject._

import akka.NotUsed
import com.example.lagomchat.api.LagomchatService
import com.example.lagomchatstream.api.LagomchatStreamService
import com.github.mmizutani.playgulp.GulpAssets
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{Messages, MessagesApi}
import play.api.mvc._
import play.api.routing.{JavaScriptReverseRoute, JavaScriptReverseRouter}

import scala.concurrent.Future


object ChatController {
  case class User(name: String)
}

class ChatController @Inject()(gulpAssets: GulpAssets,
                               messages: MessagesApi,
                               chatService: LagomchatService,
                               stream: LagomchatStreamService) extends Controller {
  import ChatController._

  val loginForm = Form(
    mapping(
      "name" -> text
    )(User.apply)(User.unapply)
  )

  def index = Action { implicit request =>
    implicit val msg = messages.preferred(request)
    Ok(views.html.login(loginForm))
  }

  def login = Action { implicit request =>
    implicit val msg = messages.preferred(request)
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.login(loginForm))
      },
      loginForm => {
        Redirect("/chat").withSession(request.session + ("name" -> loginForm.name))
      }
    )
  }


  def chat = (UserAction andThen PermissionCheck).async { request =>
    gulpAssets.at("index.html")(request)
  }

  def receiveMessage = Action.async { implicit request =>
    Future.successful(NoContent)
  }

  def messageStream = WebSocket.acceptOrResult[NotUsed, String] { request =>
    Future.successful {
      request.session.get("username") match {
        case None => Left(Forbidden)
        case Some(_) => Right(/* TODO: ストリームの実装 */???)
      }
    }
  }

  class UserRequest[A](val username: Option[String], request: Request[A]) extends WrappedRequest[A](request)

  object UserAction extends ActionBuilder[UserRequest] with ActionTransformer[Request, UserRequest] {
    override protected def transform[A](request: Request[A]) = Future.successful {
      new UserRequest[A](request.session.get("username"), request)
    }
  }

  object PermissionCheck extends ActionFilter[UserRequest] {
    override protected def filter[A](request: UserRequest[A]) = Future.successful {
      if (request.username.isEmpty) {
        Some(Forbidden)
      } else {
        None
      }
    }
  }
}
