package ru.alexpanchenko.stater.plugin.visitors

import groovy.transform.TypeChecked
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import ru.alexpanchenko.stater.plugin.utils.Const
import ru.alexpanchenko.stater.plugin.utils.Descriptors
import ru.alexpanchenko.stater.plugin.utils.Store
import ru.alexpanchenko.stater.plugin.utils.Types
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.model.MethodDescriptor
import ru.alexpanchenko.stater.plugin.utils.MethodDescriptorUtils

@TypeChecked
class OnCreateVisitor extends MethodVisitor {

  OnCreateVisitor(MethodVisitor methodVisitor) {
    super(Const.ASM_VERSION, methodVisitor)
  }

  @Override
  void visitCode() {
    mv.visitCode()

    Label l1 = new Label()
    mv.visitVarInsn(Opcodes.ALOAD, 1)
    mv.visitJumpInsn(Opcodes.IFNULL, l1)

    Const.stateFields.each { field ->
      Label label = new Label()
      mv.visitLabel(label)
      mv.visitVarInsn(Opcodes.ALOAD, 0)
      mv.visitVarInsn(Opcodes.ALOAD, 1)
      mv.visitLdcInsn(field.key)

      final StateType type = MethodDescriptorUtils.primitiveIsObject(field.descriptor) ? StateType.SERIALIZABLE : field.type
      MethodDescriptor methodDescriptor = MethodDescriptorUtils.getDescriptorByType(type, true)
      if (!methodDescriptor.isValid()) {
        throw new IllegalStateException("StateType for ${field.name} in ${field.owner} is unknown!")
      }
      mv.visitMethodInsn(
          Opcodes.INVOKEVIRTUAL,
          Types.BUNDLE,
          methodDescriptor.method,
          "(${Descriptors.STRING})${methodDescriptor.descriptor}",
          false
      )
      // cast
      if (type == StateType.SERIALIZABLE
          || type == StateType.PARCELABLE
          || type == StateType.PARCELABLE_ARRAY
          || type == StateType.IBINDER
      ) {
        mv.visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).internalName)
      }
      mv.visitFieldInsn(Opcodes.PUTFIELD, field.owner, field.name, field.descriptor)
    }
    mv.visitLabel(l1)
  }

}
