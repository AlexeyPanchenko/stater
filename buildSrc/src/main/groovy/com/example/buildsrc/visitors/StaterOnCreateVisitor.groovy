package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import com.example.buildsrc.Descriptors
import com.example.buildsrc.Methods
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

    mv.visitVarInsn(Opcodes.ALOAD, 0)
    mv.visitVarInsn(Opcodes.ALOAD, 1)
    mv.visitLdcInsn("KEY")
    mv.visitMethodInsn(
        Opcodes.INVOKEVIRTUAL, Types.BUNDLE, Methods.GET_INT, "(${Descriptors.STRING})${Types.INT}", false
    )
    mv.visitFieldInsn(Opcodes.PUTFIELD, "com/example/stater/MainActivity2", "aParam", Types.INT)

    mv.visitLabel(l1)
  }

}
