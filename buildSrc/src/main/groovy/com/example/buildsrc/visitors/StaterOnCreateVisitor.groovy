package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import com.example.buildsrc.Descriptors
import com.example.buildsrc.StateType
import com.example.buildsrc.Store
import com.example.buildsrc.Types
import com.example.buildsrc.utils.MethodDescriptor
import com.example.buildsrc.utils.MethodDescriptorUtils
import groovy.transform.TypeChecked
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

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
      Label label = new Label()
      mv.visitLabel(label)
      mv.visitVarInsn(Opcodes.ALOAD, 0)
      mv.visitVarInsn(Opcodes.ALOAD, 1)
      mv.visitLdcInsn(field.key)

      final StateType type = MethodDescriptorUtils.primitiveIsObject(field.descriptor) ? StateType.SERIALIZABLE : field.type
      println("STATE=$type")

      MethodDescriptor methodDescriptor = MethodDescriptorUtils.getDescriptorByType(type, true)
      if (!methodDescriptor.isValid()) {
        throw new IllegalStateException("StateType for ${field.name} in ${field.owner} is unknown!")
      }
      mv.visitMethodInsn(
          Opcodes.INVOKEVIRTUAL,
          Types.BUNDLE,
          methodDescriptor.method,
          "(${Descriptors.STRING})${methodDescriptor.descriptor}",
          false
      )
      // cast
      if (type == StateType.SERIALIZABLE
          || type == StateType.PARCELABLE
          || type == StateType.PARCELABLE_ARRAY
          || type == StateType.INT_ARRAY_LIST
          || type == StateType.CHAR_SEQUENCE_ARRAY_LIST
          || type == StateType.PARCELABLE_ARRAY_LIST
      ) {
        mv.visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).internalName)
      }
      mv.visitFieldInsn(Opcodes.PUTFIELD, field.owner, field.name, field.descriptor)
    }
    mv.visitLabel(l1)
  }

}
