package ru.alexpanchenko.stater

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils

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
    println("${transformInvocation.inputs}")
    println("${transformInvocation.referencedInputs}")
    println("${transformInvocation.outputProvider}")
    println("${transformInvocation.incremental}")


    Collection<TransformInput> inputs = transformInvocation.getInputs()
    Collection<TransformInput> referencedInputs = transformInvocation.getReferencedInputs()
    println("inputs = ${inputs}")
    println("referencedInputs = ${referencedInputs}")
    TransformOutputProvider outputProvider = transformInvocation.getOutputProvider()
    for (TransformInput input : inputs) {
      for (JarInput jarInput : input.getJarInputs()) {
        File dest = outputProvider.getContentLocation(
            jarInput.getFile().getAbsolutePath(),
            jarInput.getContentTypes(),
            jarInput.getScopes(),
            Format.JAR)
        transformJar(jarInput.getFile(), dest)
      }
      for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
        println("== DI = " + directoryInput.file.listFiles().toArrayString())
        File dest = outputProvider.getContentLocation(directoryInput.getName(),
            directoryInput.getContentTypes(), directoryInput.getScopes(),
            Format.DIRECTORY)
        transformDir(directoryInput.getFile(), dest)
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
    println("=== transform dir = " + srcDirPath + ", " + destDirPath)
    for (File file : input.listFiles()) {
      String destFilePath = file.absolutePath.replace(srcDirPath, destDirPath)
      File destFile = new File(destFilePath)
      if (file.isDirectory()) {
        transformDir(file, destFile)
      } else if (file.isFile()) {
        FileUtils.touch(destFile)
        transformSingleFile(file, destFile)
      }
    }
  }

  private static void transformSingleFile(File input, File dest) {
    println("=== transformSingleFile === input = ${input} dest = ${dest}")
    //weave(input.getAbsolutePath(), dest.getAbsolutePath())
  }

//  private static void weave(String inputPath, String outputPath) {
//    try {
//      FileInputStream is = new FileInputStream(inputPath)
//      ClassReader cr = new ClassReader(is)
//      ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES)
//      TestMethodClassAdapter adapter = new TestMethodClassAdapter(cw)
//      cr.accept(adapter, 0)
//      FileOutputStream fos = new FileOutputStream(outputPath)
//      fos.write(cw.toByteArray())
//      fos.close()
//    } catch (IOException e) {
//      e.printStackTrace()
//    }
//  }

}
