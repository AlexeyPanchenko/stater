rootProject.name="Stater"

include("app")
include("library")

pluginManagement {

  includeBuild("stater")

  repositories {
    google()
    mavenCentral()
    jcenter()
  }

  resolutionStrategy {
    eachPlugin {
      val pluginId = requested.id.id
      if (pluginId.startsWith("com.android.")) {
        useModule("com.android.tools.build:gradle:4.2.1")
      } else if (pluginId.startsWith("com.novoda.bintray-release")) {
        useModule("com.novoda:bintray-release:0.9.1")
      }
    }
  }
}

dependencyResolutionManagement {

  repositories {
    google()
    mavenCentral()
    jcenter()
  }
}
