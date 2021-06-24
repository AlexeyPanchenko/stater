package ru.alexpanchenko.stater.plugin.visitors

import javassist.ClassPool
import org.objectweb.asm.*
import ru.alexpanchenko.stater.plugin.StateFieldStorage
import ru.alexpanchenko.stater.plugin.utils.*

class StaterClassVisitor(
  classVisitor: ClassVisitor,
  private val classPool: ClassPool,
  private val typeDeterminator: StateTypeDeterminator,
  private val fieldStorage: StateFieldStorage
) : ClassVisitor(Const.ASM_VERSION, classVisitor), Opcodes {

  private var needTransform = false
  private var hasOnCreate = false
  private var hasSavedInstanceState = false
  private var owner: String? = null
  private var superOwner: String? = null

  override fun visit(
    version: Int,
    access: Int,
    name: String?,
    signature: String?,
    superName: String?,
    interfaces: Array<out String>?
  ) {
    this.owner = name
    this.superOwner = superName
    needTransform = ClassHierarchyUtils.containsParent(
      classPool,
      superName,
      Packages.ACTIVITY,
      Packages.ACTIVITY_X_SUPPORT,
      Packages.FRAGMENT_X,
      Packages.FRAGMENT_SUPPORT,
      Packages.FRAGMENT
    )
    super.visit(version, access, name, signature, superName, interfaces)
  }

  override fun visitField(
    access: Int,
    name: String,
    descriptor: String,
    signature: String?,
    value: Any?
  ): FieldVisitor? {
    val fv: FieldVisitor? = super.visitField(access, name, descriptor, signature, value)
    if (needTransform) {
      return StaterFieldVisitor(fv, name, descriptor, signature, owner, typeDeterminator, fieldStorage)
    }
    return fv
  }

  override fun visitMethod(
    access: Int,
    name: String?,
    descriptor: String?,
    signature: String?,
    exceptions: Array<out String>?
  ): MethodVisitor? {
    val mv: MethodVisitor? = super.visitMethod(access, name, descriptor, signature, exceptions)
    if (needTransform && name == Methods.ON_CREATE) {
      hasOnCreate = true
      return OnCreateVisitor(mv, fieldStorage)
    }
    if (needTransform && name == Methods.ON_SAVED_INSTANCE_STATE) {
      hasSavedInstanceState = true
      return OnSavedInstanceStateVisitor(mv, fieldStorage)
    }
    return mv
  }

  override fun visitEnd() {
    if (needTransform && !hasOnCreate) {
      visitOnCreateMethod()
    }
    if (needTransform && !hasSavedInstanceState) {
      visitOnSaveInstanceStateMethod()
    }
    super.visitEnd()
    fieldStorage.clear()
  }

  private fun visitOnCreateMethod() {
    val methodVisitor: MethodVisitor = cv.visitMethod(
      Opcodes.ACC_PROTECTED, Methods.ON_CREATE, Descriptors.ON_CREATE, null, null
    )
    methodVisitor.visitCode()
    val l0 = Label()
    methodVisitor.visitLabel(l0)
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 1)
    methodVisitor.visitMethodInsn(
      Opcodes.INVOKESPECIAL, superOwner, Methods.ON_CREATE, Descriptors.ON_CREATE, false
    )
    val l1 = Label()
    methodVisitor.visitLabel(l1)
    OnCreateVisitor(methodVisitor, fieldStorage).visitCode()
    val l2 = Label()
    methodVisitor.visitLabel(l2)
    methodVisitor.visitInsn(Opcodes.RETURN)
    methodVisitor.visitMaxs(3, 1)
    methodVisitor.visitEnd()
  }

  private fun visitOnSaveInstanceStateMethod() {
    val methodVisitor: MethodVisitor = cv.visitMethod(
      Opcodes.ACC_PROTECTED,
    Methods.ON_SAVED_INSTANCE_STATE,
    Descriptors.ON_SAVED_INSTANCE_STATE,
    null,
    null
    )
    methodVisitor.visitCode()
    val l0 = Label()
    methodVisitor.visitLabel(l0)
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 1)
    methodVisitor.visitMethodInsn(
      Opcodes.INVOKESPECIAL, superOwner,
      Methods.ON_SAVED_INSTANCE_STATE,
      Descriptors.ON_SAVED_INSTANCE_STATE,
      false
    )
    val l1 = Label()
    methodVisitor.visitLabel(l1)
    OnSavedInstanceStateVisitor(methodVisitor, fieldStorage).visitCode()
    val l2 = Label()
    methodVisitor.visitLabel(l2)
    methodVisitor.visitInsn(Opcodes.RETURN)
    methodVisitor.visitMaxs(3, 1)
    methodVisitor.visitEnd()
  }
}