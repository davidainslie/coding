import scala.collection.LinearSeq
import sbt._

object Dependencies {
  def apply(): Seq[ModuleID] =
    LinearSeq(scalaMeta, scalatest, specs2, pprint).flatten

  lazy val scalaMeta: Seq[ModuleID] =
    LinearSeq("org.scalameta" %% "scalameta" % "4.5.6")

  lazy val scalatest: Seq[ModuleID] =
    LinearSeq("org.scalatest" %% "scalatest" % "3.2.12" % "test, it" withSources() withJavadoc())

  lazy val specs2: Seq[ModuleID] = {
    val group = "org.specs2"
    val version = "4.15.0"

    LinearSeq(
      "specs2-core", "specs2-scalacheck", "specs2-matcher-extra"
    ).map(group %% _ % version % "test, it" withSources() withJavadoc())
  }

  lazy val pprint: Seq[ModuleID] =
    LinearSeq("com.lihaoyi" %% "pprint" % "0.7.3")
}