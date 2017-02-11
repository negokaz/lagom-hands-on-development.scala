organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `lagom-chat` = (project in file("."))
  .aggregate(`lagom-chat-api`, `lagom-chat-impl`, `lagom-chat-stream-api`, `lagom-chat-stream-impl`, `web-gateway`)

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
      lagomScaladslPersistenceCassandra,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`lagom-chat-api`)

lazy val `lagom-chat-stream-api` = (project in file("lagom-chat-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-chat-stream-impl` = (project in file("lagom-chat-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`lagom-chat-stream-api`, `lagom-chat-api`)

lazy val `web-gateway` = (project in file("web-gateway"))
  .enablePlugins(PlayScala, LagomPlayScala)
  .settings(
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(
      filters,
      macwire,
      "org.webjars" % "react" % "15.3.2",
      "org.webjars" % "react-router" % "1.0.3",
      "org.webjars" % "jquery" % "3.1.1-1",
      "org.webjars" % "foundation" % "6.2.3"
    )
  )
  .dependsOn(`lagom-chat-api`)