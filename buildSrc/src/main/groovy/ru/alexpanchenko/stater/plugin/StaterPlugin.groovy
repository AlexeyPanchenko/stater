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
    StaterPluginExtension extension = project.extensions.create('stater', StaterPluginExtension.class)
    // Automatically adds stater library
    String version = '1.1'
    project.getDependencies().add('implementation', "ru.alexpanchenko:stater:$version")

    extension.setExtensionChangeListener {
      println("extension.withCustomSerializer = ${extension.getCustomSerializerEnabled()}")
      if (extension.getCustomSerializerEnabled()) {
        //project.getDependencies().add('implementation', "ru.alexpanchenko:serializer:$version")
      }
    }

    BaseExtension androidExtension = project.extensions.findByType(BaseExtension.class)
    androidExtension.registerTransform(new StaterTransform(project))
  }
}

@TypeChecked
@CompileStatic
class StaterPluginExtension {
  private boolean customSerializerEnabled = false
  private ExtensionChangeListener listener

  void setCustomSerializerEnabled(boolean enable) {
    customSerializerEnabled = enable
    if (listener != null) {
      listener.onChanged()
    }
  }

  boolean getCustomSerializerEnabled() {
    return customSerializerEnabled
  }

  void setExtensionChangeListener(ExtensionChangeListener listener) {
    this.listener = listener
  }

}

interface ExtensionChangeListener {
  void onChanged()
}
