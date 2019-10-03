package ru.alexpanchenko.stater.plugin.visitors;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.FieldVisitor;

import javassist.ClassPool;
import javassist.NotFoundException;
import ru.alexpanchenko.stater.plugin.model.StateType;
import ru.alexpanchenko.stater.plugin.utils.Const;
import ru.alexpanchenko.stater.plugin.utils.Descriptors;
import ru.alexpanchenko.stater.plugin.utils.StateTypeDeterminator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class StaterFieldVisitorTest {

  private static final String A_CLASS = "ru/alexpanchenko/stater/plugin/utils/models/A.class";
  private static final String B_CLASS = "ru/alexpanchenko/stater/plugin/utils/models/B.class";
  private static final String B_PACKAGE = B_CLASS.replace(".class", "").replace("/", ".");

  private final FieldVisitor fieldVisitor = mock(FieldVisitor.class);
  private String name = "name";
  private String descriptor = "descriptor";
  private String signature = "signature";
  private String owner = "owner";

  @Before
  public void setUp() {
    Const.stateFields.clear();
  }

  @Test
  public void testDeterminatorDeterminate() {
    StateTypeDeterminator typeDeterminator = mock(StateTypeDeterminator.class);

    StaterFieldVisitor visitor = new StaterFieldVisitor(
      fieldVisitor, name, descriptor, signature, owner, typeDeterminator
    );
    visitor.visitAnnotation(Descriptors.STATE, true);
    verify(typeDeterminator).determinate(descriptor, signature);
  }

  @Test
  public void testDeterminatorNotDeterminate() {
    StateTypeDeterminator typeDeterminator = mock(StateTypeDeterminator.class);

    StaterFieldVisitor visitor = new StaterFieldVisitor(
      fieldVisitor, name, descriptor, signature, owner, typeDeterminator
    );
    visitor.visitAnnotation(Descriptors.STATE + "a", true);
    verify(typeDeterminator, never()).determinate(descriptor, signature);
  }

  @Test(expected = IllegalStateException.class)
  public void testIncorrectType() {
    descriptor = "Lcom/example/Fake";
    signature = null;

    StaterFieldVisitor visitor = new StaterFieldVisitor(
      fieldVisitor, name, descriptor, signature, owner, new StateTypeDeterminator()
    );
    visitor.visitAnnotation(Descriptors.STATE, true);
  }

  @Test
  public void testBoolean() {
    descriptor = Descriptors.BOOLEAN;
    signature = null;

    StaterFieldVisitor visitor = new StaterFieldVisitor(
      fieldVisitor, name, descriptor, signature, owner, new StateTypeDeterminator()
    );
    visitor.visitAnnotation(Descriptors.STATE, true);

    assertFalse(Const.stateFields.isEmpty());
    assertEquals(Const.stateFields.size(), 1);
    assertEquals(Const.stateFields.get(0).type, StateType.BOOLEAN);
  }

  @Test
  public void testSerializable() throws NotFoundException {
    ClassPool.getDefault().appendClassPath(A_CLASS);
    ClassPool.getDefault().appendClassPath(B_CLASS);
    descriptor = "L" + B_PACKAGE + ";";
    signature = null;

    StaterFieldVisitor visitor = new StaterFieldVisitor(
      fieldVisitor, name, descriptor, signature, owner, new StateTypeDeterminator()
    );
    visitor.visitAnnotation(Descriptors.STATE, true);

    assertEquals(Const.stateFields.get(0).type, StateType.SERIALIZABLE);
  }

}
