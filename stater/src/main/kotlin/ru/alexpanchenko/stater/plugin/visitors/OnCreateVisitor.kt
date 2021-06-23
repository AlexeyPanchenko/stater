package ru.alexpanchenko.stater.plugin.visitors

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import ru.alexpanchenko.stater.plugin.StateFieldStorage
import ru.alexpanchenko.stater.plugin.model.MethodDescriptor
import ru.alexpanchenko.stater.plugin.model.SaverField
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.*

class OnCreateVisitor(
  methodVisitor: MethodVisitor?,
  private val fieldStorage: StateFieldStorage,
) : MethodVisitor(Const.ASM_VERSION, methodVisitor) {


  override fun visitCode() {
    mv.visitCode()

    val l1 = Label()
    mv.visitVarInsn(Opcodes.ALOAD, 1)
    mv.visitJumpInsn(Opcodes.IFNULL, l1)

    fieldStorage.fields.forEach { field ->
      val methodDescriptor: MethodDescriptor = MethodDescriptorUtils.getDescriptorByType(field.type, true)
      if (!methodDescriptor.isValid()) {
        throw IllegalStateException("StateType for ${field.name} in ${field.owner} is unknown!")
      }
      mv.visitVarInsn(Opcodes.ALOAD, 0)
      mv.visitVarInsn(Opcodes.ALOAD, 1)
      mv.visitLdcInsn(field.key)
      mv.visitMethodInsn(
        Opcodes.INVOKEVIRTUAL,
        Types.BUNDLE,
        methodDescriptor.method,
        "(${Descriptors.STRING})${methodDescriptor.descriptor}",
        false
      )
      // cast
      if (needToCastField(field)) {
        mv.visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).internalName)
      }

      if (field.type == StateType.CUSTOM) {
        addCustomTypeDeserialization(field)
      }
      mv.visitFieldInsn(Opcodes.PUTFIELD, field.owner, field.name, field.descriptor)
    }
    mv.visitLabel(l1)
  }

  private fun needToCastField(field: SaverField): Boolean {
    return field.type == StateType.SERIALIZABLE ||
      field.type == StateType.PARCELABLE ||
      field.type == StateType.PARCELABLE_ARRAY ||
      field.type == StateType.IBINDER
  }

  private fun addCustomTypeDeserialization(field: SaverField) {
    if (field.signature.isNullOrEmpty()) {
      mv.visitLdcInsn(Type.getType(field.descriptor))
      mv.visitMethodInsn(
        Opcodes.INVOKESTATIC,
        Types.SERIALIZER,
        Methods.DESERIALIZE,
        Descriptors.SERIALIZER_DESERIALIZE,
        false
      )
    } else {
      addTypedDeserialization(field.signature)
    }
    mv.visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).internalName)
  }

  @Throws(IllegalStateException::class)
  private fun addTypedDeserialization(signature: String) {
    val types: List<String> = MethodDescriptorUtils.getSignatureTypes(signature)
    val size: Int = types.size
    addIntConstToStack(size)
    mv.visitTypeInsn(Opcodes.ANEWARRAY, Types.CLASS)
    types.forEachIndexed { i, type ->
      mv.visitInsn(Opcodes.DUP)
      addIntConstToStack(i)
      mv.visitLdcInsn(Type.getObjectType(type))
      mv.visitInsn(Opcodes.AASTORE)
    }
    mv.visitMethodInsn(
      Opcodes.INVOKESTATIC,
      Types.SERIALIZER,
      Methods.DESERIALIZE_TYPED,
      Descriptors.SERIALIZER_DESERIALIZE_TYPED,
      false
    )
  }

  @Throws(IllegalStateException::class)
  private fun addIntConstToStack(value: Int) {
    when {
      value == 0 -> mv.visitInsn(Opcodes.ICONST_0)
      value == 1 -> mv.visitInsn(Opcodes.ICONST_1)
      value == 2 -> mv.visitInsn(Opcodes.ICONST_2)
      value == 3 -> mv.visitInsn(Opcodes.ICONST_3)
      value == 4 -> mv.visitInsn(Opcodes.ICONST_4)
      value == 5 -> mv.visitInsn(Opcodes.ICONST_5)
      value <= 127 -> mv.visitIntInsn(Opcodes.BIPUSH, value)
      else -> {
        throw IllegalStateException("That is Typed hell! Your typed object has more than 127 signatures!")
      }
    }
  }

}