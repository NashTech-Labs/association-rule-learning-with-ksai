package ksai.association.rule.learning.demo

import akka.actor.ActorSystem
import akka.util.Timeout
import ksai.core.association.ARM
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.io.Source

object AssociationRuleLearning extends App {

  implicit val actorSystem: ActorSystem = ActorSystem("ARM-Test")
  implicit val timeout: Timeout = Timeout(20 seconds)

  val data: Array[Array[Int]] =
    Source.fromFile(getClass.getResource("/kosarak.dat").getPath)
      .getLines()
      .map(_.split(" ").map(_.toInt))
      .toArray

  val arm = ARM(data, 0.003)

  val eventualResults = arm.learn(0.5)

  eventualResults.map(array => array.foreach(rule => println(rule)))
}
