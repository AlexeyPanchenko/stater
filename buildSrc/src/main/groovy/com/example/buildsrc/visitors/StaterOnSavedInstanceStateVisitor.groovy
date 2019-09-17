package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import com.example.buildsrc.Descriptors
import com.example.buildsrc.Methods
import com.example.buildsrc.StateType
import com.example.buildsrc.Store
import com.example.buildsrc.Types
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
      //todo: FixType
      mv.visitFieldInsn(Opcodes.GETFIELD, field.owner, field.name, field.descriptor)
      switch (field.type) {
        case StateType.BOOLEAN:
          mv.visitMethodInsn(
              Opcodes.INVOKEVIRTUAL,
              Types.BUNDLE,
              Methods.PUT_BOOLEAN,
              "(${Descriptors.STRING}${Descriptors.BOOLEAN})${Descriptors.VOID}",
              false
          )
          break
        case StateType.INT:
          mv.visitMethodInsn(
              Opcodes.INVOKEVIRTUAL,
              Types.BUNDLE,
              Methods.PUT_INT,
              "(${Descriptors.STRING}${Descriptors.INT})${Descriptors.VOID}",
              false
          )
          break
        case StateType.LONG:
          mv.visitMethodInsn(
              Opcodes.INVOKEVIRTUAL,
              Types.BUNDLE,
              Methods.PUT_LONG,
              "(${Descriptors.STRING}${Descriptors.LONG})${Descriptors.VOID}",
              false
          )
          break
        case StateType.STRING:
          mv.visitMethodInsn(
              Opcodes.INVOKEVIRTUAL,
              Types.BUNDLE,
              Methods.PUT_STRING,
              "(${Descriptors.STRING}${Descriptors.STRING})${Descriptors.VOID}",
              false
          )
          break
        case StateType.SERIALIZABLE:
          break
        case StateType.PARCELABLE:
          break
      }
    }
  }

}