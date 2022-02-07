package com.tjl

import ciris.{ConfigValue, Effect, Secret, env, prop}
import com.tjl.Config.DatabaseConfig
import eu.timepit.refined.types.string.NonEmptyString
import ciris.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.collection.MinSize
import cats.implicits._
import java.util.UUID
import com.datastax.oss.driver.api.core.session.Session
import eu.timepit.refined.types.string.NonEmptyString

object Config {

  final case class DatabaseConfig(
      keyspace: NonEmptyString,
      hosts: NonEmptyString,
      port: Int,
      preparedStatementCacheSize: Int,
      username: NonEmptyString,
      password: Secret[NonEmptyString],
  )
}

trait CassandraConf {

  /*
  val config: ConfigValue[Effect, DatabaseConfig] =
    (
      prop("cassandra.keyspace").as[NonEmptyString],
      prop("cassandra.hosts").as[NonEmptyString],
      prop("cassandra.port").as[Int],
      prop("cassandra.preparedStatementCacheSize").as[Int],
      prop("cassandra.username").as[NonEmptyString],
      env("PASSWORD").as[NonEmptyString].secret,
    ).parMapN(DatabaseConfig.apply)
   */

}
