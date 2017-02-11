organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

val playJsonDerivedCodecs = "org.julienrf" %% "play-json-derived-codecs" % "3.3"
val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `lagom-chat` = (project in file("."))
  .aggregate(`message-api`, `message-impl`, `user-api`, `user-impl`, `web-gateway`)

lazy val `message-api` = (project in file("message-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `message-impl` = (project in file("message-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslPubSub,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`message-api`)

lazy val `user-api` = (project in file("user-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      playJsonDerivedCodecs
    )
  )

lazy val `user-impl` = (project in file("user-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslPubSub,
      macwire
    )
  )
  .dependsOn(`user-api`)

val playGulp = "com.github.mmizutani" %% "play-gulp" % "0.1.5" exclude("com.typesafe.play", "play")

lazy val `web-gateway` = (project in file("web-gateway"))
// ハンズオンで npm, bower, gulp をインストールしなくても良いように
// PlayGulpPlugin を無効化
//.enablePlugins(PlayScala, LagomPlayScala, PlayGulpPlugin)
  .enablePlugins(PlayScala, LagomPlayScala)
  .settings(
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(
      filters,
      macwire,
      playGulp
    )
  )
  .dependsOn(`message-api`, `user-api`)