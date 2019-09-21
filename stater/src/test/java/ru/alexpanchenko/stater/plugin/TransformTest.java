package ru.alexpanchenko.stater.plugin;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import ru.alexpanchenko.stater.plugin.visitors.StaterClassVisitor;

import static org.junit.Assert.assertArrayEquals;

/**
 * Test for compare TestClass.class after transform with TestResultClass.class.
 */
public class TransformTest {

  private static final String PATH = "/src/test/java/ru/alexpanchenko/stater/plugin/classes/";
  private static final String TEST_CLASS = "/TestClass.class";
  private static final String TEST_RESULT_CLASS = "/TestResultClass.class";

  @Test
  public void testCompareFiles() throws IOException {
    Path path = Paths.get(new File("any").getAbsolutePath());
    assertArrayEquals(getTransformedBytes(path), getExpectedBytes(path));
  }

  private byte[] getTransformedBytes(Path path) throws IOException {
    FileInputStream inputStream = new FileInputStream(path.getParent().toString() + PATH + TEST_CLASS);
    ClassReader classReader = new ClassReader(inputStream);
    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    StaterClassVisitor adapter = new StaterClassVisitor(classWriter);
    classReader.accept(adapter, ClassReader.EXPAND_FRAMES);
    return classWriter.toByteArray();
  }

  private byte[] getExpectedBytes(Path path) throws IOException {
    FileInputStream inputStream = new FileInputStream(
      path.getParent().toString() + PATH + TEST_RESULT_CLASS
    );
    ClassReader classReader = new ClassReader(inputStream);
    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    classReader.accept(classWriter, ClassReader.EXPAND_FRAMES);
    return classWriter.toByteArray();
  }

}
