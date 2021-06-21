package ru.alexpanchenko.stater.plugin.visitors

import com.android.annotations.NonNull
import groovy.transform.TypeChecked
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import ru.alexpanchenko.stater.plugin.StateFieldStorage
import ru.alexpanchenko.stater.plugin.model.MethodDescriptor
import ru.alexpanchenko.stater.plugin.model.SaverField
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.*

@TypeChecked
class OnCreateVisitor extends MethodVisitor {

  private final StateFieldStorage fieldStorage

  OnCreateVisitor(@NonNull MethodVisitor methodVisitor, @NonNull StateFieldStorage fieldStorage) {
    super(Const.ASM_VERSION, methodVisitor)
    this.fieldStorage = fieldStorage
  }

  @Override
  void visitCode() {
    mv.visitCode()

    Label l1 = new Label()
    mv.visitVarInsn(Opcodes.ALOAD, 1)
    mv.visitJumpInsn(Opcodes.IFNULL, l1)

    fieldStorage.getFields().each { field ->
      MethodDescriptor methodDescriptor = MethodDescriptorUtils.getDescriptorByType(field.type, true)
      if (methodDescriptor == null || !methodDescriptor.isValid()) {
        throw new IllegalStateException("StateType for ${field.name} in ${field.owner} is unknown!")
      }
      mv.visitVarInsn(Opcodes.ALOAD, 0)
      mv.visitVarInsn(Opcodes.ALOAD, 1)
      mv.visitLdcInsn(field.key)
      mv.visitMethodInsn(
          Opcodes.INVOKEVIRTUAL,
          Types.BUNDLE,
          methodDescriptor.method,
          "(${Descriptors.STRING})${methodDescriptor.descriptor}",
          false
      )
      // cast
      if (needToCastField(field)) {
        mv.visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).internalName)
      }

      if (field.type == StateType.CUSTOM) {
        addCustomTypeDeserialization(field)
      }
      mv.visitFieldInsn(Opcodes.PUTFIELD, field.owner, field.name, field.descriptor)
    }
    mv.visitLabel(l1)
  }

  private boolean needToCastField(SaverField field) {
   return field.type == StateType.SERIALIZABLE ||
       field.type == StateType.PARCELABLE ||
       field.type == StateType.PARCELABLE_ARRAY ||
       field.type == StateType.IBINDER
  }

  private void addCustomTypeDeserialization(@NonNull SaverField field) {
    if (field.signature == null || field.signature.isEmpty()) {
      mv.visitLdcInsn(Type.getType(field.descriptor))
      mv.visitMethodInsn(
          Opcodes.INVOKESTATIC,
          Types.SERIALIZER,
          Methods.DESERIALIZE,
          Descriptors.SERIALIZER_DESERIALIZE,
          false
      )
    } else {
      addTypedDeserialization(field)
    }
    mv.visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).internalName)
  }

  private void addTypedDeserialization(@NonNull SaverField field) throws IllegalStateException {
    final List<String> types = MethodDescriptorUtils.getSignatureTypes(field.signature)
    final int size = types.size()
    addIntConstToStack(size)
    mv.visitTypeInsn(Opcodes.ANEWARRAY, Types.CLASS)
    types.eachWithIndex { String type, int i ->
      mv.visitInsn(Opcodes.DUP)
      addIntConstToStack(i)
      mv.visitLdcInsn(Type.getObjectType(type))
      mv.visitInsn(Opcodes.AASTORE)
    }
    mv.visitMethodInsn(
        Opcodes.INVOKESTATIC,
        Types.SERIALIZER,
        Methods.DESERIALIZE_TYPED,
        Descriptors.SERIALIZER_DESERIALIZE_TYPED,
        false
    )
  }

  private void addIntConstToStack(int value) throws IllegalStateException {
    if (value == 0) {
      mv.visitInsn(Opcodes.ICONST_0)
    } else if (value == 1) {
      mv.visitInsn(Opcodes.ICONST_1)
    } else if (value == 2) {
      mv.visitInsn(Opcodes.ICONST_2)
    } else if (value == 3) {
      mv.visitInsn(Opcodes.ICONST_3)
    } else if (value == 4) {
      mv.visitInsn(Opcodes.ICONST_4)
    } else if (value == 5) {
      mv.visitInsn(Opcodes.ICONST_5)
    } else if (value <= 127) {
      mv.visitIntInsn(Opcodes.BIPUSH, value)
    } else {
      throw new IllegalStateException("That is Typed hell! Your typed object has more than 127 signatures!")
    }
  }

}
