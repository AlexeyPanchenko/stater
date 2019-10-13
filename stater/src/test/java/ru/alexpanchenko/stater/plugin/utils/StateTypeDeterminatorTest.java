package ru.alexpanchenko.stater.plugin.utils;

import org.junit.Test;

import javassist.ClassPool;
import javassist.NotFoundException;
import ru.alexpanchenko.stater.plugin.model.StateType;

import static org.junit.Assert.assertEquals;

public class StateTypeDeterminatorTest {

  private final ClassPool classPool = ClassPool.getDefault();
  private static final String A_CLASS = "ru/alexpanchenko/stater/plugin/utils/models/A.class";
  private static final String B_CLASS = "ru/alexpanchenko/stater/plugin/utils/models/B.class";

  private final StateTypeDeterminator typeDeterminator = new StateTypeDeterminator(classPool);

  @Test
  public void testBoolean() {
    assertEquals(typeDeterminator.determinate(Descriptors.BOOLEAN,null), StateType.BOOLEAN);
  }

  @Test
  public void testBooleanArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.BOOLEAN_ARRAY,null), StateType.BOOLEAN_ARRAY);
  }

  @Test
  public void testByte() {
    assertEquals(typeDeterminator.determinate(Descriptors.BYTE,null), StateType.BYTE);
  }

  @Test
  public void testByteArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.BYTE_ARRAY,null), StateType.BYTE_ARRAY);
  }

  @Test
  public void testChar() {
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR,null), StateType.CHAR);
  }

  @Test
  public void testCharArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_ARRAY,null), StateType.CHAR_ARRAY);
  }

  @Test
  public void testShort() {
    assertEquals(typeDeterminator.determinate(Descriptors.SHORT,null), StateType.SHORT);
  }

  @Test
  public void testShortArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.SHORT_ARRAY,null), StateType.SHORT_ARRAY);
  }

  @Test
  public void testInt() {
    assertEquals(typeDeterminator.determinate(Descriptors.INT,null), StateType.INT);
  }

  @Test
  public void testIntArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.INT_ARRAY,null), StateType.INT_ARRAY);
  }

  @Test
  public void testIntArrayList() {
    String signatureList = "Ljava/util/List<" + Descriptors.INTEGER + ">;";
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureList), StateType.INT_ARRAY_LIST);
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureList), StateType.INT_ARRAY_LIST);

    String signatureArrayList = "Ljava/util/ArrayList<" + Descriptors.INTEGER + ">;";
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureArrayList), StateType.INT_ARRAY_LIST);
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureArrayList), StateType.INT_ARRAY_LIST);
  }

  @Test
  public void testFloat() {
    assertEquals(typeDeterminator.determinate(Descriptors.FLOAT,null), StateType.FLOAT);
  }

  @Test
  public void testFloatArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.FLOAT_ARRAY,null), StateType.FLOAT_ARRAY);
  }

  @Test
  public void testLong() {
    assertEquals(typeDeterminator.determinate(Descriptors.LONG,null), StateType.LONG);
  }

  @Test
  public void testLongArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.LONG_ARRAY,null), StateType.LONG_ARRAY);
  }

  @Test
  public void testDouble() {
    assertEquals(typeDeterminator.determinate(Descriptors.DOUBLE,null), StateType.DOUBLE);
  }

  @Test
  public void testDoubleArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.DOUBLE_ARRAY,null), StateType.DOUBLE_ARRAY);
  }

  @Test
  public void testString() {
    assertEquals(typeDeterminator.determinate(Descriptors.STRING,null), StateType.STRING);
  }

  @Test
  public void testStringArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.STRING_ARRAY,null), StateType.STRING_ARRAY);
  }

  @Test
  public void testStringArrayList() {
    String signatureList = "Ljava/util/List<" + Descriptors.STRING + ">;";
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureList), StateType.STRING_ARRAY_LIST);
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureList), StateType.STRING_ARRAY_LIST);

    String signatureArrayList = "Ljava/util/ArrayList<" + Descriptors.STRING + ">;";
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureArrayList), StateType.STRING_ARRAY_LIST);
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureArrayList), StateType.STRING_ARRAY_LIST);
  }

  @Test
  public void testCharSequence() {
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_SEQUENCE,null), StateType.CHAR_SEQUENCE);
  }

  @Test
  public void testCharSequenceArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_SEQUENCE_ARRAY,null), StateType.CHAR_SEQUENCE_ARRAY);
  }

  @Test
  public void testCharSequenceArrayList() {
    String signatureList = "Ljava/util/List<" + Descriptors.CHAR_SEQUENCE + ">;";
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureList), StateType.CHAR_SEQUENCE_ARRAY_LIST);
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureList), StateType.CHAR_SEQUENCE_ARRAY_LIST);

    String signatureArrayList = "Ljava/util/ArrayList<" + Descriptors.CHAR_SEQUENCE + ">;";
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureArrayList), StateType.CHAR_SEQUENCE_ARRAY_LIST);
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureArrayList), StateType.CHAR_SEQUENCE_ARRAY_LIST);
  }

  @Test
  public void testSerializable() {
    assertEquals(typeDeterminator.determinate(Descriptors.SERIALIZABLE,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.BOOLEAN_OBJ,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.BOOLEAN_OBJ_ARRAY,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.BYTE_OBJ,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.BYTE_OBJ_ARRAY,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_OBJ,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_OBJ_ARRAY,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.SHORT_OBJ,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.SHORT_OBJ_ARRAY,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.FLOAT_OBJ,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.FLOAT_OBJ_ARRAY,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.INTEGER,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.INTEGER_ARRAY,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.LONG_OBJ,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.LONG_OBJ_ARRAY,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.DOUBLE_OBJ,null), StateType.SERIALIZABLE);
    assertEquals(typeDeterminator.determinate(Descriptors.DOUBLE_OBJ_ARRAY,null), StateType.SERIALIZABLE);
  }

  @Test
  public void testParentSerializable() throws NotFoundException {
    classPool.appendClassPath(A_CLASS);
    classPool.appendClassPath(B_CLASS);

    String descriptor = "L" + B_CLASS.replace(".class", "") + ";";
    assertEquals(typeDeterminator.determinate(descriptor,null), StateType.SERIALIZABLE);
  }

  @Test
  public void testParcelable() {
    assertEquals(typeDeterminator.determinate(Descriptors.PARCELABLE,null), StateType.PARCELABLE);
  }

  @Test
  public void testParcelableArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.PARCELABLE_ARRAY,null), StateType.PARCELABLE_ARRAY);
  }

  @Test
  public void testParcelableArrayList() {
    String signatureList = "Ljava/util/List<" + Descriptors.PARCELABLE + ">;";
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureList), StateType.PARCELABLE_ARRAY_LIST);
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureList), StateType.PARCELABLE_ARRAY_LIST);

    String signatureArrayList = "Ljava/util/ArrayList<" + Descriptors.PARCELABLE + ">;";
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureArrayList), StateType.PARCELABLE_ARRAY_LIST);
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureArrayList), StateType.PARCELABLE_ARRAY_LIST);
  }

  @Test
  public void testBundle() {
    assertEquals(typeDeterminator.determinate(Descriptors.BUNDLE,null), StateType.BUNDLE);
  }

  @Test
  public void testIBinder() {
    assertEquals(typeDeterminator.determinate(Descriptors.IBINDER,null), StateType.IBINDER);
  }
}
