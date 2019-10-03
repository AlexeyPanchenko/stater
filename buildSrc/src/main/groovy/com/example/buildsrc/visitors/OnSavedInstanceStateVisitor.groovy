package com.example.buildsrc.visitors

import groovy.transform.TypeChecked
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import com.example.buildsrc.model.MethodDescriptor
import com.example.buildsrc.model.StateType
import com.example.buildsrc.utils.Const
import com.example.buildsrc.utils.Descriptors
import com.example.buildsrc.utils.MethodDescriptorUtils
import com.example.buildsrc.utils.Types

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
      MethodDescriptor methodDescriptor = MethodDescriptorUtils.getDescriptorByType(field.type, false)
      if (methodDescriptor == null || !methodDescriptor.isValid()) {
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