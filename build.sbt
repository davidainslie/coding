import sbt._

lazy val root: Project =
  Project("coding", file("."))
    .enablePlugins(JavaAppPackaging, DockerPlugin)
    .settings(
      scalaVersion := "2.13.8",
      organization := "tech.backwards",
      evictionErrorLevel := Level.Info,
      versionScheme := Some("early-semver"),
      addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full),
      addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
      scalacOptions ++= Seq(
        "-encoding", "utf8",
        "-deprecation",
        "-unchecked",
        "-language:implicitConversions",
        "-language:higherKinds",
        "-language:existentials",
        "-language:postfixOps",
        "-Ymacro-annotations",
        "-Yrangepos",
        "-P:kind-projector:underscore-placeholders" // Can use _ instead of * when defining anonymous type lambdas
        //"-Xfatal-warnings"
        //"-Ywarn-value-discard"
      ),
      libraryDependencies ++= Dependencies(),
      dependencyCheckAssemblyAnalyzerEnabled := Some(false),
      exportJars := true,
      fork := true,
      Test / publishArtifact := true,
      IntegrationTest / publishArtifact := true,
      addArtifact(IntegrationTest / packageBin / artifact, IntegrationTest / packageBin).settings,
    )
    .configs(IntegrationTest extend Test)
    .settings(inConfig(IntegrationTest extend Test)(Defaults.testSettings): _*)
    .settings(Defaults.itSettings: _*)