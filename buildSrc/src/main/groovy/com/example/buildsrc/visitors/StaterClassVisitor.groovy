package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import com.example.buildsrc.Descriptors
import com.example.buildsrc.Methods
import com.example.buildsrc.Store
import groovy.transform.TypeChecked
import org.objectweb.asm.*

@TypeChecked
class StaterClassVisitor extends ClassVisitor implements Opcodes {

  private boolean needTransform = false
  private boolean hasOnCreate = false
  private boolean hasSavedInstanceState = false
  private String owner
  private String superOwner

  StaterClassVisitor(ClassVisitor classVisitor) {
    super(Const.ASM_VERSION, classVisitor)
  }

  @Override
  void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
    println("version=$version, access=$access, name=$name, signature=$signature, superName=$superName, interfaces=$interfaces")
    this.owner = name
    this.superOwner = superName
    super.visit(version, access, name, signature, superName, interfaces)
  }

  @Override
  AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
    println("visitAnnotation: descriptor=$descriptor, visible=$visible")
    needTransform = descriptor == Descriptors.STATER
    return super.visitAnnotation(descriptor, visible)
  }

  @Override
  FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
    println("visitField: access=$access, name=$name, descriptor=$descriptor, signature=$signature, value=$value")
    FieldVisitor fv = super.visitField(access, name, descriptor, signature, value)
    if (needTransform) {
      return new StaterFieldVisitor(fv, name, descriptor, owner)
    }
    return fv
  }

  @Override
  MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
    println("visitMethod: access=$access, name=$name, descriptor=$descriptor, signature=$signature, exceptions=$exceptions")
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
  void visitEnd() {
    if (needTransform && !hasOnCreate) {
      MethodVisitor methodVisitor = cv.visitMethod(
          Opcodes.ACC_PROTECTED, Methods.ON_CREATE, Descriptors.ON_CREATE, null, null
      )
      methodVisitor.visitCode()
      Label l0 = new Label()
      methodVisitor.visitLabel(l0)
      methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
      methodVisitor.visitVarInsn(Opcodes.ALOAD, 1)
      methodVisitor.visitMethodInsn(
          Opcodes.INVOKESPECIAL, superOwner, Methods.ON_CREATE, Descriptors.ON_CREATE, false
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
          Opcodes.INVOKESPECIAL, superOwner,
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
    Store.instance.fields.clear()
  }
}
