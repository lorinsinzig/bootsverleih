// Defines scaffolding (found under .g8 folder)
// http://www.foundweekends.org/giter8/scaffolding.html
// sbt "g8Scaffold form"
//addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.13.1")
//addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.19")
//addSbtPlugin("com.typesafe.play" % "sbt-play-ebean" % "6.2.0")




addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.19")
addSbtPlugin("com.typesafe.play" % "sbt-play-ebean" % "6.2.0")
addSbtPlugin("au.com.onegeek" %% "sbt-dotenv" % "2.1.146")


ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always