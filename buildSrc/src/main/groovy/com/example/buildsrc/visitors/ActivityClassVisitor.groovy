package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import com.example.buildsrc.Descriptors
import com.example.buildsrc.Methods
import groovy.transform.TypeChecked
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes;

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
