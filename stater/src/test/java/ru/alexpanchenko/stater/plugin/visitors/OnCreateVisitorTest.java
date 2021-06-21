package ru.alexpanchenko.stater.plugin.visitors;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import ru.alexpanchenko.stater.plugin.model.SaverField;
import ru.alexpanchenko.stater.plugin.model.StateType;
import ru.alexpanchenko.stater.plugin.utils.Const;
import ru.alexpanchenko.stater.plugin.utils.Descriptors;
import ru.alexpanchenko.stater.plugin.utils.Methods;
import ru.alexpanchenko.stater.plugin.utils.Types;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OnCreateVisitorTest {

  @Before
  public void setUp() {
    Const.stateFields.clear();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalType() {
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    Const.stateFields.add(new SaverField("n", "d", "o", null));

    OnCreateVisitor onCreateVisitor = new OnCreateVisitor(mockMethodVisitor);
    onCreateVisitor.visitCode();
  }

  @Test
  public void testGetInt() {
    String fieldName = "field1";
    String fieldDescriptor = Descriptors.INT;
    String fieldOwner = Descriptors.INT;
    StateType fieldType = StateType.INT;
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    SaverField field = new SaverField(fieldName, fieldDescriptor, fieldOwner, fieldType);
    Const.stateFields.add(field);

    OnCreateVisitor onCreateVisitor = new OnCreateVisitor(mockMethodVisitor);
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
    SaverField field = new SaverField(fieldName, fieldDescriptor, fieldOwner, fieldType);
    Const.stateFields.add(field);

    OnCreateVisitor onCreateVisitor = new OnCreateVisitor(mockMethodVisitor);
    onCreateVisitor.visitCode();

    verify(mockMethodVisitor).visitTypeInsn(Opcodes.CHECKCAST, Type.getType(field.descriptor).getInternalName());
  }
}
