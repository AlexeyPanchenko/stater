package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import com.example.buildsrc.Descriptors
import com.example.buildsrc.Store
import com.example.buildsrc.Types
import com.example.buildsrc.utils.MethodDescriptor
import com.example.buildsrc.utils.MethodDescriptorUtils
import groovy.transform.TypeChecked
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

@TypeChecked
class StaterOnSavedInstanceStateVisitor extends MethodVisitor {

  StaterOnSavedInstanceStateVisitor(MethodVisitor methodVisitor) {
    super(Const.ASM_VERSION, methodVisitor)
  }

  @Override
  void visitCode() {
    mv.visitCode()

    Store.instance.fields.each { field ->
      Label label = new Label()
      mv.visitLabel(label)
      mv.visitVarInsn(Opcodes.ALOAD, 1)
      mv.visitLdcInsn(field.key)
      mv.visitVarInsn(Opcodes.ALOAD, 0)
      MethodDescriptor methodDescriptor = MethodDescriptorUtils.getDescriptorByType(field.type, false)
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