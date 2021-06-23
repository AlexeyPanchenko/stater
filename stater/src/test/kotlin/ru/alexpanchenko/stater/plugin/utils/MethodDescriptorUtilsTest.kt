package ru.alexpanchenko.stater.plugin.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.alexpanchenko.stater.plugin.model.StateType

class MethodDescriptorUtilsTest {

  @Test
  fun testBoolean() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BOOLEAN, true)
    assertEquals(getDescriptor.descriptor, Descriptors.BOOLEAN)
    assertEquals(getDescriptor.method, Methods.Get.BOOLEAN)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BOOLEAN, false)
    assertEquals(putDescriptor.descriptor, Descriptors.BOOLEAN)
    assertEquals(putDescriptor.method, Methods.Put.BOOLEAN)
  }

  @Test
  fun testBooleanArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BOOLEAN_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.BOOLEAN_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.BOOLEAN_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BOOLEAN_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.BOOLEAN_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.BOOLEAN_ARRAY)
  }

  @Test
  fun testByte() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BYTE, true)
    assertEquals(getDescriptor.descriptor, Descriptors.BYTE)
    assertEquals(getDescriptor.method, Methods.Get.BYTE)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BYTE, false)
    assertEquals(putDescriptor.descriptor, Descriptors.BYTE)
    assertEquals(putDescriptor.method, Methods.Put.BYTE)
  }

  @Test
  fun testByteArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BYTE_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.BYTE_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.BYTE_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BYTE_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.BYTE_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.BYTE_ARRAY)
  }

  @Test
  fun testChar() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR, true)
    assertEquals(getDescriptor.descriptor, Descriptors.CHAR)
    assertEquals(getDescriptor.method, Methods.Get.CHAR)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR, false)
    assertEquals(putDescriptor.descriptor, Descriptors.CHAR)
    assertEquals(putDescriptor.method, Methods.Put.CHAR)
  }

  @Test
  fun testCharArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.CHAR_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.CHAR_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.CHAR_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.CHAR_ARRAY)
  }

  @Test
  fun testShort() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SHORT, true)
    assertEquals(getDescriptor.descriptor, Descriptors.SHORT)
    assertEquals(getDescriptor.method, Methods.Get.SHORT)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SHORT, false)
    assertEquals(putDescriptor.descriptor, Descriptors.SHORT)
    assertEquals(putDescriptor.method, Methods.Put.SHORT)
  }

  @Test
  fun testShortArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SHORT_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.SHORT_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.SHORT_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SHORT_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.SHORT_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.SHORT_ARRAY)
  }

  @Test
  fun testInt() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT, true)
    assertEquals(getDescriptor.descriptor, Descriptors.INT)
    assertEquals(getDescriptor.method, Methods.Get.INT)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT, false)
    assertEquals(putDescriptor.descriptor, Descriptors.INT)
    assertEquals(putDescriptor.method, Methods.Put.INT)
  }

  @Test
  fun testIntArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.INT_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.INT_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.INT_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.INT_ARRAY)
  }

  @Test
  fun testIntArrayList() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT_ARRAY_LIST, true)
    assertEquals(getDescriptor.descriptor, Descriptors.ARRAY_LIST)
    assertEquals(getDescriptor.method, Methods.Get.INT_ARRAY_LIST)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.INT_ARRAY_LIST, false)
    assertEquals(putDescriptor.descriptor, Descriptors.ARRAY_LIST)
    assertEquals(putDescriptor.method, Methods.Put.INT_ARRAY_LIST)
  }

  @Test
  fun testFloat() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.FLOAT, true)
    assertEquals(getDescriptor.descriptor, Descriptors.FLOAT)
    assertEquals(getDescriptor.method, Methods.Get.FLOAT)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.FLOAT, false)
    assertEquals(putDescriptor.descriptor, Descriptors.FLOAT)
    assertEquals(putDescriptor.method, Methods.Put.FLOAT)
  }

  @Test
  fun testFloatArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.FLOAT_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.FLOAT_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.FLOAT_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.FLOAT_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.FLOAT_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.FLOAT_ARRAY)
  }

  @Test
  fun testLong() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.LONG, true)
    assertEquals(getDescriptor.descriptor, Descriptors.LONG)
    assertEquals(getDescriptor.method, Methods.Get.LONG)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.LONG, false)
    assertEquals(putDescriptor.descriptor, Descriptors.LONG)
    assertEquals(putDescriptor.method, Methods.Put.LONG)
  }

  @Test
  fun testLongArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.LONG_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.LONG_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.LONG_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.LONG_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.LONG_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.LONG_ARRAY)
  }

  @Test
  fun testDouble() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.DOUBLE, true)
    assertEquals(getDescriptor.descriptor, Descriptors.DOUBLE)
    assertEquals(getDescriptor.method, Methods.Get.DOUBLE)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.DOUBLE, false)
    assertEquals(putDescriptor.descriptor, Descriptors.DOUBLE)
    assertEquals(putDescriptor.method, Methods.Put.DOUBLE)
  }

  @Test
  fun testDoubleArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.DOUBLE_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.DOUBLE_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.DOUBLE_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.DOUBLE_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.DOUBLE_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.DOUBLE_ARRAY)
  }

  @Test
  fun testString() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING, true)
    assertEquals(getDescriptor.descriptor, Descriptors.STRING)
    assertEquals(getDescriptor.method, Methods.Get.STRING)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING, false)
    assertEquals(putDescriptor.descriptor, Descriptors.STRING)
    assertEquals(putDescriptor.method, Methods.Put.STRING)
  }

  @Test
  fun testStringArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.STRING_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.STRING_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.STRING_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.STRING_ARRAY)
  }

  @Test
  fun testStringArrayList() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING_ARRAY_LIST, true)
    assertEquals(getDescriptor.descriptor, Descriptors.ARRAY_LIST)
    assertEquals(getDescriptor.method, Methods.Get.STRING_ARRAY_LIST)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.STRING_ARRAY_LIST, false)
    assertEquals(putDescriptor.descriptor, Descriptors.ARRAY_LIST)
    assertEquals(putDescriptor.method, Methods.Put.STRING_ARRAY_LIST)
  }

  @Test
  fun testCharSequence() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE, true)
    assertEquals(getDescriptor.descriptor, Descriptors.CHAR_SEQUENCE)
    assertEquals(getDescriptor.method, Methods.Get.CHAR_SEQUENCE)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE, false)
    assertEquals(putDescriptor.descriptor, Descriptors.CHAR_SEQUENCE)
    assertEquals(putDescriptor.method, Methods.Put.CHAR_SEQUENCE)
  }

  @Test
  fun testCharSequenceArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.CHAR_SEQUENCE_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.CHAR_SEQUENCE_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.CHAR_SEQUENCE_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.CHAR_SEQUENCE_ARRAY)
  }

  @Test
  fun testCharSequenceArrayList() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE_ARRAY_LIST, true)
    assertEquals(getDescriptor.descriptor, Descriptors.ARRAY_LIST)
    assertEquals(getDescriptor.method, Methods.Get.CHAR_SEQUENCE_ARRAY_LIST)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.CHAR_SEQUENCE_ARRAY_LIST, false)
    assertEquals(putDescriptor.descriptor, Descriptors.ARRAY_LIST)
    assertEquals(putDescriptor.method, Methods.Put.CHAR_SEQUENCE_ARRAY_LIST)
  }

  @Test
  fun testSerializable() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SERIALIZABLE, true)
    assertEquals(getDescriptor.descriptor, Descriptors.SERIALIZABLE)
    assertEquals(getDescriptor.method, Methods.Get.SERIALIZABLE)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.SERIALIZABLE, false)
    assertEquals(putDescriptor.descriptor, Descriptors.SERIALIZABLE)
    assertEquals(putDescriptor.method, Methods.Put.SERIALIZABLE)
  }

  @Test
  fun testParcelable() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE, true)
    assertEquals(getDescriptor.descriptor, Descriptors.PARCELABLE)
    assertEquals(getDescriptor.method, Methods.Get.PARCELABLE)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE, false)
    assertEquals(putDescriptor.descriptor, Descriptors.PARCELABLE)
    assertEquals(putDescriptor.method, Methods.Put.PARCELABLE)
  }

  @Test
  fun testParcelableArray() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE_ARRAY, true)
    assertEquals(getDescriptor.descriptor, Descriptors.PARCELABLE_ARRAY)
    assertEquals(getDescriptor.method, Methods.Get.PARCELABLE_ARRAY)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE_ARRAY, false)
    assertEquals(putDescriptor.descriptor, Descriptors.PARCELABLE_ARRAY)
    assertEquals(putDescriptor.method, Methods.Put.PARCELABLE_ARRAY)
  }

  @Test
  fun testParcelableArrayList() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE_ARRAY_LIST, true)
    assertEquals(getDescriptor.descriptor, Descriptors.ARRAY_LIST)
    assertEquals(getDescriptor.method, Methods.Get.PARCELABLE_ARRAY_LIST)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.PARCELABLE_ARRAY_LIST, false)
    assertEquals(putDescriptor.descriptor, Descriptors.ARRAY_LIST)
    assertEquals(putDescriptor.method, Methods.Put.PARCELABLE_ARRAY_LIST)
  }

  @Test
  fun testBundle() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BUNDLE, true)
    assertEquals(getDescriptor.descriptor, Descriptors.BUNDLE)
    assertEquals(getDescriptor.method, Methods.Get.BUNDLE)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.BUNDLE, false)
    assertEquals(putDescriptor.descriptor, Descriptors.BUNDLE)
    assertEquals(putDescriptor.method, Methods.Put.BUNDLE)
  }

  @Test
  fun testIBinder() {
    val getDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.IBINDER, true)
    assertEquals(getDescriptor.descriptor, Descriptors.IBINDER)
    assertEquals(getDescriptor.method, Methods.Get.IBINDER)
    val putDescriptor = MethodDescriptorUtils.getDescriptorByType(StateType.IBINDER, false)
    assertEquals(putDescriptor.descriptor, Descriptors.IBINDER)
    assertEquals(putDescriptor.method, Methods.Put.IBINDER)
  }

  @Test
  fun testObjectsAsPrimitives() {
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.BOOLEAN_OBJ))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.BOOLEAN_OBJ_ARRAY))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.BYTE_OBJ))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.BYTE_OBJ_ARRAY))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.CHAR_OBJ))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.CHAR_OBJ_ARRAY))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.SHORT_OBJ))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.SHORT_OBJ_ARRAY))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.FLOAT_OBJ))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.FLOAT_OBJ_ARRAY))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.INTEGER))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.INTEGER_ARRAY))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.LONG_OBJ))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.LONG_OBJ_ARRAY))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.DOUBLE_OBJ))
    assertTrue(MethodDescriptorUtils.primitiveIsObject(Descriptors.DOUBLE_OBJ_ARRAY))
  }

  @Test
  fun testSignatureTypes() {
    val types = MethodDescriptorUtils.getSignatureTypes("Ljava/util/List<Lru/example/CustomClass;>;")
    assertEquals(types.size.toLong(), 2)
    assertEquals(types[0], "java/util/List")
    assertEquals(types[1], "ru/example/CustomClass")
  }

  @Test
  fun testDoubleGenericSignatureTypes() {
    val types = MethodDescriptorUtils.getSignatureTypes(
      "Ljava/util/List<Ljava/util/List<Lru/example/CustomClass;>;>;"
    )
    assertEquals(types.size.toLong(), 3)
    assertEquals(types[0], "java/util/List")
    assertEquals(types[1], "java/util/List")
    assertEquals(types[2], "ru/example/CustomClass")
  }
}