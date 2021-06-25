plugins {
  `kotlin-dsl`
}

repositories {
  google()
  mavenCentral()
}

dependencies {
  implementation("com.android.tools.build:gradle:4.2.1")
  implementation(kotlin(module = "gradle-plugin", version = "1.4.32"))
}