import ru.alexpanchenko.stater.plugin.StaterPluginExtension

plugins {
  id("android-app-convention")
  id("stater-plugin")
}

android {
  defaultConfig {
    applicationId = "com.example.stater"
  }
}

configure<StaterPluginExtension> {
  customSerializerEnabled = true
}

dependencies {
  implementation("androidx.appcompat:appcompat:1.3.0")
}