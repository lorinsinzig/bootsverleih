// Defines scaffolding (found under .g8 folder)
// http://www.foundweekends.org/giter8/scaffolding.html
// sbt "g8Scaffold form"
//addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.13.1")
//addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.19")
//addSbtPlugin("com.typesafe.play" % "sbt-play-ebean" % "6.2.0"


// project/plugins.sbt
resolvers += "GitHub Package Registry (informaticon)" at "https://maven.pkg.github.com/informaticon/_"
credentials += {
  sys.env.get("MAVEN_PASSWORD").map { token =>
    Credentials("GitHub Package Registry", "maven.pkg.github.com", sys.env.getOrElse("MAVEN_USERNAME", "_"), token)
  }.getOrElse {
    Credentials(Path.userHome / ".sbt" / "github.credentials")
  }
}

resolvers += "Legacy Sbt Plugins" at "https://legacy-sbt-plugins.informaticon.com"
credentials += {
  sys.env.get("LEGACY_SBT_PLUGINS_MAVEN_PASSWORD").map { pw =>
    Credentials("legacy-sbt-plugins", "legacy-sbt-plugins.informaticon.com", "sbt", pw)
  }.getOrElse(
    Credentials(Path.userHome / ".sbt" / "legacy-sbt-plugins.credentials")
  )
}

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.19")
addSbtPlugin("com.typesafe.play" % "sbt-play-ebean" % "6.2.0")
addSbtPlugin("au.com.onegeek" %% "sbt-dotenv" % "2.1.146")

addSbtPlugin("com.informaticon" % "lib.sbt.base.npm-webpack-plugin" % "0.3.1")

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always