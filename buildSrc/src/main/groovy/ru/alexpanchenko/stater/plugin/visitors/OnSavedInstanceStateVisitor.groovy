package ru.alexpanchenko.stater.plugin.visitors

import groovy.transform.TypeChecked
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import ru.alexpanchenko.stater.plugin.model.MethodDescriptor
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.*

@TypeChecked
class OnSavedInstanceStateVisitor extends MethodVisitor {

  OnSavedInstanceStateVisitor(MethodVisitor methodVisitor) {
    super(Const.ASM_VERSION, methodVisitor)
  }

  @Override
  void visitCode() {
    mv.visitCode()

    Const.stateFields.each { field ->
      MethodDescriptor methodDescriptor = MethodDescriptorUtils.getDescriptorByType(field.type, false)
      if (methodDescriptor == null || !methodDescriptor.isValid()) {
        throw new IllegalStateException("StateType for ${field.name} in ${field.owner} is unknown!")
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

  private addCustomTypeSerialization() {
    mv.visitMethodInsn(
        Opcodes.INVOKESTATIC,
        Types.SERIALIZER,
        Methods.SERIALIZE,
        Descriptors.SERIALIZER_SERIALIZE,
        false
    )
  }

}