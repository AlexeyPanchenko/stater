package ru.alexpanchenko.stater.plugin.utils;

import org.junit.Test;

import java.util.List;

import ru.alexpanchenko.stater.plugin.model.MethodDescriptor;
import ru.alexpanchenko.stater.plugin.model.StateType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodDescriptorUtilsTest {

  @Test
  public void testBoolean() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BOOLEAN, true);
    assertEquals(getDescriptor.descriptor, Descriptors.BOOLEAN);
    assertEquals(getDescriptor.method, Methods.Get.BOOLEAN);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BOOLEAN, false);
    assertEquals(putDescriptor.descriptor, Descriptors.BOOLEAN);
    assertEquals(putDescriptor.method, Methods.Put.BOOLEAN);
  }

  @Test
  public void testBooleanArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BOOLEAN_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.BOOLEAN_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.BOOLEAN_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BOOLEAN_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.BOOLEAN_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.BOOLEAN_ARRAY);
  }

  @Test
  public void testByte() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BYTE, true);
    assertEquals(getDescriptor.descriptor, Descriptors.BYTE);
    assertEquals(getDescriptor.method, Methods.Get.BYTE);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BYTE, false);
    assertEquals(putDescriptor.descriptor, Descriptors.BYTE);
    assertEquals(putDescriptor.method, Methods.Put.BYTE);
  }

  @Test
  public void testByteArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BYTE_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.BYTE_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.BYTE_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BYTE_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.BYTE_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.BYTE_ARRAY);
  }

  @Test
  public void testChar() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR, true);
    assertEquals(getDescriptor.descriptor, Descriptors.CHAR);
    assertEquals(getDescriptor.method, Methods.Get.CHAR);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR, false);
    assertEquals(putDescriptor.descriptor, Descriptors.CHAR);
    assertEquals(putDescriptor.method, Methods.Put.CHAR);
  }

  @Test
  public void testCharArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.CHAR_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.CHAR_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.CHAR_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.CHAR_ARRAY);
  }

  @Test
  public void testShort() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SHORT, true);
    assertEquals(getDescriptor.descriptor, Descriptors.SHORT);
    assertEquals(getDescriptor.method, Methods.Get.SHORT);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SHORT, false);
    assertEquals(putDescriptor.descriptor, Descriptors.SHORT);
    assertEquals(putDescriptor.method, Methods.Put.SHORT);
  }

  @Test
  public void testShortArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SHORT_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.SHORT_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.SHORT_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SHORT_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.SHORT_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.SHORT_ARRAY);
  }

  @Test
  public void testInt() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT, true);
    assertEquals(getDescriptor.descriptor, Descriptors.INT);
    assertEquals(getDescriptor.method, Methods.Get.INT);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT, false);
    assertEquals(putDescriptor.descriptor, Descriptors.INT);
    assertEquals(putDescriptor.method, Methods.Put.INT);
  }

  @Test
  public void testIntArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.INT_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.INT_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.INT_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.INT_ARRAY);
  }

  @Test
  public void testIntArrayList() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT_ARRAY_LIST, true);
    assertEquals(getDescriptor.descriptor, Descriptors.ARRAY_LIST);
    assertEquals(getDescriptor.method, Methods.Get.INT_ARRAY_LIST);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT_ARRAY_LIST, false);
    assertEquals(putDescriptor.descriptor, Descriptors.ARRAY_LIST);
    assertEquals(putDescriptor.method, Methods.Put.INT_ARRAY_LIST);
  }

  @Test
  public void testFloat() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.FLOAT, true);
    assertEquals(getDescriptor.descriptor, Descriptors.FLOAT);
    assertEquals(getDescriptor.method, Methods.Get.FLOAT);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.FLOAT, false);
    assertEquals(putDescriptor.descriptor, Descriptors.FLOAT);
    assertEquals(putDescriptor.method, Methods.Put.FLOAT);
  }

  @Test
  public void testFloatArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.FLOAT_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.FLOAT_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.FLOAT_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.FLOAT_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.FLOAT_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.FLOAT_ARRAY);
  }

  @Test
  public void testLong() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.LONG, true);
    assertEquals(getDescriptor.descriptor, Descriptors.LONG);
    assertEquals(getDescriptor.method, Methods.Get.LONG);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.LONG, false);
    assertEquals(putDescriptor.descriptor, Descriptors.LONG);
    assertEquals(putDescriptor.method, Methods.Put.LONG);
  }

  @Test
  public void testLongArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.LONG_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.LONG_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.LONG_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.LONG_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.LONG_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.LONG_ARRAY);
  }

  @Test
  public void testDouble() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.DOUBLE, true);
    assertEquals(getDescriptor.descriptor, Descriptors.DOUBLE);
    assertEquals(getDescriptor.method, Methods.Get.DOUBLE);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.DOUBLE, false);
    assertEquals(putDescriptor.descriptor, Descriptors.DOUBLE);
    assertEquals(putDescriptor.method, Methods.Put.DOUBLE);
  }

  @Test
  public void testDoubleArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.DOUBLE_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.DOUBLE_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.DOUBLE_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.DOUBLE_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.DOUBLE_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.DOUBLE_ARRAY);
  }

  @Test
  public void testString() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING, true);
    assertEquals(getDescriptor.descriptor, Descriptors.STRING);
    assertEquals(getDescriptor.method, Methods.Get.STRING);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING, false);
    assertEquals(putDescriptor.descriptor, Descriptors.STRING);
    assertEquals(putDescriptor.method, Methods.Put.STRING);
  }

  @Test
  public void testStringArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.STRING_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.STRING_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.STRING_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.STRING_ARRAY);
  }

  @Test
  public void testStringArrayList() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING_ARRAY_LIST, true);
    assertEquals(getDescriptor.descriptor, Descriptors.ARRAY_LIST);
    assertEquals(getDescriptor.method, Methods.Get.STRING_ARRAY_LIST);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING_ARRAY_LIST, false);
    assertEquals(putDescriptor.descriptor, Descriptors.ARRAY_LIST);
    assertEquals(putDescriptor.method, Methods.Put.STRING_ARRAY_LIST);
  }

  @Test
  public void testCharSequence() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE, true);
    assertEquals(getDescriptor.descriptor, Descriptors.CHAR_SEQUENCE);
    assertEquals(getDescriptor.method, Methods.Get.CHAR_SEQUENCE);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE, false);
    assertEquals(putDescriptor.descriptor, Descriptors.CHAR_SEQUENCE);
    assertEquals(putDescriptor.method, Methods.Put.CHAR_SEQUENCE);
  }

  @Test
  public void testCharSequenceArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.CHAR_SEQUENCE_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.CHAR_SEQUENCE_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.CHAR_SEQUENCE_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.CHAR_SEQUENCE_ARRAY);
  }

  @Test
  public void testCharSequenceArrayList() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE_ARRAY_LIST, true);
    assertEquals(getDescriptor.descriptor, Descriptors.ARRAY_LIST);
    assertEquals(getDescriptor.method, Methods.Get.CHAR_SEQUENCE_ARRAY_LIST);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE_ARRAY_LIST, false);
    assertEquals(putDescriptor.descriptor, Descriptors.ARRAY_LIST);
    assertEquals(putDescriptor.method, Methods.Put.CHAR_SEQUENCE_ARRAY_LIST);
  }

  @Test
  public void testSerializable() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SERIALIZABLE, true);
    assertEquals(getDescriptor.descriptor, Descriptors.SERIALIZABLE);
    assertEquals(getDescriptor.method, Methods.Get.SERIALIZABLE);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SERIALIZABLE, false);
    assertEquals(putDescriptor.descriptor, Descriptors.SERIALIZABLE);
    assertEquals(putDescriptor.method, Methods.Put.SERIALIZABLE);
  }

  @Test
  public void testParcelable() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE, true);
    assertEquals(getDescriptor.descriptor, Descriptors.PARCELABLE);
    assertEquals(getDescriptor.method, Methods.Get.PARCELABLE);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE, false);
    assertEquals(putDescriptor.descriptor, Descriptors.PARCELABLE);
    assertEquals(putDescriptor.method, Methods.Put.PARCELABLE);
  }

  @Test
  public void testParcelableArray() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE_ARRAY, true);
    assertEquals(getDescriptor.descriptor, Descriptors.PARCELABLE_ARRAY);
    assertEquals(getDescriptor.method, Methods.Get.PARCELABLE_ARRAY);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE_ARRAY, false);
    assertEquals(putDescriptor.descriptor, Descriptors.PARCELABLE_ARRAY);
    assertEquals(putDescriptor.method, Methods.Put.PARCELABLE_ARRAY);
  }

  @Test
  public void testParcelableArrayList() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE_ARRAY_LIST, true);
    assertEquals(getDescriptor.descriptor, Descriptors.ARRAY_LIST);
    assertEquals(getDescriptor.method, Methods.Get.PARCELABLE_ARRAY_LIST);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE_ARRAY_LIST, false);
    assertEquals(putDescriptor.descriptor, Descriptors.ARRAY_LIST);
    assertEquals(putDescriptor.method, Methods.Put.PARCELABLE_ARRAY_LIST);
  }

  @Test
  public void testBundle() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BUNDLE, true);
    assertEquals(getDescriptor.descriptor, Descriptors.BUNDLE);
    assertEquals(getDescriptor.method, Methods.Get.BUNDLE);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BUNDLE, false);
    assertEquals(putDescriptor.descriptor, Descriptors.BUNDLE);
    assertEquals(putDescriptor.method, Methods.Put.BUNDLE);
  }

  @Test
  public void testIBinder() {
    MethodDescriptor getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.IBINDER, true);
    assertEquals(getDescriptor.descriptor, Descriptors.IBINDER);
    assertEquals(getDescriptor.method, Methods.Get.IBINDER);

    MethodDescriptor putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.IBINDER, false);
    assertEquals(putDescriptor.descriptor, Descriptors.IBINDER);
    assertEquals(putDescriptor.method, Methods.Put.IBINDER);
  }

  @Test
  public void testObjectsAsPrimitives() {
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.BOOLEAN_OBJ));
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.BOOLEAN_OBJ_ARRAY));

    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.BYTE_OBJ));
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.BYTE_OBJ_ARRAY));

    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.CHAR_OBJ));
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.CHAR_OBJ_ARRAY));

    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.SHORT_OBJ));
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.SHORT_OBJ_ARRAY));

    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.FLOAT_OBJ));
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.FLOAT_OBJ_ARRAY));

    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.INTEGER));
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.INTEGER_ARRAY));

    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.LONG_OBJ));
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.LONG_OBJ_ARRAY));

    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.DOUBLE_OBJ));
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.DOUBLE_OBJ_ARRAY));
  }

  @Test
  public void testSignatureTypes() {
    List<String> types = MethodDescriptorUtils.getSignatureTypes("Ljava/util/List<Lru/example/CustomClass;>;");
    assertEquals(types.size(), 2);
    assertEquals(types.get(0), "java/util/List");
    assertEquals(types.get(1), "ru/example/CustomClass");
  }

  @Test
  public void testDoubleGenericSignatureTypes() {
    List<String> types = MethodDescriptorUtils.getSignatureTypes(
      "Ljava/util/List<Ljava/util/List<Lru/example/CustomClass;>;>;"
    );
    assertEquals(types.size(), 3);
    assertEquals(types.get(0), "java/util/List");
    assertEquals(types.get(1), "java/util/List");
    assertEquals(types.get(2), "ru/example/CustomClass");
  }
}
