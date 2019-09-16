package com.example.buildsrc;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM7;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.POP;

public class TestMethodVisitor extends MethodVisitor {

  public TestMethodVisitor(MethodVisitor methodVisitor) {
    super(ASM7, methodVisitor);
  }

  @Override
  public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
    System.out.println("== TestMethodVisitor, owner = " + owner + ", name = " + name);
    mv.visitLdcInsn(" before method exec");
    mv.visitLdcInsn(" [ASM] method in " + owner + " ,name=" + name);
    mv.visitMethodInsn(
      INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false
    );
    mv.visitInsn(POP);

    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);

    mv.visitLdcInsn(" after method exec");
    mv.visitLdcInsn(" method in " + owner + " ,name=" + name);
    mv.visitMethodInsn(
      INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false
    );
    mv.visitInsn(POP);
  }
}
