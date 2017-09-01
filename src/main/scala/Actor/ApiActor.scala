package Actor

import akka.actor.{Actor, ActorLogging}
import controller.InvoiceController.getTotalOfInvoice
import controller.ItemsController.aa
import db.model.{Invoice, Item}
import spray.http.MediaTypes
import spray.routing.{HttpService, Route}

class ApiActor extends Actor with HttpService with ActorLogging {
  def actorRefFactory = context

  def receive = runRoute(apiRoute)

  val apiRoute: Route =
    path("item" / IntNumber) { id =>
      get {
        complete {
          "get" + id
        }
      } ~
        put {
          complete {
            "put" + id
          }
        } ~
        delete {
          complete {
            "delete" + id
          }
        }

    }


  path("item") {
    get {
      complete {
        //   aa()
        var invoice = new Invoice(1, List(new Item("item1", 10, 10, 2), new Item("item2", 10, 10, 5)))
        getTotalOfInvoice(invoice).toString
      }
    } ~ post {
      complete {
        "post"
      }
    } ~
      put {
        complete {
          "Put"
        }
      } ~
      delete {
        complete {
          "delete"
        }
      }
  } ~ path("") {
    respondWithMediaType(MediaTypes.`text/html`) {
      complete {

        var invoice = new Invoice(1, List(new Item("item1", 10, 10, 2), new Item("item2", 10, 10, 5)))
        getTotalOfInvoice(invoice).toString
      }
    }
  }
}
