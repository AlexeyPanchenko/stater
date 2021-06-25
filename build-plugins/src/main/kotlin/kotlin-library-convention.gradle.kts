plugins {
  id("java-library")
  id("kotlin-jvm-convention")
  `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}