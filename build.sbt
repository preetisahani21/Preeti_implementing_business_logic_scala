name := "Preeti_implementing_business_logic_scala"

version := "0.1"

scalaVersion := "2.13.4"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.7.0-M7"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.12.1"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
libraryDependencies +=  "ch.rasc" % "bsoncodec" % "1.0.1"
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.7.0"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.26"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.11"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.26"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11"
