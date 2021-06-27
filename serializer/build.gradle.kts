plugins {
  id("kotlin-library-convention")
}

dependencies {
  implementation("com.google.code.gson:gson:2.8.6")
  testImplementation("junit:junit:4.13.2")
}

// ./gradlew :serializer:clean :serializer:build :serializer:bintrayUpload -PbintrayUser=BINTRAY_USERNAME -PbintrayKey=BINTRAY_KEY -PdryRun=false