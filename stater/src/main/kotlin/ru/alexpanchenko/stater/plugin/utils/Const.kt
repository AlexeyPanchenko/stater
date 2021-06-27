package ru.alexpanchenko.stater.plugin.utils

import org.objectweb.asm.Opcodes

object Const {
  const val ASM_VERSION: Int = Opcodes.ASM7
}

object Methods {
  const val ON_CREATE = "onCreate"
  const val ON_SAVED_INSTANCE_STATE = "onSaveInstanceState"
  const val SERIALIZE = "serialize"
  const val DESERIALIZE = "deserialize"
  const val DESERIALIZE_TYPED = "deserializeTyped"

  object Put {
    const val BOOLEAN = "putBoolean"
    const val BOOLEAN_ARRAY = "putBooleanArray"
    const val BYTE = "putByte"
    const val BYTE_ARRAY = "putByteArray"
    const val CHAR = "putChar"
    const val CHAR_ARRAY = "putCharArray"
    const val SHORT = "putShort"
    const val SHORT_ARRAY = "putShortArray"
    const val INT = "putInt"
    const val INT_ARRAY = "putIntArray"
    const val INT_ARRAY_LIST = "putIntegerArrayList"
    const val FLOAT = "putFloat"
    const val FLOAT_ARRAY = "putFloatArray"
    const val LONG = "putLong"
    const val LONG_ARRAY = "putLongArray"
    const val DOUBLE = "putDouble"
    const val DOUBLE_ARRAY = "putDoubleArray"
    const val STRING = "putString"
    const val STRING_ARRAY = "putStringArray"
    const val STRING_ARRAY_LIST = "putStringArrayList"
    const val CHAR_SEQUENCE = "putCharSequence"
    const val CHAR_SEQUENCE_ARRAY = "putCharSequenceArray"
    const val CHAR_SEQUENCE_ARRAY_LIST = "putCharSequenceArrayList"
    const val SERIALIZABLE = "putSerializable"
    const val PARCELABLE = "putParcelable"
    const val PARCELABLE_ARRAY = "putParcelableArray"
    const val PARCELABLE_ARRAY_LIST = "putParcelableArrayList"
    const val BUNDLE = "putBundle"
    const val IBINDER = "putBinder"
  }

  object Get {
    const val BOOLEAN = "getBoolean"
    const val BOOLEAN_ARRAY = "getBooleanArray"
    const val BYTE = "getByte"
    const val BYTE_ARRAY = "getByteArray"
    const val CHAR = "getChar"
    const val CHAR_ARRAY = "getCharArray"
    const val SHORT = "getShort"
    const val SHORT_ARRAY = "getShortArray"
    const val INT = "getInt"
    const val INT_ARRAY = "getIntArray"
    const val INT_ARRAY_LIST = "getIntegerArrayList"
    const val FLOAT = "getFloat"
    const val FLOAT_ARRAY = "getFloatArray"
    const val LONG = "getLong"
    const val LONG_ARRAY = "getLongArray"
    const val DOUBLE = "getDouble"
    const val DOUBLE_ARRAY = "getDoubleArray"
    const val STRING = "getString"
    const val STRING_ARRAY = "getStringArray"
    const val STRING_ARRAY_LIST = "getStringArrayList"
    const val CHAR_SEQUENCE = "getCharSequence"
    const val CHAR_SEQUENCE_ARRAY = "getCharSequenceArray"
    const val CHAR_SEQUENCE_ARRAY_LIST = "getCharSequenceArrayList"
    const val SERIALIZABLE = "getSerializable"
    const val PARCELABLE = "getParcelable"
    const val PARCELABLE_ARRAY = "getParcelableArray"
    const val PARCELABLE_ARRAY_LIST = "getParcelableArrayList"
    const val BUNDLE = "getBundle"
    const val IBINDER = "getBinder"
  }

}

object Packages {
  const val ACTIVITY = "android.app.Activity"
  const val ACTIVITY_X_SUPPORT = "androidx.appcompat.app.AppCompatActivity"
  const val FRAGMENT = "android.app.Fragment"
  const val FRAGMENT_X = "androidx.fragment.app.Fragment"
  const val FRAGMENT_SUPPORT = "android.support.v4.app.Fragment"

  const val INTEGER = "java.lang.Integer"
  const val STRING = "java.lang.String"
  const val CHAR_SEQUENCE = "java.lang.CharSequence"
  const val SERIALIZABLE = "java.io.Serializable"
  const val PARCELABLE = "android.os.Parcelable"
  const val IBINDER = "android.os.IBinder"
}

