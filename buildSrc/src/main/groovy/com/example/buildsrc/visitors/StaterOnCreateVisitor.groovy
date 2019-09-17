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
class StaterOnCreateVisitor extends MethodVisitor {


  StaterOnCreateVisitor(MethodVisitor methodVisitor) {
    super(Const.ASM_VERSION, methodVisitor)
  }

  @Override
  void visitCode() {
    mv.visitCode()

    Label l1 = new Label()
    mv.visitVarInsn(Opcodes.ALOAD, 1)
    mv.visitJumpInsn(Opcodes.IFNULL, l1)

    Store.instance.fields.each { field ->
      println("FIELD =$field")
      Label label = new Label()
      mv.visitLabel(label)
      mv.visitVarInsn(Opcodes.ALOAD, 0)
      mv.visitVarInsn(Opcodes.ALOAD, 1)
      mv.visitLdcInsn(field.key)
      //todo: FixTypes
      switch (field.type) {
        case StateType.BOOLEAN:
          mv.visitMethodInsn(
              Opcodes.INVOKEVIRTUAL, Types.BUNDLE, Methods.GET_BOOLEAN, "(${Descriptors.STRING})${Descriptors.BOOLEAN}", false
          )
          break
        case StateType.INT:
          mv.visitMethodInsn(
              Opcodes.INVOKEVIRTUAL, Types.BUNDLE, Methods.GET_INT, "(${Descriptors.STRING})${Descriptors.INT}", false
          )
          break
        case StateType.LONG:
          mv.visitMethodInsn(
              Opcodes.INVOKEVIRTUAL, Types.BUNDLE, Methods.GET_LONG, "(${Descriptors.STRING})${Descriptors.LONG}", false
          )
          break
        case StateType.STRING:
          mv.visitMethodInsn(
              Opcodes.INVOKEVIRTUAL, Types.BUNDLE, Methods.GET_STRING, "(${Descriptors.STRING})${Descriptors.STRING}", false
          )
          break
        case StateType.SERIALIZABLE:
          break
        case StateType.PARCELABLE:
          break
      }
      mv.visitFieldInsn(Opcodes.PUTFIELD, field.owner, field.name, field.descriptor)
    }
    mv.visitLabel(l1)
  }

}
