package ru.alexpanchenko.stater.plugin.visitors

import javassist.ClassPool
import javassist.NotFoundException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import ru.alexpanchenko.stater.plugin.StateFieldStorage
import ru.alexpanchenko.stater.plugin.utils.Const
import ru.alexpanchenko.stater.plugin.utils.Methods
import ru.alexpanchenko.stater.plugin.utils.StateTypeDeterminator
import org.mockito.Mockito.`when` as whenever

class StaterClassVisitorTest {

  companion object {
    private const val ACTIVITY_CLASS = "android/app/Activity.class"
    private const val FRAGMENT_CLASS = "androidx/fragment/app/Fragment.class"
    private val ACTIVITY_NAME = ACTIVITY_CLASS.replace(".class", "")
    private val FRAGMENT_NAME = FRAGMENT_CLASS.replace(".class", "")
  }

  private val classPool = ClassPool.getDefault()
  private val typeDeterminator = StateTypeDeterminator(classPool, false)
  private val fieldStorage = StateFieldStorage()

  @Before
  fun setUp() {
    fieldStorage.clear()
  }

  @Test
  fun testNotNeedTransform() {
    val name = "name"
    val descriptor = "descriptor"
    val signature = "signature"
    val classVisitor = mock(ClassVisitor::class.java)
    val mockFieldVisitor = mock(FieldVisitor::class.java)
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    whenever(classVisitor.visitField(Opcodes.ACC_PRIVATE, name, descriptor, signature, null))
      .thenReturn(mockFieldVisitor)
    whenever(classVisitor.visitMethod(Opcodes.ACC_PROTECTED, name, descriptor, signature, null))
      .thenReturn(mockMethodVisitor)
    val visitor = StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage)
    visitor.visitAnnotation("", true)
    val fieldVisitor = visitor.visitField(Opcodes.ACC_PRIVATE, name, descriptor, signature, null)
    assertNotEquals(fieldVisitor?.javaClass, StaterFieldVisitor::class.java)
    val methodVisitor = visitor.visitMethod(Opcodes.ACC_PROTECTED, name, descriptor, signature, null)
    assertNotEquals(methodVisitor?.javaClass, OnCreateVisitor::class.java)
    assertNotEquals(methodVisitor?.javaClass, OnSavedInstanceStateVisitor::class.java)
  }

  @Test
  @Throws(NotFoundException::class)
  fun testVisitActivityClass() {
    classPool.appendClassPath(ACTIVITY_CLASS)
    val classVisitor = mock(ClassVisitor::class.java)
    val visitor = StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage)
    visitor.visit(Const.ASM_VERSION, Opcodes.ACC_PUBLIC, "MyActivity", null, ACTIVITY_NAME, arrayOf())
    val fieldVisitor = visitor.visitField(Opcodes.ACC_PRIVATE, "name", "descriptor", null, null)
    assertEquals(fieldVisitor?.javaClass, StaterFieldVisitor::class.java)
  }

  @Test
  @Throws(NotFoundException::class)
  fun testVisitFragmentClass() {
    classPool.appendClassPath(FRAGMENT_CLASS)
    val classVisitor = mock(ClassVisitor::class.java)
    val visitor = StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage)
    visitor.visit(Const.ASM_VERSION, Opcodes.ACC_PUBLIC, "MyFragment", null, FRAGMENT_NAME, arrayOf())
    val fieldVisitor = visitor.visitField(Opcodes.ACC_PRIVATE, "name", "descriptor", null, null)
    assertEquals(fieldVisitor?.javaClass, StaterFieldVisitor::class.java)
  }

  @Test
  fun testOnCreateMethodVisitor() {
    val classVisitor = mock(ClassVisitor::class.java)
    val visitor = StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage)
    visitor.visit(Const.ASM_VERSION, Opcodes.ACC_PUBLIC, "MyActivity", null, ACTIVITY_NAME, arrayOf())
    val methodVisitor = visitor.visitMethod(Opcodes.ACC_PRIVATE, Methods.ON_CREATE, "descriptor", null, arrayOf())
    assertEquals(methodVisitor?.javaClass, OnCreateVisitor::class.java)
  }

  @Test
  fun testOnSaveInstanceStateMethodVisitor() {
    val classVisitor = mock(ClassVisitor::class.java)
    val visitor = StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage)
    visitor.visit(Const.ASM_VERSION, Opcodes.ACC_PUBLIC, "MyActivity", null, ACTIVITY_NAME, arrayOf())
    val methodVisitor = visitor.visitMethod(Opcodes.ACC_PRIVATE, Methods.ON_SAVED_INSTANCE_STATE, "descriptor", null, arrayOf())
    assertEquals(methodVisitor?.javaClass, OnSavedInstanceStateVisitor::class.java)
  }
}