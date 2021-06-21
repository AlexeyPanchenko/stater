plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
  groovy
  `maven-publish`
}
//apply<com.novoda.gradle.release.ReleasePlugin>()

buildscript {
  repositories {
    google()
    jcenter()
  }
//  dependencies {
//    classpath("com.novoda:bintray-release:0.9.2")
//  }
}

repositories {
  google()
  mavenCentral()
}

dependencies {
  implementation(gradleApi())
  implementation(localGroovy())
  implementation("com.android.tools.build:gradle:4.2.1")
  implementation("com.android.tools.build:gradle-api:4.2.1")
  implementation("org.javassist:javassist:3.25.0-GA")
  implementation("org.ow2.asm:asm:7.2")
  testImplementation("junit:junit:4.12")
  testImplementation("org.mockito:mockito-core:3.1.0")
}


gradlePlugin {
  plugins {
    create("stater") {
      id = "stater-plugin"
      implementationClass = "ru.alexpanchenko.stater.plugin.StaterPlugin"
    }
  }
}

//configure<com.novoda.gradle.release.PublishExtension> {
//  userOrg = property("USER_ORG") as String
//  groupId = property("GROUP_ID") as String
//  artifactId = property("ARTIFACT_ID_PLUGIN") as String
//  publishVersion = property("VERSION") as String
//  desc = "Plugin for transform Activity/Fragment bytecode."
//  website = "https://github.com/AlexeyPanchenko/stater"
//}