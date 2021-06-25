buildscript {

  val kotlin_version by extra("1.5.0")
  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    classpath("com.android.tools.build:gradle:4.2.1")
  }
}