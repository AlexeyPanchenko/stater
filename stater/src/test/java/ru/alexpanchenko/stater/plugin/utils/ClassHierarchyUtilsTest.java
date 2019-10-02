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
    ClassPool.getDefault().appendClassPath(A_CLASS);
    assertTrue(ClassHierarchyUtils.containsParent(B_PACKAGE, B_PACKAGE));
  }

  @Test
  public void testContainsParent() throws NotFoundException {
    ClassPool.getDefault().appendClassPath(A_CLASS);
    ClassPool.getDefault().appendClassPath(B_CLASS);
    assertTrue(ClassHierarchyUtils.containsParent(B_PACKAGE, A_PACKAGE));
  }

  @Test
  public void testNotContainsParent() throws NotFoundException {
    ClassPool.getDefault().appendClassPath(A_CLASS);
    ClassPool.getDefault().appendClassPath(B_CLASS);
    ClassPool.getDefault().appendClassPath(C_CLASS);
    assertFalse(ClassHierarchyUtils.containsParent(B_PACKAGE, C_CLASS));
    assertFalse(ClassHierarchyUtils.containsParent(A_PACKAGE, C_CLASS));
  }

  @Test
  public void testNotContainsInterface() throws NotFoundException {
    ClassPool.getDefault().appendClassPath(C_CLASS);
    assertFalse(ClassHierarchyUtils.containsInterface(C_PACKAGE, SERIALIZABLE_PACKAGE));
  }

  @Test
  public void testContainsInterface() throws NotFoundException {
    ClassPool.getDefault().appendClassPath(A_CLASS);
    assertTrue(ClassHierarchyUtils.containsInterface(A_PACKAGE, SERIALIZABLE_PACKAGE));
  }

  @Test
  public void testParentContainsInterface() throws NotFoundException {
    ClassPool.getDefault().appendClassPath(A_CLASS);
    ClassPool.getDefault().appendClassPath(B_CLASS);
    assertTrue(ClassHierarchyUtils.containsInterface(B_PACKAGE, SERIALIZABLE_PACKAGE));
  }


}
