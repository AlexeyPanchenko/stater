package ru.alexpanchenko.stater.plugin.visitors;

import org.junit.Before;
import org.junit.Test;

import ru.alexpanchenko.stater.plugin.StateFieldStorage;
import ru.alexpanchenko.stater.plugin.model.SaverField;
import ru.alexpanchenko.stater.plugin.model.StateType;
import ru.alexpanchenko.stater.plugin.utils.Descriptors;
import ru.alexpanchenko.stater.plugin.utils.Methods;
import ru.alexpanchenko.stater.plugin.utils.Types;
import stater.org.objectweb.asm.MethodVisitor;
import stater.org.objectweb.asm.Opcodes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OnSavedInstanceStateVisitorTest {

  private final StateFieldStorage fieldStorage = new StateFieldStorage();

  @Before
  public void setUp() {
    fieldStorage.clear();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalType() {
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    fieldStorage.add(new SaverField("n", "d", "sign", "o", null));

    OnSavedInstanceStateVisitor visitor = new OnSavedInstanceStateVisitor(mockMethodVisitor, fieldStorage);
    visitor.visitCode();
  }

  @Test
  public void testBaseFlow() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.INT;
    String fieldOwner = Descriptors.OBJECT;
    StateType fieldType = StateType.INT;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType);
    fieldStorage.add(field);

    OnSavedInstanceStateVisitor visitor = new OnSavedInstanceStateVisitor(mockMethodVisitor, fieldStorage);
    visitor.visitCode();

    verify(mockMethodVisitor).visitCode();
    verify(mockMethodVisitor).visitVarInsn(Opcodes.ALOAD, 1);
    verify(mockMethodVisitor).visitLdcInsn(field.key);
    verify(mockMethodVisitor).visitVarInsn(Opcodes.ALOAD, 0);
    verify(mockMethodVisitor).visitFieldInsn(Opcodes.GETFIELD, field.owner, field.name, field.descriptor);
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Put.INT,
      "(" + Descriptors.STRING + Descriptors.INT + ")" + Descriptors.VOID,
      false
    );
  }

  @Test
  public void testPutString() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.STRING;
    String fieldOwner = Descriptors.OBJECT;
    StateType fieldType = StateType.STRING;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType);
    fieldStorage.add(field);

    OnSavedInstanceStateVisitor visitor = new OnSavedInstanceStateVisitor(mockMethodVisitor, fieldStorage);
    visitor.visitCode();

    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Put.STRING,
      "(" + Descriptors.STRING + Descriptors.STRING + ")" + Descriptors.VOID,
      false
    );
  }

  @Test
  public void testCastListToArrayList() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.LIST;
    String fieldOwner = Descriptors.OBJECT;
    StateType fieldType = StateType.INT_ARRAY_LIST;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType);
    fieldStorage.add(field);

    OnSavedInstanceStateVisitor visitor = new OnSavedInstanceStateVisitor(mockMethodVisitor, fieldStorage);
    visitor.visitCode();

    verify(mockMethodVisitor).visitTypeInsn(Opcodes.CHECKCAST, Types.ARRAY_LIST);
    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      Types.BUNDLE,
      Methods.Put.INT_ARRAY_LIST,
      "(" + Descriptors.STRING + Descriptors.ARRAY_LIST + ")" + Descriptors.VOID,
      false
    );
  }

  @Test
  public void testSerializeCustomType() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.OBJECT;
    String fieldOwner = Descriptors.OBJECT;
    StateType fieldType = StateType.CUSTOM;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, null, fieldOwner, fieldType);
    fieldStorage.add(field);

    OnSavedInstanceStateVisitor visitor = new OnSavedInstanceStateVisitor(mockMethodVisitor, fieldStorage);
    visitor.visitCode();

    verify(mockMethodVisitor).visitMethodInsn(
      Opcodes.INVOKESTATIC,
      Types.SERIALIZER,
      Methods.SERIALIZE,
      Descriptors.SERIALIZER_SERIALIZE,
      false
    );
  }

}
