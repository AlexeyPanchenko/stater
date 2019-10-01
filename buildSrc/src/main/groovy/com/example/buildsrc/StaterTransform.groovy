package com.example.buildsrc

import com.android.build.api.transform.*
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import com.example.buildsrc.visitors.StaterClassVisitor
import com.google.common.collect.ImmutableSet
import groovy.transform.TypeChecked
import javassist.ClassPool
import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.util.TraceClassVisitor

@TypeChecked
class StaterTransform extends Transform {

  Project project

  StaterTransform(Project project) {
    this.project = project
  }

  @Override
  String getName() {
    return StaterTransform.simpleName
  }

  @Override
  Set<QualifiedContent.ContentType> getInputTypes() {
    return TransformManager.CONTENT_CLASS
  }

  @Override
  Set<? super QualifiedContent.Scope> getScopes() {
    return TransformManager.PROJECT_ONLY
  }

  /**
   * @return references scopes for getting information, without transformation.
   */
  @Override
  Set<? super QualifiedContent.Scope> getReferencedScopes() {
    return ImmutableSet.of(QualifiedContent.Scope.EXTERNAL_LIBRARIES, QualifiedContent.Scope.SUB_PROJECTS)
  }

  @Override
  boolean isIncremental() {
    return false
  }

  @Override
  void transform(
      TransformInvocation transformInvocation
  ) throws TransformException, InterruptedException, IOException {
    super.transform(transformInvocation)

    long time = System.currentTimeMillis()

    ClassPool classPool = ClassPool.getDefault()
    fillPoolAndroidInputs(classPool)
    fillPoolReferencedInputs(transformInvocation, classPool)

    transformInvocation.outputProvider.deleteAll()

    transformInvocation.inputs.each { transformInput ->
      transformInput.directoryInputs.each { directoryInput ->
        classPool.appendClassPath(directoryInput.file.absolutePath)
        transformDirectoryInputs(directoryInput, transformInvocation)
      }
      transformInput.jarInputs.each { jarInput ->
        classPool.appendClassPath(jarInput.file.absolutePath)
        copyJarInputs(jarInput, transformInvocation)
      }
    }
    println("TIME = ${System.currentTimeMillis() - time}")
  }

  private void fillPoolAndroidInputs(ClassPool classPool) {
    classPool.appendClassPath(project.extensions.findByType(BaseExtension.class).bootClasspath[0].toString())
  }

  private void fillPoolReferencedInputs(TransformInvocation transformInvocation, ClassPool classPool) {
    transformInvocation.referencedInputs.each { transformInput ->
      transformInput.directoryInputs.each { directoryInput ->
        classPool.appendClassPath(directoryInput.file.absolutePath)
      }
      transformInput.jarInputs.each { jarInput ->
        classPool.appendClassPath(jarInput.file.absolutePath)
      }
    }
  }

  private void transformDirectoryInputs(DirectoryInput directoryInput, TransformInvocation transformInvocation) {
    File destFolder = transformInvocation.outputProvider.getContentLocation(
        directoryInput.getName(),
        directoryInput.getContentTypes(),
        directoryInput.getScopes(),
        Format.DIRECTORY
    )
    transformDir(directoryInput.file, destFolder)
  }

  private void copyJarInputs(JarInput jarInput, TransformInvocation transformInvocation) {
    File destFolder = transformInvocation.outputProvider.getContentLocation(
        jarInput.getName(),
        jarInput.getContentTypes(),
        jarInput.getScopes(),
        Format.JAR
    )
    FileUtils.copyFile(jarInput.file, destFolder)
  }

  private void transformDir(File input, File dest) {
    if (dest.exists()) {
      FileUtils.forceDelete(dest)
    }
    FileUtils.forceMkdir(dest)
    String srcDirPath = input.getAbsolutePath()
    String destDirPath = dest.getAbsolutePath()
    for (File file : input.listFiles()) {
      String destFilePath = file.absolutePath.replace(srcDirPath, destDirPath)
      File destFile = new File(destFilePath)
      if (file.isDirectory()) {
        transformDir(file, destFile)
      } else if (file.isFile()) {
        if (file.name.endsWith(".class")
            && !file.name.endsWith("R.class")
            && !file.name.endsWith("BuildConfig.class")
            && !file.name.contains("R\$")) {
          transformSingleFile(file, destFile)
        } else {
          FileUtils.copyFile(file, destFile)
        }
      }
    }
  }

  private void transformSingleFile(File input, File dest) {
    transformClass(input.getAbsolutePath(), dest.getAbsolutePath())
  }

  private void transformClass(String inputPath, String outputPath) {
    FileInputStream is = new FileInputStream(inputPath)
    ClassReader classReader = new ClassReader(is)
    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES)

    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out))
    StaterClassVisitor adapter = new StaterClassVisitor(classWriter)

    classReader.accept(adapter, ClassReader.EXPAND_FRAMES)
    byte [] newBytes = classWriter.toByteArray()
    FileOutputStream fos = new FileOutputStream(outputPath)
    fos.write(newBytes)
    fos.close()
  }

}
