package ru.alexpanchenko.stater.plugin.visitors

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import ru.alexpanchenko.stater.plugin.StateFieldStorage
import ru.alexpanchenko.stater.plugin.model.MethodDescriptor
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.*

class OnSavedInstanceStateVisitor(
  methodVisitor: MethodVisitor?,
  private val fieldStorage: StateFieldStorage,
) : MethodVisitor(Const.ASM_VERSION, methodVisitor) {

  override fun visitCode() {
    mv.visitCode()

    fieldStorage.fields.forEach { field ->
      val methodDescriptor: MethodDescriptor = MethodDescriptorUtils.getDescriptorByType(field.type, false)
      if (!methodDescriptor.isValid()) {
        throw IllegalStateException("StateType for ${field.name} in ${field.owner} is unknown!")
      }
      mv.visitVarInsn(Opcodes.ALOAD, 1)
      mv.visitLdcInsn(field.key)
      mv.visitVarInsn(Opcodes.ALOAD, 0)
      mv.visitFieldInsn(Opcodes.GETFIELD, field.owner, field.name, field.descriptor)
      if (field.type == StateType.CUSTOM) {
        addCustomTypeSerialization()
      } else if (field.descriptor == Descriptors.LIST) {
        // cast List to ArrayList :)
        mv.visitTypeInsn(Opcodes.CHECKCAST, Types.ARRAY_LIST)
      }
      mv.visitMethodInsn(
        Opcodes.INVOKEVIRTUAL,
        Types.BUNDLE,
        methodDescriptor.method,
        "(${Descriptors.STRING}${methodDescriptor.descriptor})${Descriptors.VOID}",
        false
      )
    }
  }

  private fun addCustomTypeSerialization() {
    mv.visitMethodInsn(
      Opcodes.INVOKESTATIC,
      Types.SERIALIZER,
      Methods.SERIALIZE,
      Descriptors.SERIALIZER_SERIALIZE,
      false
    )
  }
}