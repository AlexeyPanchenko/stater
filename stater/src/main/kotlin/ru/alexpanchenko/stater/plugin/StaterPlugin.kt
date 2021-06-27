package ru.alexpanchenko.stater.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

private const val VERSION = "1.2"

class StaterPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    val isAndroidApp: Boolean = project.plugins.findPlugin("com.android.application") != null
    val isAndroidLib: Boolean = project.plugins.findPlugin("com.android.library") != null
    if (!isAndroidApp && !isAndroidLib) {
      throw GradleException("'com.android.application' or 'com.android.library' plugin required.")
    }
    // Automatically add stater library
    project.dependencies.add("implementation", "ru.alexpanchenko:stater:$VERSION")

    val androidExtension: BaseExtension? = project.extensions.findByType(BaseExtension::class.java)
    androidExtension?.registerTransform(StaterTransform(project))
    registerExtension(project)
  }

  private fun registerExtension(project: Project) {
    val extension: StaterPluginExtension = project.extensions.create(
      StaterPluginExtension.NAME, StaterPluginExtension::class.java
    )
    extension.listener = { isEnabled ->
      if (isEnabled) {
        // Add serializer library
        project.dependencies.add("implementation", "ru.alexpanchenko:stater-serializer:$VERSION")
      }
    }
  }
}