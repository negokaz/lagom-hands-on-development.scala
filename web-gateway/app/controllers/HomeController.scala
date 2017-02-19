package controllers

import javax.inject._

import com.example.lagomchat.api.LagomchatService
import com.github.mmizutani.playgulp.GulpAssets
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{Messages, MessagesApi}
import play.api.mvc._
import play.api.routing.{JavaScriptReverseRoute, JavaScriptReverseRouter}

import scala.concurrent.Future


object HomeController {
  case class User(name: String)
}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
class HomeController @Inject()(gulpAssets: GulpAssets, messages: MessagesApi, chatService: LagomchatService) extends Controller {
  import HomeController._

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

  def chat = gulpAssets.at("index.html")

  def receiveMessage = Action.async { implicit request =>
    Future.successful(NoContent)
  }
}
