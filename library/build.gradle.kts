

plugins {
  id("java-library")
//  id 'com.novoda.bintray-release'
  `maven-publish`
}
//apply plugin: 'maven'

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

//publish {
//    userOrg = USER_ORG
//    groupId = GROUP_ID
//    artifactId = ARTIFACT_ID_LIB
//    publishVersion = VERSION
//    desc = 'Lightweight library to save state in your Activity/Fragment.'
//    website = 'https://github.com/AlexeyPanchenko/stater'
//}

// ./gradlew :library:clean :library:build :library:bintrayUpload -PbintrayUser=BINTRAY_USERNAME -PbintrayKey=BINTRAY_KEY -PdryRun=false