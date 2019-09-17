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
  private boolean hasOnCreate = false
  private boolean hasSavedInstanceState = false

  ActivityClassVisitor(ClassVisitor classVisitor) {
    super(Const.ASM_VERSION, classVisitor)
  }

  @Override
  void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
    needTransform = name == "com/example/stater/MainActivity2"
    super.visit(version, access, name, signature, superName, interfaces)
  }

  @Override
  MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions)
    if (needTransform && name == Methods.ON_CREATE) {
      hasOnCreate = true
      return new StaterOnCreateVisitor(mv)
    }
    if (needTransform && name == Methods.ON_SAVED_INSTANCE_STATE) {
      hasSavedInstanceState = true
      return new StaterOnSavedInstanceStateVisitor(mv)
    }
    return mv
  }

  @Override
  FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
    FieldVisitor fv = super.visitField(access, name, descriptor, signature, value)
    if (needTransform) {
      return new StaterFieldVisitor(fv)
    }
    return fv
  }

  @Override
  void visitEnd() {
    if (needTransform && !hasOnCreate) {
      MethodVisitor methodVisitor = cv.visitMethod(
          Opcodes.ACC_PROTECTED,
          Methods.ON_CREATE,
          Descriptors.ON_CREATE,
          null,
          null
      )
      methodVisitor.visitCode()
      Label l0 = new Label()
      methodVisitor.visitLabel(l0)
      methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
      methodVisitor.visitVarInsn(Opcodes.ALOAD, 1)
      methodVisitor.visitMethodInsn(
          Opcodes.INVOKESPECIAL,
          "androidx/appcompat/app/AppCompatActivity",
          Methods.ON_CREATE,
          Descriptors.ON_CREATE,
          false
      )
      Label l1 = new Label()
      methodVisitor.visitLabel(l1)
      new StaterOnCreateVisitor(methodVisitor).visitCode()
      Label l2 = new Label()
      methodVisitor.visitLabel(l2)
      methodVisitor.visitInsn(Opcodes.RETURN)
      methodVisitor.visitMaxs(2, 2)
      methodVisitor.visitEnd()
    }
    if (needTransform && !hasSavedInstanceState) {
      MethodVisitor methodVisitor = cv.visitMethod(
          Opcodes.ACC_PROTECTED,
          Methods.ON_SAVED_INSTANCE_STATE,
          Descriptors.ON_SAVED_INSTANCE_STATE,
          null,
          null
      )
      methodVisitor.visitCode()
      Label l0 = new Label()
      methodVisitor.visitLabel(l0)
      methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
      methodVisitor.visitVarInsn(Opcodes.ALOAD, 1)
      methodVisitor.visitMethodInsn(
          Opcodes.INVOKESPECIAL,
          "androidx/appcompat/app/AppCompatActivity",
          Methods.ON_SAVED_INSTANCE_STATE,
          Descriptors.ON_SAVED_INSTANCE_STATE,
          false
      )
      Label l1 = new Label()
      methodVisitor.visitLabel(l1)
      new StaterOnSavedInstanceStateVisitor(methodVisitor).visitCode()
      Label l2 = new Label()
      methodVisitor.visitLabel(l2)
      methodVisitor.visitInsn(Opcodes.RETURN)
      methodVisitor.visitMaxs(2, 2)
      methodVisitor.visitEnd()
    }
    super.visitEnd()
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