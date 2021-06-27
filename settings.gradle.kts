rootProject.name="Stater"

include("app")
include("library")
include("serializer")

pluginManagement {

  includeBuild("stater")
  includeBuild("build-plugins")

  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    jcenter()
  }
}

dependencyResolutionManagement {

  repositories {
    google()
    mavenCentral()
    jcenter()
  }
}
