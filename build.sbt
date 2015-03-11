name := "interview-cake"

version := "1.0"

scalaVersion := "2.11.3"

libraryDependencies ++= {
  Seq(
    "org.scalaz" %% "scalaz-core" % "7.1.0" withSources(),
    "org.scalatest" % "scalatest_2.11" % "2.2.0" % Test withSources(),
    "org.hamcrest" % "hamcrest-all" % "1.3" % Test withSources(),
    "junit" % "junit" % "4.8.1" % Test
  )
}

resolvers += Resolver.mavenLocal
