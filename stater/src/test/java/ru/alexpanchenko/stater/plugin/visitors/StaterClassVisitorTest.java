package ru.alexpanchenko.stater.plugin.visitors;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import ru.alexpanchenko.stater.plugin.utils.Const;
import ru.alexpanchenko.stater.plugin.utils.Descriptors;
import ru.alexpanchenko.stater.plugin.utils.Methods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StaterClassVisitorTest {

  @Before
  public void setUp() {
    Const.stateFields.clear();
  }

  @Test
  public void testNotNeedTransform() {
    String name = "name";
    String descriptor = "descriptor";
    String signature = "signature";
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    FieldVisitor mockFieldVisitor = mock(FieldVisitor.class);
    MethodVisitor mockMethodVisitor = mock(MethodVisitor.class);
    when(classVisitor.visitField(Opcodes.ACC_PRIVATE, name, descriptor, signature, null)).thenReturn(mockFieldVisitor);
    when(classVisitor.visitMethod(Opcodes.ACC_PROTECTED, name, descriptor, signature, null)).thenReturn(mockMethodVisitor);

    StaterClassVisitor visitor = new StaterClassVisitor(classVisitor);
    visitor.visitAnnotation("", true);
    FieldVisitor fieldVisitor = visitor.visitField(Opcodes.ACC_PRIVATE, name, descriptor, signature, null);

    assertNotEquals(fieldVisitor.getClass(), StaterFieldVisitor.class);

    MethodVisitor methodVisitor = visitor.visitMethod(
      Opcodes.ACC_PROTECTED, name, descriptor, signature, null
    );
    assertNotEquals(methodVisitor.getClass(), OnCreateVisitor.class);
    assertNotEquals(methodVisitor.getClass(), OnSavedInstanceStateVisitor.class);
  }

  @Test
  public void testStaterFieldVisitor() {
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    StaterClassVisitor visitor = new StaterClassVisitor(classVisitor);
    visitor.visitAnnotation(Descriptors.STATER, true);
    FieldVisitor fieldVisitor = visitor.visitField(Opcodes.ACC_PRIVATE, "name", "descriptor", null, null);

    assertEquals(fieldVisitor.getClass(), StaterFieldVisitor.class);
  }

  @Test
  public void testOnCreateMethodVisitor() {
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    StaterClassVisitor visitor = new StaterClassVisitor(classVisitor);
    visitor.visitAnnotation(Descriptors.STATER, true);
    MethodVisitor methodVisitor = visitor.visitMethod(
      Opcodes.ACC_PRIVATE, Methods.ON_CREATE, "descriptor", null, null
    );

    assertEquals(methodVisitor.getClass(), OnCreateVisitor.class);
  }

  @Test
  public void testOnSaveInstanceStateMethodVisitor() {
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    StaterClassVisitor visitor = new StaterClassVisitor(classVisitor);
    visitor.visitAnnotation(Descriptors.STATER, true);
    MethodVisitor methodVisitor = visitor.visitMethod(
      Opcodes.ACC_PRIVATE, Methods.ON_SAVED_INSTANCE_STATE, "descriptor", null, null
    );

    assertEquals(methodVisitor.getClass(), OnSavedInstanceStateVisitor.class);
  }
}
