package ru.alexpanchenko.stater.plugin

import com.android.annotations.NonNull
import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.google.common.collect.ImmutableSet
import groovy.transform.TypeChecked
import javassist.ClassPool
import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.util.TraceClassVisitor
import ru.alexpanchenko.stater.plugin.visitors.StaterClassVisitor

@TypeChecked
class StaterTransform extends Transform {

  private final Project project
  private boolean withCustomSerializer

  StaterTransform(@NonNull Project project) {
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
    this.withCustomSerializer = project.extensions.getByType(StaterPluginExtension.class).getCustomSerializerEnabled()

    transformInvocation.outputProvider.deleteAll()

    ClassPool classPool = new StaterClassPool(
        project, transformInvocation.inputs, transformInvocation.referencedInputs
    )

    transformInvocation.inputs.each { transformInput ->
      transformInput.directoryInputs.each { directoryInput ->
        transformDirectoryInputs(classPool, directoryInput, transformInvocation)
      }
      transformInput.jarInputs.each { jarInput ->
        copyJarInputs(jarInput, transformInvocation)
      }
    }
    classPool.close()
  }

  private void transformDirectoryInputs(
      @NonNull ClassPool classPool,
      @NonNull DirectoryInput directoryInput,
      @NonNull TransformInvocation transformInvocation
  ) {
    File destFolder = transformInvocation.outputProvider.getContentLocation(
        directoryInput.getName(),
        directoryInput.getContentTypes(),
        directoryInput.getScopes(),
        Format.DIRECTORY
    )
    transformDir(classPool, directoryInput.file, destFolder)
  }

  private void copyJarInputs(@NonNull JarInput jarInput, @NonNull TransformInvocation transformInvocation) {
    File destFolder = transformInvocation.outputProvider.getContentLocation(
        jarInput.getName(),
        jarInput.getContentTypes(),
        jarInput.getScopes(),
        Format.JAR
    )
    FileUtils.copyFile(jarInput.file, destFolder)
  }

  private void transformDir(@NonNull ClassPool classPool, @NonNull File input, @NonNull File dest) {
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
        transformDir(classPool, file, destFile)
      } else if (file.isFile()) {
        if (file.name.endsWith(".class")
            && !file.name.endsWith("R.class")
            && !file.name.endsWith("BuildConfig.class")
            && !file.name.contains("R\$")) {
          transformSingleFile(classPool, file, destFile)
        } else {
          FileUtils.copyFile(file, destFile)
        }
      }
    }
  }

  private void transformSingleFile(@NonNull ClassPool classPool, @NonNull File input, @NonNull File dest) {
    transformClass(classPool, input.getAbsolutePath(), dest.getAbsolutePath())
  }

  private void transformClass(@NonNull ClassPool classPool, @NonNull String inputPath, @NonNull String outputPath) {
    FileInputStream is = new FileInputStream(inputPath)
    ClassReader classReader = new ClassReader(is)
    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS)

    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out))
    StaterClassVisitor adapter = new StaterClassVisitor(traceClassVisitor, classPool, withCustomSerializer)

    classReader.accept(adapter, ClassReader.SKIP_FRAMES)
    byte [] newBytes = classWriter.toByteArray()
    FileOutputStream fos = new FileOutputStream(outputPath)
    fos.write(newBytes)
    fos.close()
  }

}
