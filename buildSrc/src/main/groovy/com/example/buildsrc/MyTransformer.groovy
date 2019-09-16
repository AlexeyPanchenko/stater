package com.example.buildsrc

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import groovy.transform.TypeChecked
import org.apache.commons.io.FileUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.util.TraceClassVisitor

@TypeChecked
class MyTransformer extends Transform {

  @Override
  String getName() {
    return MyTransformer.simpleName
  }

  @Override
  Set<QualifiedContent.ContentType> getInputTypes() {
    return TransformManager.CONTENT_CLASS
  }

  @Override
  Set<? super QualifiedContent.Scope> getScopes() {
    return TransformManager.PROJECT_ONLY
  }

//  @Override
//  Set<? super QualifiedContent.Scope> getReferencedScopes() {
//    return ImmutableSet.of(QualifiedContent.Scope.EXTERNAL_LIBRARIES, QualifiedContent.Scope.SUB_PROJECTS)
//  }

  @Override
  boolean isIncremental() {
    return false
  }

  @Override
  void transform(
      TransformInvocation transformInvocation
  ) throws TransformException, InterruptedException, IOException {
    super.transform(transformInvocation)
    println("===== ASM Transform =====")

    transformInvocation.outputProvider.deleteAll()

    transformInvocation.inputs.each { transformInput ->
      transformInput.directoryInputs.each { directoryInput ->
        File inputFile = directoryInput.getFile()

        File destFolder = transformInvocation.outputProvider.getContentLocation(
            directoryInput.getName(),
            directoryInput.getContentTypes(),
            directoryInput.getScopes(),
            Format.DIRECTORY
        )
        transformDir(inputFile, destFolder)
      }

      transformInput.jarInputs.each { jarInput ->
        File inputFile = jarInput.getFile()

        File destFolder = transformInvocation.outputProvider.getContentLocation(
            jarInput.getName(),
            jarInput.getContentTypes(),
            jarInput.getScopes(),
            Format.JAR
        )
        FileUtils.copyFile(inputFile, destFolder)
      }
    }
  }

  private static void transformDir(File input, File dest) {
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
        //FileUtils.touch(destFile)
        //FileUtils.copyFile(file, destFile)
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

  private static void transformSingleFile(File input, File dest) {
    transformClass(input.getAbsolutePath(), dest.getAbsolutePath())
  }

  private static void transformClass(String inputPath, String outputPath) {
    FileInputStream is = new FileInputStream(inputPath)
    ClassReader classReader = new ClassReader(is)
    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES)


    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out))
    ActivityClassVisitor adapter = new ActivityClassVisitor(traceClassVisitor)


    classReader.accept(adapter, ClassReader.EXPAND_FRAMES)
    byte [] newBytes = classWriter.toByteArray()
    FileOutputStream fos = new FileOutputStream(outputPath)
    fos.write(newBytes)
    fos.close()
  }

}
