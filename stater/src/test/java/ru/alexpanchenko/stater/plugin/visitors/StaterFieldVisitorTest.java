package ru.alexpanchenko.stater.plugin.visitors;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;

import ru.alexpanchenko.stater.plugin.model.SaverField;
import ru.alexpanchenko.stater.plugin.model.StateType;
import ru.alexpanchenko.stater.plugin.utils.Const;
import ru.alexpanchenko.stater.plugin.utils.Descriptors;
import ru.alexpanchenko.stater.plugin.utils.Types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class StaterFieldVisitorTest {

  @Before
  public void setUp() {
    Const.stateFields.clear();
  }

  @Test
  public void testStateFieldVisitor() {
    //final String name = "name";
    //final String descriptor = "descriptor";
    //final String owner = "owner";
    //FieldVisitor fieldVisitor = mock(FieldVisitor.class);
    //
    //StaterFieldVisitor visitor = new StaterFieldVisitor(fieldVisitor, name, descriptor, owner);
    //AnnotationVisitor annotationVisitor = visitor.visitAnnotation(Descriptors.STATE, true);
    //
    //assertEquals(annotationVisitor.getClass(), StateAnnotationVisitor.class);
    //
    //assertTrue(Const.stateFields.isEmpty());
    //
    //annotationVisitor.visitEnum(Types.STATE, Descriptors.STATE, StateType.BOOLEAN.toString());
    //
    //assertFalse(Const.stateFields.isEmpty());
    //assertEquals(Const.stateFields.size(), 1);
    //assertEquals(Const.stateFields.get(0), new SaverField(name, descriptor, owner, StateType.BOOLEAN));
  }
}
