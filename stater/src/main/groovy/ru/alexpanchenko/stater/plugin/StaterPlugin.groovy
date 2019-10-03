package ru.alexpanchenko.stater.plugin

import com.android.annotations.NonNull
import com.android.build.gradle.BaseExtension
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

@TypeChecked
@CompileStatic
class StaterPlugin implements Plugin<Project> {

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
    androidExtension.registerTransform(new StaterTransform(project))
  }
}
