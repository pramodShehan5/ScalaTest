name := "SlickTest"

version := "0.1"

scalaVersion := "2.11.6"

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
    Seq(
      "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
      "com.typesafe.slick" %% "slick" % "2.1.0",
      "c3p0" % "c3p0" % "0.9.1.2",
      "ch.qos.logback" % "logback-classic" % "1.0.9",
      "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
      "org.slf4j" % "slf4j-nop" % "1.6.4",
      "io.spray"            %%  "spray-can"     % sprayV,
      "io.spray"            %%  "spray-routing" % sprayV,
      "io.spray"            %%  "spray-json"    % "1.3.1", //has not been updated to 1.3.2 yet
      "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
      "com.typesafe.akka" %% "akka-http" % "10.0.8"
    )
  }