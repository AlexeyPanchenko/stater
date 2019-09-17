package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import com.example.buildsrc.Descriptors
import com.example.buildsrc.Methods
import com.example.buildsrc.Types
import groovy.transform.TypeChecked
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
    mv.visitVarInsn(Opcodes.ALOAD, 1)
    mv.visitLdcInsn("KEY")
    mv.visitVarInsn(Opcodes.ALOAD, 0)
    mv.visitFieldInsn(Opcodes.GETFIELD, "com/example/stater/MainActivity2", "aParam", Types.INT)
    mv.visitMethodInsn(
        Opcodes.INVOKEVIRTUAL,
        Types.BUNDLE,
        Methods.PUT_INT,
        "(${Descriptors.STRING}${Types.INT})${Descriptors.VOID}",
        false
    )
  }

}