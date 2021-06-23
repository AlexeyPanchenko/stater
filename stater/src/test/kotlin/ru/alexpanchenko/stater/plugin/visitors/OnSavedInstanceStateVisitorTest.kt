package ru.alexpanchenko.stater.plugin.visitors

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import ru.alexpanchenko.stater.plugin.StateFieldStorage
import ru.alexpanchenko.stater.plugin.model.SaverField
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.Descriptors
import ru.alexpanchenko.stater.plugin.utils.Methods
import ru.alexpanchenko.stater.plugin.utils.Types

class OnSavedInstanceStateVisitorTest {

  private val fieldStorage = StateFieldStorage()

  @Before
  fun setUp() {
    fieldStorage.clear()
  }

  @Test
  fun testBaseFlow() {
    val fieldName = "field1"
    val fieldDescriptor = Descriptors.INT
    val fieldOwner = Descriptors.OBJECT
    val fieldType = StateType.INT
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    val field = SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType)
    fieldStorage.add(field)
    val visitor = OnSavedInstanceStateVisitor(mockMethodVisitor, fieldStorage)
    visitor.visitCode()
    verify(mockMethodVisitor).visitCode()
    verify(mockMethodVisitor).visitVarInsn(Opcodes.ALOAD, 1)
    verify(mockMethodVisitor).visitLdcInsn(field.key)
    verify(mockMethodVisitor).visitVarInsn(Opcodes.ALOAD, 0)
    verify(mockMethodVisitor).visitFieldInsn(Opcodes.GETFIELD, field.owner, field.name, field.descriptor)
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Put.INT,
      "(${Descriptors.STRING}${Descriptors.INT})${Descriptors.VOID}",
      false
    )
  }

  @Test
  fun testPutString() {
    val fieldName = "field1"
    val fieldDescriptor = Descriptors.STRING
    val fieldOwner = Descriptors.OBJECT
    val fieldType = StateType.STRING
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    val field = SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType)
    fieldStorage.add(field)
    val visitor = OnSavedInstanceStateVisitor(mockMethodVisitor, fieldStorage)
    visitor.visitCode()
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Put.STRING,
      "(${Descriptors.STRING}${Descriptors.STRING})${Descriptors.VOID}",
      false
    )
  }

  @Test
  fun testCastListToArrayList() {
    val fieldName = "field1"
    val fieldDescriptor = Descriptors.LIST
    val fieldOwner = Descriptors.OBJECT
    val fieldType = StateType.INT_ARRAY_LIST
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    val field = SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType)
    fieldStorage.add(field)
    val visitor = OnSavedInstanceStateVisitor(mockMethodVisitor, fieldStorage)
    visitor.visitCode()
    verify(mockMethodVisitor).visitTypeInsn(Opcodes.CHECKCAST, Types.ARRAY_LIST)
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Put.INT_ARRAY_LIST,
      "(${Descriptors.STRING}${Descriptors.ARRAY_LIST})${Descriptors.VOID}",
      false
    )
  }

  @Test
  fun testSerializeCustomType() {
    val fieldName = "field1"
    val fieldDescriptor = Descriptors.OBJECT
    val fieldOwner = Descriptors.OBJECT
    val fieldType = StateType.CUSTOM
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    val field = SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType)
    fieldStorage.add(field)
    val visitor = OnSavedInstanceStateVisitor(mockMethodVisitor, fieldStorage)
    visitor.visitCode()
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKESTATIC,
      Types.SERIALIZER,
      Methods.SERIALIZE,
      Descriptors.SERIALIZER_SERIALIZE,
      false
    )
  }
}