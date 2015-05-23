


import org.stormenroute.mecha._
import sbt._
import sbt.Keys._



object ExamplesApplicationBuild extends MechaRepoBuild {

  import Input._
  val configQuery = {
    val target = const("file(\"/target/\")").map(("target", _))
    val version =
      stringQuery("Enter version: ")
        .map(v => "\"" + v + "\"")
        .map(("version", _))
    traverseFull(List(target, version))
  }

  lazy val examplesApplicationSettings = Defaults.defaultSettings ++
    MechaRepoPlugin.defaultSettings ++ Seq(
    name := "examples-application",
    scalaVersion := "2.11.4",
    version := "0.1",
    organization := "com.storm-enroute",
    libraryDependencies ++= superRepoDependencies("examples-application"),
    MechaRepoPlugin.configQueryKey := Some(configQuery),
    MechaRepoPlugin.generateConfigFileTask
  )

  def repoName = "examples-application"

  lazy val examplesApplication: Project = Project(
    "examples-application",
    file("."),
    settings = examplesApplicationSettings
  ) dependsOnSuperRepo

}
