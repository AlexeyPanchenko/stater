package ru.alexpanchenko.stater.plugin.visitors

import groovy.transform.TypeChecked
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import ru.alexpanchenko.stater.plugin.utils.Const
import ru.alexpanchenko.stater.plugin.utils.Descriptors
import ru.alexpanchenko.stater.plugin.utils.Store
import ru.alexpanchenko.stater.plugin.utils.Types
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.model.MethodDescriptor
import ru.alexpanchenko.stater.plugin.utils.MethodDescriptorUtils

@TypeChecked
class OnSavedInstanceStateVisitor extends MethodVisitor {

  OnSavedInstanceStateVisitor(MethodVisitor methodVisitor) {
    super(Const.ASM_VERSION, methodVisitor)
  }

  @Override
  void visitCode() {
    mv.visitCode()

    Const.stateFields.each { field ->
      Label label = new Label()
      mv.visitLabel(label)
      mv.visitVarInsn(Opcodes.ALOAD, 1)
      mv.visitLdcInsn(field.key)
      mv.visitVarInsn(Opcodes.ALOAD, 0)
      final StateType type = MethodDescriptorUtils.primitiveIsObject(field.descriptor)
          ? StateType.SERIALIZABLE : field.type
      MethodDescriptor methodDescriptor = MethodDescriptorUtils.getDescriptorByType(type, false)
      if (!methodDescriptor.isValid()) {
        throw new IllegalStateException("StateType for ${field.name} in ${field.owner} is unknown!")
      }
      mv.visitFieldInsn(Opcodes.GETFIELD, field.owner, field.name, field.descriptor)
      // cast List to ArrayList :)
      if (field.descriptor == Descriptors.LIST) {
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

}