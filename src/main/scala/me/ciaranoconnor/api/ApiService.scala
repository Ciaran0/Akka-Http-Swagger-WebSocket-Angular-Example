package me.ciaranoconnor.api

import akka.http.scaladsl.server.RouteConcatenation
import me.ciaranoconnor.app.BootedCore

trait ApiService extends RouteConcatenation with UserHttpService with ResourceService with BootedCore {

  private implicit val _ = system.dispatcher

  def routes = userRoutes ~
    resourceRoutes ~
    new SwaggerDocService(system).routes
}
