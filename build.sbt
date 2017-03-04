organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `lagom-chat` = (project in file("."))
  .aggregate(`lagom-chat-api`, `lagom-chat-impl`, `user-api`, `user-impl`, `web-gateway`)

lazy val `lagom-chat-api` = (project in file("lagom-chat-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-chat-impl` = (project in file("lagom-chat-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPubSub,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`lagom-chat-api`)

lazy val `user-api` = (project in file("user-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `user-impl` = (project in file("user-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      macwire
    )
  )
  .dependsOn(`user-api`)

lazy val `web-gateway` = (project in file("web-gateway"))
  .enablePlugins(PlayScala, LagomPlayScala, PlayGulpPlugin)
  .settings(
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(
      filters,
      macwire
    )
  )
  .dependsOn(`lagom-chat-api`, `user-api`)