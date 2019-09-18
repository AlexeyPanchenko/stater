package com.example.buildsrc

import groovy.transform.TypeChecked
import org.objectweb.asm.Opcodes

@TypeChecked
class Const {
  public static final int ASM_VERSION = Opcodes.ASM7
}

@TypeChecked
class Methods {
  public static final String ON_CREATE = "onCreate"
  public static final String ON_SAVED_INSTANCE_STATE = "onSaveInstanceState"

  static class Put {
    public static final String BOOLEAN = "putBoolean"
    public static final String BOOLEAN_ARRAY = "putBooleanArray"
    public static final String BYTE = "putByte"
    public static final String BYTE_ARRAY = "putByteArray"
    public static final String CHAR = "putChar"
    public static final String CHAR_ARRAY = "putCharArray"
    public static final String SHORT = "putShort"
    public static final String SHORT_ARRAY = "putShortArray"
    public static final String INT = "putInt"
    public static final String INT_ARRAY = "putIntArray"
    public static final String INT_ARRAY_LIST = "putIntegerArrayList"
    public static final String FLOAT = "putFloat"
    public static final String FLOAT_ARRAY = "putFloatArray"
    public static final String LONG = "putLong"
    public static final String LONG_ARRAY = "putLongArray"
    public static final String DOUBLE = "putDouble"
    public static final String DOUBLE_ARRAY = "putDoubleArray"
    public static final String STRING = "putString"
    public static final String STRING_ARRAY = "putStringArray"
    public static final String STRING_ARRAY_LIST = "putStringArrayList"
    public static final String CHAR_SEQUENCE = "putCharSequence"
    public static final String CHAR_SEQUENCE_ARRAY = "putCharSequenceArray"
    public static final String CHAR_SEQUENCE_ARRAY_LIST = "putCharSequenceArrayList"
    public static final String SERIALIZABLE = "putSerializable"
    public static final String PARCELABLE = "putParcelable"
    public static final String PARCELABLE_ARRAY = "putParcelableArray"
    public static final String PARCELABLE_ARRAY_LIST = "putParcelableArrayList"
    public static final String BUNDLE = "putBundle"
  }

  static class Get {
    public static final String BOOLEAN = "getBoolean"
    public static final String BOOLEAN_ARRAY = "getBooleanArray"
    public static final String BYTE = "getByte"
    public static final String BYTE_ARRAY = "getByteArray"
    public static final String CHAR = "getChar"
    public static final String CHAR_ARRAY = "getCharArray"
    public static final String SHORT = "getShort"
    public static final String SHORT_ARRAY = "getShortArray"
    public static final String INT = "getInt"
    public static final String INT_ARRAY = "getIntArray"
    public static final String INT_ARRAY_LIST = "getIntegerArrayList"
    public static final String FLOAT = "getFloat"
    public static final String FLOAT_ARRAY = "getFloatArray"
    public static final String LONG = "getLong"
    public static final String LONG_ARRAY = "getLongArray"
    public static final String DOUBLE = "getDouble"
    public static final String DOUBLE_ARRAY = "getDoubleArray"
    public static final String STRING = "getString"
    public static final String STRING_ARRAY = "getStringArray"
    public static final String STRING_ARRAY_LIST = "getStringArrayList"
    public static final String CHAR_SEQUENCE = "getCharSequence"
    public static final String CHAR_SEQUENCE_ARRAY = "getCharSequenceArray"
    public static final String CHAR_SEQUENCE_ARRAY_LIST = "getCharSequenceArrayList"
    public static final String SERIALIZABLE = "getSerializable"
    public static final String PARCELABLE = "getParcelable"
    public static final String PARCELABLE_ARRAY = "getParcelableArray"
    public static final String PARCELABLE_ARRAY_LIST = "getParcelableArrayList"
    public static final String BUNDLE = "getBundle"
  }
 
}

@TypeChecked
class Types {
  public static final String LIST = "java/util/List"
  public static final String ARRAY_LIST = "java/util/ArrayList"
  public static final String BYTE = "B"
  public static final String BOOLEAN = "Z"
  public static final String BOOLEAN_OBJ = "java/lang/Boolean"
  public static final String SHORT = "S"
  public static final String INT = "I"
  public static final String INTEGER = "java/lang/Integer"
  public static final String FLOAT = "F"
  public static final String LONG = "J"
  public static final String DOUBLE = "D"
  public static final String STRING = "java/lang/String"
  public static final String CHAR_SEQUENCE = "java/lang/CharSequence"
  public static final String SERIALIZABLE = "java/io/Serializable"
  public static final String PARCELABLE = "android/os/Parcelable"
  public static final String BUNDLE = "android/os/Bundle"
  // todo!
  public static final String STATE_SAVER = "com/example/stater/lib/StateSaver"
  public static final String STATER = "com/example/stater/lib/Stater"
}

@TypeChecked
class Descriptors {
  public static final String LIST = "L${Types.LIST};"
  public static final String ARRAY_LIST = "L${Types.ARRAY_LIST};"
  public static final String BOOLEAN = "Z"
  public static final String BOOLEAN_ARRAY = "[$BOOLEAN"
  public static final String BYTE = "B"
  public static final String BYTE_ARRAY = "[$BYTE"
  public static final String CHAR = "C"
  public static final String CHAR_ARRAY = "[$CHAR"
  public static final String SHORT = "S"
  public static final String SHORT_ARRAY = "[$SHORT"
  public static final String INT = "I"
  public static final String INTEGER = "L${Types.INTEGER};"
  public static final String INT_ARRAY = "[$INT"
  public static final String FLOAT = "F"
  public static final String FLOAT_ARRAY = "[$FLOAT"
  public static final String LONG = "J"
  public static final String LONG_ARRAY = "[$LONG"
  public static final String DOUBLE = "D"
  public static final String DOUBLE_ARRAY = "[$DOUBLE"
  public static final String STRING = "L${Types.STRING};"
  public static final String STRING_ARRAY = "[$STRING"
  public static final String CHAR_SEQUENCE = "L${Types.CHAR_SEQUENCE};"
  public static final String CHAR_SEQUENCE_ARRAY = "[$CHAR_SEQUENCE"
  public static final String SERIALIZABLE = "L${Types.SERIALIZABLE};"
  public static final String PARCELABLE = "L${Types.PARCELABLE};"
  public static final String PARCELABLE_ARRAY = "[$PARCELABLE"
  public static final String BUNDLE = "L${Types.BUNDLE};"
  public static final String STATE_SAVER = "L${Types.STATE_SAVER};"
  public static final String STATER = "L${Types.STATER};"
  public static final String VOID = "V"
  public static final String ON_CREATE = "(${BUNDLE})${VOID}"
  public static final String ON_SAVED_INSTANCE_STATE = "(${BUNDLE})${VOID}"
}
