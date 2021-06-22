package ru.alexpanchenko.stater.plugin.visitors;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import javassist.ClassPool;
import javassist.NotFoundException;
import ru.alexpanchenko.stater.plugin.StateFieldStorage;
import ru.alexpanchenko.stater.plugin.utils.Const;
import ru.alexpanchenko.stater.plugin.utils.Methods;
import ru.alexpanchenko.stater.plugin.utils.StateTypeDeterminator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StaterClassVisitorTest {

  private static final String ACTIVITY_CLASS = "android/app/Activity.class";
  private static final String ACTIVITY_NAME = ACTIVITY_CLASS.replace(".class", "");
  private static final String FRAGMENT_CLASS = "androidx/fragment/app/Fragment.class";
  private static final String FRAGMENT_NAME = FRAGMENT_CLASS.replace(".class", "");

  private final ClassPool classPool = ClassPool.getDefault();
  private final StateTypeDeterminator typeDeterminator = new StateTypeDeterminator(classPool, false);
  private final StateFieldStorage fieldStorage = new StateFieldStorage();

  @Before
  public void setUp() {
    fieldStorage.clear();
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

    StaterClassVisitor visitor = new StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage);
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
  public void testVisitActivityClass() throws NotFoundException {
    classPool.appendClassPath(ACTIVITY_CLASS);
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    StaterClassVisitor visitor = new StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage);
    visitor.visit(Const.ASM_VERSION, Opcodes.ACC_PUBLIC, "MyActivity", null, ACTIVITY_NAME, new String[]{});
    FieldVisitor fieldVisitor = visitor.visitField(Opcodes.ACC_PRIVATE, "name", "descriptor", null, null);
    assertEquals(fieldVisitor.getClass(), StaterFieldVisitor.class);
  }

  @Test
  public void testVisitFragmentClass() throws NotFoundException {
    classPool.appendClassPath(FRAGMENT_CLASS);
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    StaterClassVisitor visitor = new StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage);
    visitor.visit(Const.ASM_VERSION, Opcodes.ACC_PUBLIC, "MyFragment", null, FRAGMENT_NAME, new String[]{});
    FieldVisitor fieldVisitor = visitor.visitField(Opcodes.ACC_PRIVATE, "name", "descriptor", null, null);
    assertEquals(fieldVisitor.getClass(), StaterFieldVisitor.class);
  }

  @Test
  public void testOnCreateMethodVisitor() {
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    StaterClassVisitor visitor = new StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage);
    visitor.visit(Const.ASM_VERSION, Opcodes.ACC_PUBLIC, "MyActivity", null, ACTIVITY_NAME, new String[]{});

    MethodVisitor methodVisitor = visitor.visitMethod(
      Opcodes.ACC_PRIVATE, Methods.ON_CREATE, "descriptor", null, new String[]{}
    );

    assertEquals(methodVisitor.getClass(), OnCreateVisitor.class);
  }

  @Test
  public void testOnSaveInstanceStateMethodVisitor() {
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    StaterClassVisitor visitor = new StaterClassVisitor(classVisitor, classPool, typeDeterminator, fieldStorage);
    visitor.visit(Const.ASM_VERSION, Opcodes.ACC_PUBLIC, "MyActivity", null, ACTIVITY_NAME, new String[]{});

    MethodVisitor methodVisitor = visitor.visitMethod(
      Opcodes.ACC_PRIVATE, Methods.ON_SAVED_INSTANCE_STATE, "descriptor", null, new String[]{}
    );

    assertEquals(methodVisitor.getClass(), OnSavedInstanceStateVisitor.class);
  }
}
