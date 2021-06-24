package ru.alexpanchenko.stater.plugin.utils

import javassist.ClassPool
import javassist.NotFoundException
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ClassHierarchyUtilsTest {

  companion object {
    private const val A_CLASS = "ru/alexpanchenko/stater/plugin/utils/A.class"
    private const val B_CLASS = "ru/alexpanchenko/stater/plugin/utils/B.class"
    private const val C_CLASS = "ru/alexpanchenko/stater/plugin/utils/C.class"
    private const val SERIALIZABLE_PACKAGE = "java.io.Serializable"

    private val A_PACKAGE = A_CLASS.replace(".class", "").replace("/", ".")
    private val B_PACKAGE = B_CLASS.replace(".class", "").replace("/", ".")
    private val C_PACKAGE = C_CLASS.replace(".class", "").replace("/", ".")
  }

  @Test
  @Throws(NotFoundException::class)
  fun testContainsParentSelf() {
    val classPool = ClassPool.getDefault()
    classPool.appendClassPath(A_CLASS)
    assertTrue(ClassHierarchyUtils.containsParent(classPool, B_PACKAGE, B_PACKAGE))
  }

  @Test
  @Throws(NotFoundException::class)
  fun testContainsParent() {
    val classPool = ClassPool.getDefault()
    classPool.appendClassPath(A_CLASS)
    classPool.appendClassPath(B_CLASS)
    assertTrue(ClassHierarchyUtils.containsParent(classPool, B_PACKAGE, A_PACKAGE))
  }

  @Test
  @Throws(NotFoundException::class)
  fun testNotContainsParent() {
    val classPool = ClassPool.getDefault()
    classPool.appendClassPath(A_CLASS)
    classPool.appendClassPath(B_CLASS)
    classPool.appendClassPath(C_CLASS)
    assertFalse(ClassHierarchyUtils.containsParent(classPool, B_PACKAGE, C_CLASS))
    assertFalse(ClassHierarchyUtils.containsParent(classPool, A_PACKAGE, C_CLASS))
  }

  @Test
  @Throws(NotFoundException::class)
  fun testNotContainsInterface() {
    val classPool = ClassPool.getDefault()
    classPool.appendClassPath(C_CLASS)
    assertFalse(ClassHierarchyUtils.containsInterface(classPool, C_PACKAGE, SERIALIZABLE_PACKAGE))
  }

  @Test
  @Throws(NotFoundException::class)
  fun testContainsInterface() {
    val classPool = ClassPool.getDefault()
    classPool.appendClassPath(A_CLASS)
    assertTrue(ClassHierarchyUtils.containsInterface(classPool, A_PACKAGE, SERIALIZABLE_PACKAGE))
  }

  @Test
  @Throws(NotFoundException::class)
  fun testParentContainsInterface() {
    val classPool = ClassPool.getDefault()
    classPool.appendClassPath(A_CLASS)
    classPool.appendClassPath(B_CLASS)
    assertTrue(ClassHierarchyUtils.containsInterface(classPool, B_PACKAGE, SERIALIZABLE_PACKAGE))
  }
}