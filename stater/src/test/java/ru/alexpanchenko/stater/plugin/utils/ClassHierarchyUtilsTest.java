package ru.alexpanchenko.stater.plugin.utils;

import org.junit.Test;

import javassist.ClassPool;
import javassist.NotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClassHierarchyUtilsTest {

  private static final String A_CLASS = "ru/alexpanchenko/stater/plugin/utils/models/A.class";
  private static final String A_PACKAGE = A_CLASS.replace(".class", "").replace("/", ".");
  private static final String B_CLASS = "ru/alexpanchenko/stater/plugin/utils/models/B.class";
  private static final String B_PACKAGE = B_CLASS.replace(".class", "").replace("/", ".");
  private static final String C_CLASS = "ru/alexpanchenko/stater/plugin/utils/models/C.class";
  private static final String C_PACKAGE = C_CLASS.replace(".class", "").replace("/", ".");
  private static final String SERIALIZABLE_PACKAGE = "java.io.Serializable";

  @Test
  public void testContainsParentSelf() throws NotFoundException {
    final ClassPool classPool = ClassPool.getDefault();
    classPool.appendClassPath(A_CLASS);
    assertTrue(ClassHierarchyUtils.containsParent(classPool, B_PACKAGE, B_PACKAGE));
  }

  @Test
  public void testContainsParent() throws NotFoundException {
    final ClassPool classPool = ClassPool.getDefault();
    classPool.appendClassPath(A_CLASS);
    classPool.appendClassPath(B_CLASS);
    assertTrue(ClassHierarchyUtils.containsParent(classPool, B_PACKAGE, A_PACKAGE));
  }

  @Test
  public void testNotContainsParent() throws NotFoundException {
    final ClassPool classPool = ClassPool.getDefault();
    classPool.appendClassPath(A_CLASS);
    classPool.appendClassPath(B_CLASS);
    classPool.appendClassPath(C_CLASS);
    assertFalse(ClassHierarchyUtils.containsParent(classPool, B_PACKAGE, C_CLASS));
    assertFalse(ClassHierarchyUtils.containsParent(classPool, A_PACKAGE, C_CLASS));
  }

  @Test
  public void testNotContainsInterface() throws NotFoundException {
    final ClassPool classPool = ClassPool.getDefault();
    classPool.appendClassPath(C_CLASS);
    assertFalse(ClassHierarchyUtils.containsInterface(classPool, C_PACKAGE, SERIALIZABLE_PACKAGE));
  }

  @Test
  public void testContainsInterface() throws NotFoundException {
    final ClassPool classPool = ClassPool.getDefault();
    classPool.appendClassPath(A_CLASS);
    assertTrue(ClassHierarchyUtils.containsInterface(classPool, A_PACKAGE, SERIALIZABLE_PACKAGE));
  }

  @Test
  public void testParentContainsInterface() throws NotFoundException {
    final ClassPool classPool = ClassPool.getDefault();
    classPool.appendClassPath(A_CLASS);
    classPool.appendClassPath(B_CLASS);
    assertTrue(ClassHierarchyUtils.containsInterface(classPool, B_PACKAGE, SERIALIZABLE_PACKAGE));
  }


}
