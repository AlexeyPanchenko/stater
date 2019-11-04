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
import stater.org.objectweb.asm.Type;

import static org.mockito.Mockito.mock;
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
}
