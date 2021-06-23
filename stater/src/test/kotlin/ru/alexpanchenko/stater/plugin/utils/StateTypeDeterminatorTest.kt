package ru.alexpanchenko.stater.plugin.utils

import javassist.ClassPool
import javassist.NotFoundException
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.alexpanchenko.stater.plugin.model.StateType

class StateTypeDeterminatorTest {

  companion object {
    private const val A_CLASS = "ru/alexpanchenko/stater/plugin/utils/A.class"
    private const val B_CLASS = "ru/alexpanchenko/stater/plugin/utils/B.class"
  }

  private val classPool = ClassPool.getDefault()
  private val typeDeterminator = StateTypeDeterminator(classPool, false)

  @Test
  fun testBoolean() {
    assertEquals(typeDeterminator.determinate(Descriptors.BOOLEAN, null), StateType.BOOLEAN)
  }

  @Test
  fun testBooleanArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.BOOLEAN_ARRAY, null), StateType.BOOLEAN_ARRAY)
  }

  @Test
  fun testByte() {
    assertEquals(typeDeterminator.determinate(Descriptors.BYTE, null), StateType.BYTE)
  }

  @Test
  fun testByteArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.BYTE_ARRAY, null), StateType.BYTE_ARRAY)
  }

  @Test
  fun testChar() {
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR, null), StateType.CHAR)
  }

  @Test
  fun testCharArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_ARRAY, null), StateType.CHAR_ARRAY)
  }

  @Test
  fun testShort() {
    assertEquals(typeDeterminator.determinate(Descriptors.SHORT, null), StateType.SHORT)
  }

  @Test
  fun testShortArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.SHORT_ARRAY, null), StateType.SHORT_ARRAY)
  }

  @Test
  fun testInt() {
    assertEquals(typeDeterminator.determinate(Descriptors.INT, null), StateType.INT)
  }

  @Test
  fun testIntArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.INT_ARRAY, null), StateType.INT_ARRAY)
  }

  @Test
  fun testIntArrayList() {
    val signatureList = "Ljava/util/List<" + Descriptors.INTEGER + ">;"
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureList), StateType.INT_ARRAY_LIST)
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureList), StateType.INT_ARRAY_LIST)
    val signatureArrayList = "Ljava/util/ArrayList<" + Descriptors.INTEGER + ">;"
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureArrayList), StateType.INT_ARRAY_LIST)
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureArrayList), StateType.INT_ARRAY_LIST)
  }

  @Test
  fun testFloat() {
    assertEquals(typeDeterminator.determinate(Descriptors.FLOAT, null), StateType.FLOAT)
  }

  @Test
  fun testFloatArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.FLOAT_ARRAY, null), StateType.FLOAT_ARRAY)
  }

  @Test
  fun testLong() {
    assertEquals(typeDeterminator.determinate(Descriptors.LONG, null), StateType.LONG)
  }

  @Test
  fun testLongArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.LONG_ARRAY, null), StateType.LONG_ARRAY)
  }

  @Test
  fun testDouble() {
    assertEquals(typeDeterminator.determinate(Descriptors.DOUBLE, null), StateType.DOUBLE)
  }

  @Test
  fun testDoubleArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.DOUBLE_ARRAY, null), StateType.DOUBLE_ARRAY)
  }

  @Test
  fun testString() {
    assertEquals(typeDeterminator.determinate(Descriptors.STRING, null), StateType.STRING)
  }

  @Test
  fun testStringArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.STRING_ARRAY, null), StateType.STRING_ARRAY)
  }

  @Test
  fun testStringArrayList() {
    val signatureList = "Ljava/util/List<" + Descriptors.STRING + ">;"
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureList), StateType.STRING_ARRAY_LIST)
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureList), StateType.STRING_ARRAY_LIST)
    val signatureArrayList = "Ljava/util/ArrayList<" + Descriptors.STRING + ">;"
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureArrayList), StateType.STRING_ARRAY_LIST)
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureArrayList), StateType.STRING_ARRAY_LIST)
  }

  @Test
  fun testCharSequence() {
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_SEQUENCE, null), StateType.CHAR_SEQUENCE)
  }

  @Test
  fun testCharSequenceArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_SEQUENCE_ARRAY, null), StateType.CHAR_SEQUENCE_ARRAY)
  }

  @Test
  fun testCharSequenceArrayList() {
    val signatureList = "Ljava/util/List<" + Descriptors.CHAR_SEQUENCE + ">;"
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureList), StateType.CHAR_SEQUENCE_ARRAY_LIST)
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureList), StateType.CHAR_SEQUENCE_ARRAY_LIST)
    val signatureArrayList = "Ljava/util/ArrayList<" + Descriptors.CHAR_SEQUENCE + ">;"
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureArrayList), StateType.CHAR_SEQUENCE_ARRAY_LIST)
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureArrayList), StateType.CHAR_SEQUENCE_ARRAY_LIST)
  }

  @Test
  fun testSerializable() {
    assertEquals(typeDeterminator.determinate(Descriptors.SERIALIZABLE, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.BOOLEAN_OBJ, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.BOOLEAN_OBJ_ARRAY, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.BYTE_OBJ, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.BYTE_OBJ_ARRAY, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_OBJ, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.CHAR_OBJ_ARRAY, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.SHORT_OBJ, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.SHORT_OBJ_ARRAY, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.FLOAT_OBJ, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.FLOAT_OBJ_ARRAY, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.INTEGER, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.INTEGER_ARRAY, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.LONG_OBJ, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.LONG_OBJ_ARRAY, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.DOUBLE_OBJ, null), StateType.SERIALIZABLE)
    assertEquals(typeDeterminator.determinate(Descriptors.DOUBLE_OBJ_ARRAY, null), StateType.SERIALIZABLE)
  }

  @Test
  @Throws(NotFoundException::class)
  fun testParentSerializable() {
    classPool.appendClassPath(A_CLASS)
    classPool.appendClassPath(B_CLASS)
    val descriptor = "L" + B_CLASS.replace(".class", "") + ";"
    assertEquals(typeDeterminator.determinate(descriptor, null), StateType.SERIALIZABLE)
  }

  @Test
  fun testParcelable() {
    assertEquals(typeDeterminator.determinate(Descriptors.PARCELABLE, null), StateType.PARCELABLE)
  }

  @Test
  fun testParcelableArray() {
    assertEquals(typeDeterminator.determinate(Descriptors.PARCELABLE_ARRAY, null), StateType.PARCELABLE_ARRAY)
  }

  @Test
  fun testParcelableArrayList() {
    val signatureList = "Ljava/util/List<" + Descriptors.PARCELABLE + ">;"
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureList), StateType.PARCELABLE_ARRAY_LIST)
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureList), StateType.PARCELABLE_ARRAY_LIST)
    val signatureArrayList = "Ljava/util/ArrayList<" + Descriptors.PARCELABLE + ">;"
    assertEquals(typeDeterminator.determinate(Descriptors.LIST, signatureArrayList), StateType.PARCELABLE_ARRAY_LIST)
    assertEquals(typeDeterminator.determinate(Descriptors.ARRAY_LIST, signatureArrayList), StateType.PARCELABLE_ARRAY_LIST)
  }

  @Test
  fun testBundle() {
    assertEquals(typeDeterminator.determinate(Descriptors.BUNDLE, null), StateType.BUNDLE)
  }

  @Test
  fun testIBinder() {
    assertEquals(typeDeterminator.determinate(Descriptors.IBINDER, null), StateType.IBINDER)
  }

  @Test(expected = IllegalStateException::class)
  fun testUnsupportedType() {
    typeDeterminator.determinate(Descriptors.OBJECT, null)
  }

  @Test(expected = IllegalStateException::class)
  fun testUnsupportedListType() {
    typeDeterminator.determinate(Descriptors.LIST, "Ljava/util/List<" + Descriptors.OBJECT + ">;")
  }

  @Test
  fun testUnsupportedTypeWithCustomSerialization() {
    val typeDeterminator = StateTypeDeterminator(classPool, true)
    val type = typeDeterminator.determinate(Descriptors.OBJECT, null)
    assertEquals(type, StateType.CUSTOM)
  }

  @Test
  fun testUnsupportedTypedTypeWithCustomSerialization() {
    val typeDeterminator = StateTypeDeterminator(classPool, true)
    val type = typeDeterminator.determinate(Descriptors.OBJECT, "Ljava/util/List<" + Descriptors.OBJECT + ">;")
    assertEquals(type, StateType.CUSTOM)
  }
}