rootProject.name="Stater"

include("app")
include("library")
include("serializer")

pluginManagement {

  includeBuild("stater")

  repositories {
    google()
    mavenCentral()
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
