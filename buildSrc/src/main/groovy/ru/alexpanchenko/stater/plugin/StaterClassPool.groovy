package ru.alexpanchenko.stater.plugin

import com.android.annotations.NonNull
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.TransformInput
import com.android.build.gradle.BaseExtension
import groovy.transform.TypeChecked
import javassist.ClassPath
import javassist.ClassPool
import org.gradle.api.Project

/**
 * Wrapper around JavaAssists {@link ClassPool}, that allows for correct cleanup of the resources used.
 *
 * @link https://github.com/realm/realm-java/blob/master/realm-transformer/src/main/kotlin/io/realm/transformer/ManagedClassPool.kt
 */
@TypeChecked
class StaterClassPool extends ClassPool implements Closeable {

  private final ArrayList<ClassPath> pathElements = new ArrayList<ClassPath>()

  StaterClassPool(
      @NonNull Project project,
      @NonNull Collection<TransformInput> inputs,
      @NonNull Collection<TransformInput> referencedInputs
  ) {
    pathElements.add(appendSystemPath())
    // append android sdk path
    pathElements.add(
        appendClassPath(project.extensions.findByType(BaseExtension.class).bootClasspath[0].toString())
    )
    // append inputs
    inputs.each { input ->
      input.directoryInputs.each { QualifiedContent directoryInput ->
        pathElements.add(appendClassPath(directoryInput.file.absolutePath))
      }

      input.jarInputs.forEach { QualifiedContent jarInput ->
        pathElements.add(appendClassPath(jarInput.file.absolutePath))
      }
    }
    // append referencedInputs
    referencedInputs.each { input ->
      input.directoryInputs.each { QualifiedContent directoryInput ->
        pathElements.add(appendClassPath(directoryInput.file.absolutePath))
      }

      input.jarInputs.forEach { QualifiedContent jarInput ->
        pathElements.add(appendClassPath(jarInput.getFile().absolutePath))
      }
    }
  }


  /**
   * Detach all ClassPath elements, effectively closing the class pool.
   */
  @Override
  void close() throws IOException {
    Iterator<ClassPath> iterator = pathElements.iterator()
    while (iterator.hasNext()) {
      ClassPath classPath = iterator.next()
      removeClassPath(classPath)
      iterator.remove()
    }
  }
}
