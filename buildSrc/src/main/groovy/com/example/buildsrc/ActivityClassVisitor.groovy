package com.example.buildsrc

import groovy.transform.TypeChecked
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

@TypeChecked
class ActivityClassVisitor extends ClassVisitor implements Opcodes {

  private boolean needTransform = false

  ActivityClassVisitor(ClassVisitor classVisitor) {
    super(Const.ASM_VERSION, classVisitor)
  }

  @Override
  void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
    println("Version=$version, Access=$access, Name=$name, Signature=$signature, Supername=$superName, Interfaces=$interfaces")
    needTransform = name == "com/example/stater/MainActivity2"
    super.visit(version, access, name, signature, superName, interfaces)
  }

  @Override
  MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions)
    println("Access=$access, Name=$name, Descriptor=$descriptor, Signature=$signature, exceptions=$exceptions")
    if (needTransform && name == "onCreate") {
      return new StaterOnCreateVisitor(mv)
    }
    if (needTransform && name == "onSaveInstanceState") {
      return new StaterOnSavedInstanceStateVisitor(mv)
    }
    return mv
  }

  @Override
  FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
    FieldVisitor fv = super.visitField(access, name, descriptor, signature, value)
    if (needTransform) {
      println("Access=$access, Name=$name, Descriptor=$descriptor, Signature=$signature, value=$value")
      return new StaterFieldVisitor(fv)
    }
    return fv
  }

}

@TypeChecked
class StaterOnCreateVisitor extends MethodVisitor {


  StaterOnCreateVisitor(MethodVisitor methodVisitor) {
    super(Const.ASM_VERSION, methodVisitor)
  }

  @Override
  void visitCode() {
    mv.visitCode()

    Label label = new Label()
    mv.visitVarInsn(Opcodes.ALOAD, 1)
    mv.visitJumpInsn(Opcodes.IFNULL, label)

    mv.visitVarInsn(Opcodes.ALOAD, 0)
    mv.visitVarInsn(Opcodes.ALOAD, 1)
    mv.visitLdcInsn("KEY")
    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/os/Bundle", "getInt", "(Ljava/lang/String;)I", false)
    mv.visitFieldInsn(Opcodes.PUTFIELD, "com/example/stater/MainActivity2", "aParam", "I")

    mv.visitLabel(label)
  }

}

@TypeChecked
class StaterOnSavedInstanceStateVisitor extends MethodVisitor {


  StaterOnSavedInstanceStateVisitor(MethodVisitor methodVisitor) {
    super(Const.ASM_VERSION, methodVisitor)
  }

  @Override
  void visitInsn(int opcode) {
    if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
      mv.visitVarInsn(Opcodes.ALOAD, 1)
      mv.visitLdcInsn("KEY")
      mv.visitVarInsn(Opcodes.ALOAD, 0)
      mv.visitFieldInsn(Opcodes.GETFIELD, "com/example/stater/MainActivity2", "aParam", "I")
      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/os/Bundle", "putInt", "(Ljava/lang/String;I)V", false)
    }
    super.visitInsn(opcode)
  }
}

@TypeChecked
class StaterFieldVisitor extends FieldVisitor {

  StaterFieldVisitor(FieldVisitor fieldVisitor) {
    super(Const.ASM_VERSION, fieldVisitor)
  }

  @Override
  AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
    println("descriptor=$descriptor, visible=$visible")
    AnnotationVisitor av = super.visitAnnotation(descriptor, visible)
    if (descriptor == "Lcom/example/stater/Stater;") {
      return new StaterAnnotationVisitor(av)
    }
    return av
  }
}

@TypeChecked
class StaterAnnotationVisitor extends AnnotationVisitor {

  StaterAnnotationVisitor(AnnotationVisitor annotationVisitor) {
    super(Const.ASM_VERSION, annotationVisitor)
  }

  @Override
  void visit(String name, Object value) {
    println("StaterAnnotationVisitor: name=$name, value=$value")
    super.visit(name, value)
  }

  @Override
  void visitEnum(String name, String descriptor, String value) {
    println("StaterAnnotationVisitor: name=$name, descriptor=$descriptor, value=$value")
    super.visitEnum(name, descriptor, value)
  }
}