package ru.alexpanchenko.stater.plugin.visitors

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import ru.alexpanchenko.stater.plugin.StateFieldStorage
import ru.alexpanchenko.stater.plugin.model.SaverField
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.Descriptors
import ru.alexpanchenko.stater.plugin.utils.Methods
import ru.alexpanchenko.stater.plugin.utils.Types

class OnCreateVisitorTest {

  private val fieldStorage = StateFieldStorage()

  @Before
  fun setUp() {
    fieldStorage.clear()
  }

  @Test
  fun testBaseFlow() {
    val fieldName = "field1"
    val fieldDescriptor = Descriptors.INT
    val fieldOwner = Descriptors.INT
    val fieldType = StateType.INT
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    val field = SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType)
    fieldStorage.add(field)
    val onCreateVisitor = OnCreateVisitor(mockMethodVisitor, fieldStorage)
    onCreateVisitor.visitCode()
    verify(mockMethodVisitor).visitCode()
    verify(mockMethodVisitor, times(2)).visitVarInsn(Opcodes.ALOAD, 1)
    verify(mockMethodVisitor).visitJumpInsn(eq(Opcodes.IFNULL), any(Label::class.java))
    verify(mockMethodVisitor).visitVarInsn(Opcodes.ALOAD, 0)
    verify(mockMethodVisitor).visitLdcInsn(field.key)
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Get.INT,
      "(${Descriptors.STRING})${Descriptors.INT}",
      false
    )
    verify(mockMethodVisitor).visitFieldInsn(Opcodes.PUTFIELD, field.owner, field.name, field.descriptor)
  }

  @Test
  fun testGetInt() {
    val fieldName = "field1"
    val fieldDescriptor = Descriptors.INT
    val fieldOwner = Descriptors.INT
    val fieldType = StateType.INT
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    val field = SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType)
    fieldStorage.add(field)
    val onCreateVisitor = OnCreateVisitor(mockMethodVisitor, fieldStorage)
    onCreateVisitor.visitCode()
    verify(mockMethodVisitor).visitCode()
    verify(mockMethodVisitor).visitLdcInsn(field.key)
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Get.INT,
      "(${Descriptors.STRING})${Descriptors.INT}",
      false
    )
    verify(mockMethodVisitor).visitFieldInsn(Opcodes.PUTFIELD, field.owner, field.name, field.descriptor)
  }

  @Test
  fun testSerializableCast() {
    val fieldName = "field1"
    val fieldDescriptor = Descriptors.INT
    val fieldOwner = Descriptors.SERIALIZABLE
    val fieldType = StateType.SERIALIZABLE
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    val field = SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType)
    fieldStorage.add(field)
    val onCreateVisitor = OnCreateVisitor(mockMethodVisitor, fieldStorage)
    onCreateVisitor.visitCode()
    verify(mockMethodVisitor).visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).internalName)
  }

  @Test
  fun testCustomField() {
    val fieldName = "field1"
    val fieldDescriptor = Descriptors.OBJECT
    val fieldOwner = Descriptors.OBJECT
    val fieldType = StateType.CUSTOM
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    val field = SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType)
    fieldStorage.add(field)
    val onCreateVisitor = OnCreateVisitor(mockMethodVisitor, fieldStorage)
    onCreateVisitor.visitCode()
    verify(mockMethodVisitor).visitLdcInsn(Type.getType(field.descriptor))
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKESTATIC,
      Types.SERIALIZER,
      Methods.DESERIALIZE,
      Descriptors.SERIALIZER_DESERIALIZE,
      false
    )
    verify(mockMethodVisitor).visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).internalName)
  }

  @Test
  fun testCustomTypedField() {
    val fieldName = "field1"
    val fieldDescriptor = Descriptors.LIST
    val fieldSignature = "L" + Types.LIST + "<" + Descriptors.OBJECT + ">;"
    val fieldOwner = Descriptors.OBJECT
    val fieldType = StateType.CUSTOM
    val mockMethodVisitor = mock(MethodVisitor::class.java)
    val field = SaverField(fieldName, fieldDescriptor, fieldSignature, fieldOwner, fieldType)
    fieldStorage.add(field)
    val onCreateVisitor = OnCreateVisitor(mockMethodVisitor, fieldStorage)
    onCreateVisitor.visitCode()
    verify(mockMethodVisitor).visitInsn(Opcodes.ICONST_2)
    verify(mockMethodVisitor).visitTypeInsn(Opcodes.ANEWARRAY, Types.CLASS)
    verify(mockMethodVisitor, times(2)).visitInsn(Opcodes.DUP)
    verify(mockMethodVisitor).visitInsn(Opcodes.ICONST_0)
    verify(mockMethodVisitor).visitInsn(Opcodes.ICONST_1)
    verify(mockMethodVisitor).visitLdcInsn(Type.getObjectType(Types.LIST))
    verify(mockMethodVisitor).visitLdcInsn(Type.getObjectType(Types.OBJECT))
    verify(mockMethodVisitor, times(2)).visitInsn(Opcodes.AASTORE)
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKESTATIC,
      Types.SERIALIZER,
      Methods.DESERIALIZE_TYPED,
      Descriptors.SERIALIZER_DESERIALIZE_TYPED,
      false
    )
  }
}