package ru.alexpanchenko.stater.plugin

import com.android.build.api.transform.TransformInput
import com.android.build.gradle.BaseExtension
import javassist.ClassPath
import javassist.ClassPool
import org.gradle.api.Project
import java.io.Closeable

class StaterClassPool(
  project: Project,
  inputs: Collection<TransformInput>,
  referencedInputs: Collection<TransformInput>
) : ClassPool(), Closeable {

  private val pathElements = ArrayList<ClassPath>()

  init {
    pathElements.add(appendSystemPath())
    // append android sdk path
    pathElements.add(
      appendClassPath(project.extensions.findByType(BaseExtension::class.java)!!.bootClasspath[0].toString())
    )
    // append inputs
    inputs.forEach { input ->
      input.directoryInputs.forEach { directoryInput ->
        pathElements.add(appendClassPath(directoryInput.file.absolutePath))
      }
      input.jarInputs.forEach { jarInput ->
        pathElements.add(appendClassPath(jarInput.file.absolutePath))
      }
    }
    // append referencedInputs
    referencedInputs.forEach { input ->
      input.directoryInputs.forEach { directoryInput ->
        pathElements.add(appendClassPath(directoryInput.file.absolutePath))
      }

      input.jarInputs.forEach { jarInput ->
        pathElements.add(appendClassPath(jarInput.getFile().absolutePath))
      }
    }
  }

  /**
   * Detach all ClassPath elements, effectively closing the class pool.
   */
  override fun close() {
    val iterator = pathElements.iterator ()
    while (iterator.hasNext()) {
      val classPath = iterator.next ()
      removeClassPath(classPath)
      iterator.remove()
    }
  }
}
