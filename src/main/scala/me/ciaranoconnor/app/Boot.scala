package me.ciaranoconnor.app

import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import me.ciaranoconnor.api.ApiService

object Boot extends App with Config with BootedCore with ApiService {
  this: ApiService with Core =>

  implicit val materializer = ActorMaterializer()

  Http().bindAndHandle(routes, httpInterface, httpPort)
  log.info("Server started")
}
