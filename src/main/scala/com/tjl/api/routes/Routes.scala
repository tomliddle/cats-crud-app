package com.tjl.api.routes

import cats.effect.IO
import com.tjl.UserService.{ApiUser, DBUser, createUser, deleteUser, getUser, updateUser}
import sttp.tapir.{Endpoint, endpoint, statusCode, stringToPath}
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import io.circe.generic.auto.*
import org.http4s.HttpRoutes
import sttp.model.StatusCode
import sttp.tapir.server.http4s.Http4sServerInterpreter
import cats.syntax.semigroupk.*

import java.util.UUID

object Routes {

  case class ErrorInfo(error: String)

  private val UserPath   = "user"
  private val UserIdPath = path[UUID]("userId")

  private val baseEndpoint =
    endpoint
      .in("api" / "v1")
      .errorOut(statusCode.and(jsonBody[ErrorInfo]))

  val getEndpoint: PublicEndpoint[UUID, (StatusCode, ErrorInfo), DBUser, Any] = baseEndpoint.get
    .in(UserPath / UserIdPath)
    .out(jsonBody[DBUser])

  val putEndpoint = baseEndpoint.put
    .in(UserPath)
    .in(jsonBody[DBUser])
    .out(jsonBody[DBUser])

  val postEndpoint = baseEndpoint.post
    .in(UserPath)
    .in(jsonBody[ApiUser])
    .out(jsonBody[DBUser])

  val deleteEndpoint: PublicEndpoint[UUID, (StatusCode, ErrorInfo), DBUser, Any] = baseEndpoint
    .in(UserPath / UserIdPath)
    .out(jsonBody[DBUser])

  val getUserRoute: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getEndpoint.serverLogic(getUser _))

  val createUserRoute: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(postEndpoint.serverLogic(createUser _))

  val updateUserRoute: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(putEndpoint.serverLogic(updateUser _))

  val deleteUserRoute: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteEndpoint.serverLogic(deleteUser _))

  val allRoutes: HttpRoutes[IO] = getUserRoute <+> createUserRoute <+> deleteUserRoute <+> updateUserRoute
}
