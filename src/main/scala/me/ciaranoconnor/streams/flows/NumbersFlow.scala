package me.ciaranoconnor.streams.flows

import akka.actor.ActorRef
import akka.http.scaladsl.model.ws.TextMessage
import akka.stream.scaladsl.{Flow, Source}

import scala.concurrent.ExecutionContext

trait NumbersFlow {

  val numbersSource = Source.actorPublisher[MyData](NumbersPublisher.props())

  /*val r = scala.util.Random

  def genNumber = {
    Thread sleep 2000
    //r.nextInt()
    val p =Iterator.continually(r.nextInt())

  }

  val src = Source.fromIterator(() => Iterator.continually(genNumber))*/

  //val doubleFlow = Flow[MyData].log("data")

  val toTextMessage = Flow[MyData].map(i => TextMessage(i.toString))

}
