import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
  setCompileSdkVersion(30)
  defaultConfig {
    setMinSdkVersion(16)
    setTargetSdkVersion(30)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  signingConfigs {
    create("release") {
      storeFile = file("debug.jks")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("release")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
}

fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? =
  add("implementation", dependencyNotation)