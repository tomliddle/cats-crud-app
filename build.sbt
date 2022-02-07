ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "3.1.0"

val cats          = "3.3.5"
val fs2           = "3.2.4"
val sttp          = "3.4.1"
val tapir         = "0.20.0-M9"
val ciris         = "2.3.2"
val Http4sVersion = "0.23.9"

lazy val root = (project in file(".")).settings(
  name := "cats-crud-app",
  libraryDependencies ++= Seq(
    "org.typelevel"               %% "cats-effect"          % cats,
    "org.typelevel"               %% "cats-effect-kernel"   % cats,
    "org.typelevel"               %% "cats-effect-std"      % cats,
    "org.typelevel"               %% "munit-cats-effect-3"  % "1.0.7"  % Test,
    "co.fs2"                      %% "fs2-core"             % fs2,
    "co.fs2"                      %% "fs2-reactive-streams" % fs2,
    "co.fs2"                      %% "fs2-io"               % fs2,
    "co.fs2"                      %% "fs2-scodec"           % fs2,
    "org.typelevel"               %% "log4cats-slf4j"       % "2.2.0",
    "org.slf4j"                    % "slf4j-simple"         % "1.7.35",
    "com.softwaremill.sttp.tapir" %% "tapir-core"           % tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe"     % tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-http4s-server"  % tapir,
    "org.http4s"                  %% "http4s-blaze-server"  % Http4sVersion,
    "com.datastax.oss"             % "java-driver-core"     % "4.14.0",
    "is.cir"                      %% "ciris"                % ciris,
    "is.cir"                      %% "ciris-refined"        % ciris,
    "org.scalatest"               %% "scalatest"            % "3.2.11" % Test,
  ),
)
