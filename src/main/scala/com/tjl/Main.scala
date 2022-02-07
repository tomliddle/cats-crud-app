package com.tjl

import cats.effect.{ExitCode, IO, IOApp}
import org.typelevel.log4cats.slf4j.Slf4jLogger
import cats.effect.*
import com.tjl.api.routes.Routes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.HttpRoutes
import sttp.capabilities.WebSockets
import sttp.capabilities.fs2.Fs2Streams
import sttp.tapir._
import sttp.tapir.server.http4s.Http4sServerInterpreter
import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Router
import org.http4s.server.websocket.WebSocketBuilder2
import fs2._
import scala.concurrent.ExecutionContext
import sttp.tapir.server.http4s.Http4sServerInterpreter
import cats.effect.unsafe.implicits.global

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    for {
      logger <- Slf4jLogger.create[IO]
      _      <- logger.warn("Logging start")
      server <- BlazeServerBuilder[IO]
        .bindHttp(8080, "localhost")
        .withHttpApp(Router.apply[IO]("/" -> Routes.allRoutes).orNotFound)
        .resource
        .use(_ => IO.never)
    } yield ExitCode.Success
  }

}
