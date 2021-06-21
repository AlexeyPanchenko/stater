//buildscript {
//    ext.kotlin_version = '1.4.30'
//    repositories {
//        google()
//        jcenter()
////        flatDir dirs: "app/libs"
//
//    }
//    dependencies {
//        classpath 'com.android.tools.build:gradle:4.2.1'
//        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
////        classpath "com.novoda:bintray-release:0.9.1"
////        classpath "org.anarres.jarjar:jarjar-gradle:1.0.1"
////        classpath "ru.alexpanchenko:stater-plugin-1.1"
//    }
//}

//allprojects {
//    repositories {
//        google()
//        jcenter()
//    }
//}

buildscript {

//  extra.apply {
//    set("kotlin_version", "1.4.30")
//  }

  repositories {
    mavenCentral()
  }

  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
  }
}

//ext.kotlin_version = '1.4.32'
//
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
