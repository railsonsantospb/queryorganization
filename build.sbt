name := """QueryOrganization"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
	guice,
  	javaWs,
  	"org.jsoup" % "jsoup" % "1.13.1",
  	"org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.13"
)