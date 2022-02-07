package com.tjl

import cats.data.State
import cats.effect.IO
import ciris.{ConfigValue, Effect, Secret, env, prop}
import org.http4s.HttpRoutes
import sttp.model.StatusCode
import sttp.tapir.server.http4s.Http4sServerInterpreter
import ciris.refined.*
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto.*
import eu.timepit.refined.collection.MinSize
import cats.implicits.*

import java.util.UUID
import com.datastax.oss.driver.api.core.session.Session
import com.tjl.api.routes.Routes.ErrorInfo

object UserService {

  case class ApiUser(firstName: String, lastName: String, age: String)
  case class DBUser(userId: UUID, firstName: String, lastName: String, age: String)

  def getUser(userId: UUID): IO[Either[(StatusCode, ErrorInfo), DBUser]] = {
    val user = DBUser(userId, "name", "lastName", "age")
    IO.pure(Right[(StatusCode, ErrorInfo), DBUser](user))
  }

  def createUser(user: ApiUser): IO[Either[(StatusCode, ErrorInfo), DBUser]] = {
    // TODO call db ops
    val user = DBUser(UUID.randomUUID(), "name", "lastName", "age")
    IO.pure(Right[(StatusCode, ErrorInfo), DBUser](user))
  }

  def deleteUser(userId: UUID): IO[Either[(StatusCode, ErrorInfo), DBUser]] = {
    // TODO call db ops
    val user = DBUser(userId, "name", "lastName", "age")
    IO.pure(Right[(StatusCode, ErrorInfo), DBUser](user))
  }

  def updateUser(user: DBUser): IO[Either[(StatusCode, ErrorInfo), DBUser]] =
    // TODO call db ops
    IO.pure(Right[(StatusCode, ErrorInfo), DBUser](user))

}
