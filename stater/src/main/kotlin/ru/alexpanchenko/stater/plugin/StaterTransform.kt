package ru.alexpanchenko.stater.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import javassist.ClassPool
import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import ru.alexpanchenko.stater.plugin.utils.StateTypeDeterminator
import ru.alexpanchenko.stater.plugin.visitors.StaterClassVisitor
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class StaterTransform(
  private val project: Project
) : Transform() {

  override fun getName(): String = StaterTransform::class.java.simpleName

  override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> = TransformManager.CONTENT_CLASS

  override fun getScopes(): MutableSet<in QualifiedContent.Scope> = TransformManager.PROJECT_ONLY

  override fun getReferencedScopes(): MutableSet<in QualifiedContent.Scope> =
    mutableSetOf(QualifiedContent.Scope.EXTERNAL_LIBRARIES, QualifiedContent.Scope.SUB_PROJECTS)

  override fun isIncremental(): Boolean = false

  override fun transform(transformInvocation: TransformInvocation) {
    transformInvocation.outputProvider.deleteAll()

    val classPool = StaterClassPool(
      project, transformInvocation.inputs, transformInvocation.referencedInputs
    )
    val withCustomSerializer =
      project.extensions.getByType(StaterPluginExtension::class.java).customSerializerEnabled

    val typeDeterminator = StateTypeDeterminator(classPool, withCustomSerializer)
    val fieldStorage = StateFieldStorage()

    transformInvocation.inputs.forEach { transformInput ->
      transformInput.directoryInputs.forEach { directoryInput ->
        transformDirectoryInputs(classPool, directoryInput, transformInvocation, typeDeterminator, fieldStorage)
      }
      transformInput.jarInputs.forEach { jarInput ->
        copyJarInputs(jarInput, transformInvocation)
      }
    }
    classPool.close()
  }

  private fun transformDirectoryInputs(
    classPool: ClassPool,
    directoryInput: DirectoryInput,
    transformInvocation: TransformInvocation,
    typeDeterminator: StateTypeDeterminator,
    fieldStorage: StateFieldStorage
  ) {
    val destFolder: File = transformInvocation.outputProvider.getContentLocation(
      directoryInput.name,
      directoryInput.contentTypes,
      directoryInput.scopes,
      Format.DIRECTORY
    )
    transformDir(classPool, directoryInput.file, destFolder, typeDeterminator, fieldStorage)
  }

  private fun copyJarInputs(jarInput: JarInput, transformInvocation: TransformInvocation) {
    val destFolder: File = transformInvocation.outputProvider.getContentLocation(
      jarInput.name,
      jarInput.contentTypes,
      jarInput.scopes,
      Format.JAR
    )
    FileUtils.copyFile(jarInput.file, destFolder)
  }

  private fun transformDir(
    classPool: ClassPool,
    input: File,
    dest: File,
    typeDeterminator: StateTypeDeterminator,
    fieldStorage: StateFieldStorage
  ) {
    if (dest.exists()) {
      FileUtils.forceDelete(dest)
    }
    FileUtils.forceMkdir(dest)
    val srcDirPath: String = input.absolutePath
    val destDirPath: String = dest.absolutePath
    input.listFiles().forEach { file ->
      val destFilePath: String = file.absolutePath.replace(srcDirPath, destDirPath)
      val destFile = File(destFilePath)
      if (file.isDirectory) {
        transformDir(classPool, file, destFile, typeDeterminator, fieldStorage)
      } else if (file.isFile) {
        if (file.name.endsWith(".class")
          && !file.name.endsWith("R.class")
          && !file.name.endsWith("BuildConfig.class")
          && !file.name.contains("R\$")
        ) {
          transformSingleFile(classPool, file, destFile, typeDeterminator, fieldStorage)
        } else {
          FileUtils.copyFile(file, destFile)
        }
      }
    }
  }

  private fun transformSingleFile(
    classPool: ClassPool,
    input: File,
    dest: File,
    typeDeterminator: StateTypeDeterminator,
    fieldStorage: StateFieldStorage
  ) {
    transformClass(classPool, input.absolutePath, dest.absolutePath, typeDeterminator, fieldStorage)
  }

  private fun transformClass(
    classPool: ClassPool,
    inputPath: String,
    outputPath: String,
    typeDeterminator: StateTypeDeterminator,
    fieldStorage: StateFieldStorage
  ) {
    val inputStream = FileInputStream(inputPath)
    val classReader = ClassReader(inputStream)
    val classWriter = ClassWriter(ClassWriter.COMPUTE_MAXS)

    val adapter = StaterClassVisitor(
      classWriter,
      classPool,
      typeDeterminator,
      fieldStorage
    )

    classReader.accept(adapter, ClassReader.SKIP_FRAMES)
    val newBytes: ByteArray = classWriter.toByteArray()
    val fos = FileOutputStream(outputPath)
    fos.write(newBytes)
    fos.close()
  }
}