name := """bootsverleih"""
organization := "com.informaticon"
version := "1.0-SNAPSHOT"

scalaVersion := "2.13.16"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaJdbc
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.33"
)


libraryDependencies += guice