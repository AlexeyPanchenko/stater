plugins {
  `java-gradle-plugin`
  `maven-publish`
  kotlin("jvm") version "1.5.0"
}

repositories {
  google()
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.android.tools.build:gradle:4.2.1")
  implementation("com.android.tools.build:gradle-api:4.2.1")
  implementation("org.javassist:javassist:3.25.0-GA")
  implementation("org.ow2.asm:asm:7.2")
  testImplementation("junit:junit:4.13.2")
  testImplementation("org.mockito:mockito-core:3.8.0")
  testImplementation("org.mockito:mockito-inline:3.8.0")
}

gradlePlugin {
  plugins {
    create("stater") {
      id = "stater-plugin"
      implementationClass = "ru.alexpanchenko.stater.plugin.StaterPlugin"
    }
  }
}