/*
import Actor.ApiActor
import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._

object MainApp extends App {
  implicit val system = ActorSystem("SlickTest")
  val apiActor = system.actorOf(Props[ApiActor], "actor")
  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(apiActor, interface = "localhost", port = 5453)
}



*/
