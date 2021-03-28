enablePlugins(GatlingPlugin, AssemblyPlugin)
name := "gatling-test"
version := "0.2"
scalaVersion := "2.12.9"
scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.3.1"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "3.3.1"
libraryDependencies += "io.gatling" % "gatling-app" % "3.3.1"
libraryDependencies += "io.gatling" % "gatling-recorder" % "3.3.1"
libraryDependencies += "org.scalaj" % "scalaj-http_2.12" % "2.4.2"


assemblyJarName in(IntegrationTest, assembly) := s"${name.value}-${version.value}.jar"


lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings: _*)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
