package ru.alexpanchenko.stater.plugin.visitors

import javassist.ClassPool
import javassist.NotFoundException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.objectweb.asm.FieldVisitor
import ru.alexpanchenko.stater.plugin.StateFieldStorage
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.Descriptors
import ru.alexpanchenko.stater.plugin.utils.StateTypeDeterminator
import org.mockito.Mockito.`when` as whenever

class StaterFieldVisitorTest {

  companion object {
    private const val A_CLASS = "ru/alexpanchenko/stater/plugin/utils/A.class"
    private const val B_CLASS = "ru/alexpanchenko/stater/plugin/utils/B.class"
    private val B_PACKAGE = B_CLASS.replace(".class", "").replace("/", ".")
  }

  private val classPool = ClassPool.getDefault()
  private val fieldStorage = StateFieldStorage()
  private val fieldVisitor = mock(FieldVisitor::class.java)
  private val name = "name"
  private var descriptor = "descriptor"
  private var signature: String? = "signature"
  private val owner = "owner"

  @Before
  fun setUp() {
    fieldStorage.clear()
  }

  @Test
  fun testDeterminatorDeterminate() {
    val typeDeterminator = mock(StateTypeDeterminator::class.java)
    whenever(typeDeterminator.determinate(descriptor, signature)).thenReturn(StateType.BOOLEAN)
    val visitor = StaterFieldVisitor(fieldVisitor, name, descriptor, signature, owner, typeDeterminator, fieldStorage)
    visitor.visitAnnotation(Descriptors.STATE, true)
    verify(typeDeterminator).determinate(descriptor, signature)
  }

  @Test
  fun testDeterminatorNotDeterminate() {
    val typeDeterminator = mock(StateTypeDeterminator::class.java)
    val visitor = StaterFieldVisitor(fieldVisitor, name, descriptor, signature, owner, typeDeterminator, fieldStorage)
    visitor.visitAnnotation(Descriptors.STATE + "a", true)
    verify(typeDeterminator, never()).determinate(descriptor, signature)
  }

  @Test(expected = IllegalStateException::class)
  fun testIncorrectType() {
    descriptor = "Lcom/example/Fake"
    signature = null
    val visitor = StaterFieldVisitor(
      fieldVisitor, name, descriptor, signature, owner, StateTypeDeterminator(classPool, false), fieldStorage
    )
    visitor.visitAnnotation(Descriptors.STATE, true)
  }

  @Test
  fun testBoolean() {
    descriptor = Descriptors.BOOLEAN
    signature = null
    val visitor = StaterFieldVisitor(
      fieldVisitor, name, descriptor, signature, owner, StateTypeDeterminator(classPool, false), fieldStorage
    )
    visitor.visitAnnotation(Descriptors.STATE, true)
    assertFalse(fieldStorage.fields.isEmpty())
    assertEquals(fieldStorage.fields.size.toLong(), 1)
    assertEquals(fieldStorage.fields[0].type, StateType.BOOLEAN)
  }

  @Test
  @Throws(NotFoundException::class)
  fun testSerializable() {
    classPool.appendClassPath(A_CLASS)
    classPool.appendClassPath(B_CLASS)
    descriptor = "L$B_PACKAGE;"
    signature = null
    val visitor = StaterFieldVisitor(
      fieldVisitor, name, descriptor, signature, owner, StateTypeDeterminator(classPool, false), fieldStorage
    )
    visitor.visitAnnotation(Descriptors.STATE, true)
    assertEquals(fieldStorage.fields[0].type, StateType.SERIALIZABLE)
  }
}