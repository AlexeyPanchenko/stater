package ru.alexpanchenko.stater.plugin.visitors;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import ru.alexpanchenko.stater.plugin.StateFieldStorage;
import ru.alexpanchenko.stater.plugin.model.SaverField;
import ru.alexpanchenko.stater.plugin.model.StateType;
import ru.alexpanchenko.stater.plugin.utils.Descriptors;
import ru.alexpanchenko.stater.plugin.utils.Methods;
import ru.alexpanchenko.stater.plugin.utils.Types;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OnCreateVisitorTest {

  private final StateFieldStorage fieldStorage = new StateFieldStorage();

  @Before
  public void setUp() {
    fieldStorage.clear();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalType() {
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    fieldStorage.add(new SaverField("n", "d", "sign", "o", null));

    OnCreateVisitor onCreateVisitor = new OnCreateVisitor(mockMethodVisitor, fieldStorage);
    onCreateVisitor.visitCode();
  }

  @Test
  public void testBaseFlow() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.INT;
    String fieldOwner = Descriptors.INT;
    StateType fieldType = StateType.INT;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType);
    fieldStorage.add(field);

    OnCreateVisitor onCreateVisitor = new OnCreateVisitor(mockMethodVisitor, fieldStorage);
    onCreateVisitor.visitCode();

    verify(mockMethodVisitor).visitCode();
    verify(mockMethodVisitor, times(2)).visitVarInsn(Opcodes.ALOAD, 1);
    verify(mockMethodVisitor).visitJumpInsn(eq(Opcodes.IFNULL), any(Label.class));
    verify(mockMethodVisitor).visitVarInsn(Opcodes.ALOAD, 0);
    verify(mockMethodVisitor).visitLdcInsn(field.key);
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Get.INT,
      "(" + Descriptors.STRING + ")" + Descriptors.INT,
      false
    );
    verify(mockMethodVisitor).visitFieldInsn(Opcodes.PUTFIELD, field.owner, field.name, field.descriptor);
  }

  @Test
  public void testGetInt() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.INT;
    String fieldOwner = Descriptors.INT;
    StateType fieldType = StateType.INT;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType);
    fieldStorage.add(field);

    OnCreateVisitor onCreateVisitor = new OnCreateVisitor(mockMethodVisitor, fieldStorage);
    onCreateVisitor.visitCode();

    verify(mockMethodVisitor).visitCode();
    verify(mockMethodVisitor).visitLdcInsn(field.key);
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Get.INT,
      "(" + Descriptors.STRING + ")" + Descriptors.INT,
      false
    );
    verify(mockMethodVisitor).visitFieldInsn(Opcodes.PUTFIELD, field.owner, field.name, field.descriptor);
  }

  @Test
  public void testSerializableCast() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.INT;
    String fieldOwner = Descriptors.SERIALIZABLE;
    StateType fieldType = StateType.SERIALIZABLE;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType);
    fieldStorage.add(field);

    OnCreateVisitor onCreateVisitor = new OnCreateVisitor(mockMethodVisitor, fieldStorage);
    onCreateVisitor.visitCode();

    verify(mockMethodVisitor).visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).getInternalName());
  }

  @Test
  public void testCustomField() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.OBJECT;
    String fieldOwner = Descriptors.OBJECT;
    StateType fieldType = StateType.CUSTOM;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType);
    fieldStorage.add(field);

    OnCreateVisitor onCreateVisitor = new OnCreateVisitor(mockMethodVisitor, fieldStorage);
    onCreateVisitor.visitCode();

    verify(mockMethodVisitor).visitLdcInsn(Type.getType(field.descriptor));
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKESTATIC,
      Types.SERIALIZER,
      Methods.DESERIALIZE,
      Descriptors.SERIALIZER_DESERIALIZE,
      false
    );
    verify(mockMethodVisitor).visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).getInternalName());
  }

  @Test
  public void testCustomTypedField() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.LIST;
    String fieldSignature = "L" + Types.LIST + "<" + Descriptors.OBJECT + ">;";
    String fieldOwner = Descriptors.OBJECT;
    StateType fieldType = StateType.CUSTOM;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, fieldSignature, fieldOwner, fieldType);
    fieldStorage.add(field);

    OnCreateVisitor onCreateVisitor = new OnCreateVisitor(mockMethodVisitor, fieldStorage);
    onCreateVisitor.visitCode();

    verify(mockMethodVisitor).visitInsn(Opcodes.ICONST_2);
    verify(mockMethodVisitor).visitTypeInsn(Opcodes.ANEWARRAY, Types.CLASS);
    verify(mockMethodVisitor, times(2)).visitInsn(Opcodes.DUP);
    verify(mockMethodVisitor).visitInsn(Opcodes.ICONST_0);
    verify(mockMethodVisitor).visitInsn(Opcodes.ICONST_1);
    verify(mockMethodVisitor).visitLdcInsn(Type.getObjectType(Types.LIST));
    verify(mockMethodVisitor).visitLdcInsn(Type.getObjectType(Types.OBJECT));
    verify(mockMethodVisitor, times(2)).visitInsn(Opcodes.AASTORE);
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKESTATIC,
      Types.SERIALIZER,
      Methods.DESERIALIZE_TYPED,
      Descriptors.SERIALIZER_DESERIALIZE_TYPED,
      false
    );
  }
}
