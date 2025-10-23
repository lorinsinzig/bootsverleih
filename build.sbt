name := """bootsverleih"""
organization := "com.informaticon"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

Compile / playEbeanModels := Seq("models.*")

scalaVersion := "2.13.11"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  "mysql" % "mysql-connector-java" % "8.0.33"
)

WebpackKeys.webpackConfigs := Seq(
  baseDirectory.value / "app" / "webpack" / "webpack.dev.js",
)