plugins {
  id("android-app-convention")
  id("stater-plugin")
}

android {
  defaultConfig {
    applicationId = "com.example.stater"
  }
}

stater {
  customSerializerEnabled = true
}

dependencies {
  implementation("androidx.appcompat:appcompat:1.3.0")
}