object Types {
  const val OBJECT = "java/lang/Object"
  const val CLASS = "java/lang/Class"
  const val LIST = "java/util/List"
  const val ARRAY_LIST = "java/util/ArrayList"
  const val BYTE = "B"
  const val BYTE_OBJ = "java/lang/Byte"
  const val BOOLEAN = "Z"
  const val BOOLEAN_OBJ = "java/lang/Boolean"
  const val CHAR_OBJ = "java/lang/Character"
  const val SHORT = "S"
  const val SHORT_OBJ = "java/lang/Short"
  const val INT = "I"
  const val INTEGER = "java/lang/Integer"
  const val FLOAT = "F"
  const val FLOAT_OBJ = "java/lang/Float"
  const val LONG = "J"
  const val LONG_OBJ = "java/lang/Long"
  const val DOUBLE = "D"
  const val DOUBLE_OBJ = "java/lang/Double"
  const val STRING = "java/lang/String"
  const val CHAR_SEQUENCE = "java/lang/CharSequence"
  const val SERIALIZABLE = "java/io/Serializable"
  const val PARCELABLE = "android/os/Parcelable"
  const val BUNDLE = "android/os/Bundle"
  const val IBINDER = "android/os/IBinder"
  const val STATE = "ru/alexpanchenko/stater/State"
  const val SERIALIZER = "ru/alexpanchenko/stater/serializer/StaterSerializer"
}

object Descriptors {
  const val OBJECT = "L${Types.OBJECT};"
  const val CLASS = "L${Types.CLASS};"
  const val LIST = "L${Types.LIST};"
  const val ARRAY_LIST = "L${Types.ARRAY_LIST};"
  const val BOOLEAN = "Z"
  const val BOOLEAN_OBJ = "L${Types.BOOLEAN_OBJ};"
  const val BOOLEAN_ARRAY = "[$BOOLEAN"
  const val BOOLEAN_OBJ_ARRAY = "[$BOOLEAN_OBJ"
  const val BYTE = "B"
  const val BYTE_OBJ = "L${Types.BYTE_OBJ};"
  const val BYTE_ARRAY = "[$BYTE"
  const val BYTE_OBJ_ARRAY = "[$BYTE_OBJ"
  const val CHAR = "C"
  const val CHAR_OBJ = "L${Types.CHAR_OBJ};"
  const val CHAR_ARRAY = "[$CHAR"
  const val CHAR_OBJ_ARRAY = "[$CHAR_OBJ"
  const val SHORT = "S"
  const val SHORT_OBJ = "L${Types.SHORT_OBJ};"
  const val SHORT_ARRAY = "[$SHORT"
  const val SHORT_OBJ_ARRAY = "[$SHORT_OBJ"
  const val INT = "I"
  const val INTEGER = "L${Types.INTEGER};"
  const val INT_ARRAY = "[$INT"
  const val INTEGER_ARRAY = "[$INTEGER"
  const val FLOAT = "F"
  const val FLOAT_OBJ = "L${Types.FLOAT_OBJ};"
  const val FLOAT_ARRAY = "[$FLOAT"
  const val FLOAT_OBJ_ARRAY = "[$FLOAT_OBJ"
  const val LONG = "J"
  const val LONG_OBJ = "L${Types.LONG_OBJ};"
  const val LONG_ARRAY = "[$LONG"
  const val LONG_OBJ_ARRAY = "[$LONG_OBJ"
  const val DOUBLE = "D"
  const val DOUBLE_OBJ = "L${Types.DOUBLE_OBJ};"
  const val DOUBLE_ARRAY = "[$DOUBLE"
  const val DOUBLE_OBJ_ARRAY = "[$DOUBLE_OBJ"
  const val STRING = "L${Types.STRING};"
  const val STRING_ARRAY = "[$STRING"
  const val CHAR_SEQUENCE = "L${Types.CHAR_SEQUENCE};"
  const val CHAR_SEQUENCE_ARRAY = "[$CHAR_SEQUENCE"
  const val SERIALIZABLE = "L${Types.SERIALIZABLE};"
  const val PARCELABLE = "L${Types.PARCELABLE};"
  const val PARCELABLE_ARRAY = "[$PARCELABLE"
  const val BUNDLE = "L${Types.BUNDLE};"
  const val IBINDER = "L${Types.IBINDER};"
  const val STATE = "L${Types.STATE};"
  const val SERIALIZER = "L${Types.SERIALIZER};"
  const val VOID = "V"
  const val ON_CREATE = "(${BUNDLE})${VOID}"
  const val ON_SAVED_INSTANCE_STATE = "(${BUNDLE})${VOID}"
  const val SERIALIZER_SERIALIZE = "(${OBJECT})${STRING}"
  const val SERIALIZER_DESERIALIZE = "(${STRING}${CLASS})${OBJECT}"
  const val SERIALIZER_DESERIALIZE_TYPED = "(${STRING}[${CLASS})${OBJECT}"
}