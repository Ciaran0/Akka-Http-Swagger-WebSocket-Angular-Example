package me.ciaranoconnor.app

import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import me.ciaranoconnor.api.ApiService
import scala.util.{ Success, Failure }

object Boot extends App with Config with BootedCore with ApiService {
  this: ApiService with Core =>

  import system.dispatcher

  implicit val materializer = ActorMaterializer()

  val binding = Http().bindAndHandle(routes, httpInterface, httpPort)
  //binding failure
  binding.onComplete {
    case Success(binding) ⇒
      val localAddress = binding.localAddress
      log.info(s"Server is listening on ${localAddress.getHostName}:${localAddress.getPort}")
    case Failure(e) ⇒
      log.error(s"Binding failed with ${e.getMessage}")
      system.terminate()
  }

}
