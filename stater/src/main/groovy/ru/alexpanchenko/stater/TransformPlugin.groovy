package ru.alexpanchenko.stater

import com.android.annotations.NonNull
import com.android.build.gradle.BaseExtension
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class TransformPlugin implements Plugin<Project> {

  @Override
  void apply(@NonNull Project project) {
    boolean isAndroidApp = project.plugins.findPlugin('com.android.application') != null
    boolean isAndroidLib = project.plugins.findPlugin('com.android.library') != null
    if (!isAndroidApp && !isAndroidLib) {
      throw new GradleException(
          "'com.android.application' or 'com.android.library' plugin required."
      )
    }
    BaseExtension androidExtension = project.extensions.findByType(BaseExtension.class)
    androidExtension.registerTransform(new MyTransformer())

    println "HELLO"
  }
}
