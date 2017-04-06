organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

// キャッシュ用
val playGulp = "com.github.mmizutani" %% "play-gulp" % "0.1.5" exclude("com.typesafe.play", "play")
val playJsonDerivedCodecs = "org.julienrf" %% "play-json-derived-codecs" % "3.3"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `lagom-hands-on-development` = (project in file("."))
  .aggregate(`lagom-hands-on-development-api`, `lagom-hands-on-development-impl`, `lagom-hands-on-development-stream-api`, `lagom-hands-on-development-stream-impl`)

lazy val `lagom-hands-on-development-api` = (project in file("lagom-hands-on-development-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-hands-on-development-impl` = (project in file("lagom-hands-on-development-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslTestKit,
      macwire,
      playJsonDerivedCodecs,
      playGulp,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`lagom-hands-on-development-api`)

lazy val `lagom-hands-on-development-stream-api` = (project in file("lagom-hands-on-development-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-hands-on-development-stream-impl` = (project in file("lagom-hands-on-development-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`lagom-hands-on-development-stream-api`, `lagom-hands-on-development-api`)

