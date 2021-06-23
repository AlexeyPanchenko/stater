package ru.alexpanchenko.stater.plugin.utils

import org.objectweb.asm.signature.SignatureReader
import ru.alexpanchenko.stater.plugin.model.MethodDescriptor
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.visitors.TypesSignatureVisitor

object MethodDescriptorUtils {

  fun getDescriptorByType(type: StateType, isGet: Boolean): MethodDescriptor {
    return when (type) {
      StateType.BOOLEAN -> MethodDescriptor(if (isGet) Methods.Get.BOOLEAN else Methods.Put.BOOLEAN, Descriptors.BOOLEAN)
      StateType.BOOLEAN_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.BOOLEAN_ARRAY else Methods.Put.BOOLEAN_ARRAY, Descriptors.BOOLEAN_ARRAY)
      StateType.BYTE -> MethodDescriptor(if (isGet) Methods.Get.BYTE else Methods.Put.BYTE, Descriptors.BYTE)
      StateType.BYTE_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.BYTE_ARRAY else Methods.Put.BYTE_ARRAY, Descriptors.BYTE_ARRAY)
      StateType.CHAR -> MethodDescriptor(if (isGet) Methods.Get.CHAR else Methods.Put.CHAR, Descriptors.CHAR)
      StateType.CHAR_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.CHAR_ARRAY else Methods.Put.CHAR_ARRAY, Descriptors.CHAR_ARRAY)
      StateType.SHORT -> MethodDescriptor(if (isGet) Methods.Get.SHORT else Methods.Put.SHORT, Descriptors.SHORT)
      StateType.SHORT_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.SHORT_ARRAY else Methods.Put.SHORT_ARRAY, Descriptors.SHORT_ARRAY)
      StateType.INT -> MethodDescriptor(if (isGet) Methods.Get.INT else Methods.Put.INT, Descriptors.INT)
      StateType.INT_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.INT_ARRAY else Methods.Put.INT_ARRAY, Descriptors.INT_ARRAY)
      StateType.INT_ARRAY_LIST -> MethodDescriptor(if (isGet) Methods.Get.INT_ARRAY_LIST else Methods.Put.INT_ARRAY_LIST, Descriptors.ARRAY_LIST)
      StateType.FLOAT -> MethodDescriptor(if (isGet) Methods.Get.FLOAT else Methods.Put.FLOAT, Descriptors.FLOAT)
      StateType.FLOAT_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.FLOAT_ARRAY else Methods.Put.FLOAT_ARRAY, Descriptors.FLOAT_ARRAY)
      StateType.LONG -> MethodDescriptor(if (isGet) Methods.Get.LONG else Methods.Put.LONG, Descriptors.LONG)
      StateType.LONG_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.LONG_ARRAY else Methods.Put.LONG_ARRAY, Descriptors.LONG_ARRAY)
      StateType.DOUBLE -> MethodDescriptor(if (isGet) Methods.Get.DOUBLE else Methods.Put.DOUBLE, Descriptors.DOUBLE)
      StateType.DOUBLE_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.DOUBLE_ARRAY else Methods.Put.DOUBLE_ARRAY, Descriptors.DOUBLE_ARRAY)
      StateType.STRING -> MethodDescriptor(if (isGet) Methods.Get.STRING else Methods.Put.STRING, Descriptors.STRING)
      StateType.STRING_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.STRING_ARRAY else Methods.Put.STRING_ARRAY, Descriptors.STRING_ARRAY)
      StateType.STRING_ARRAY_LIST -> MethodDescriptor(if (isGet) Methods.Get.STRING_ARRAY_LIST else Methods.Put.STRING_ARRAY_LIST, Descriptors.ARRAY_LIST)
      StateType.CHAR_SEQUENCE -> MethodDescriptor(if (isGet) Methods.Get.CHAR_SEQUENCE else Methods.Put.CHAR_SEQUENCE, Descriptors.CHAR_SEQUENCE)
      StateType.CHAR_SEQUENCE_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.CHAR_SEQUENCE_ARRAY else Methods.Put.CHAR_SEQUENCE_ARRAY, Descriptors.CHAR_SEQUENCE_ARRAY)
      StateType.CHAR_SEQUENCE_ARRAY_LIST -> MethodDescriptor(if (isGet) Methods.Get.CHAR_SEQUENCE_ARRAY_LIST else Methods.Put.CHAR_SEQUENCE_ARRAY_LIST, Descriptors.ARRAY_LIST)
      StateType.SERIALIZABLE -> MethodDescriptor(if (isGet) Methods.Get.SERIALIZABLE else Methods.Put.SERIALIZABLE, Descriptors.SERIALIZABLE)
      StateType.PARCELABLE -> MethodDescriptor(if (isGet) Methods.Get.PARCELABLE else Methods.Put.PARCELABLE, Descriptors.PARCELABLE)
      StateType.PARCELABLE_ARRAY -> MethodDescriptor(if (isGet) Methods.Get.PARCELABLE_ARRAY else Methods.Put.PARCELABLE_ARRAY, Descriptors.PARCELABLE_ARRAY)
      StateType.PARCELABLE_ARRAY_LIST -> MethodDescriptor(if (isGet) Methods.Get.PARCELABLE_ARRAY_LIST else Methods.Put.PARCELABLE_ARRAY_LIST, Descriptors.ARRAY_LIST)
      StateType.BUNDLE -> MethodDescriptor(if (isGet) Methods.Get.BUNDLE else Methods.Put.BUNDLE, Descriptors.BUNDLE)
      StateType.IBINDER -> MethodDescriptor(if (isGet) Methods.Get.IBINDER else Methods.Put.IBINDER, Descriptors.IBINDER)
      StateType.CUSTOM -> MethodDescriptor(if (isGet) Methods.Get.STRING else Methods.Put.STRING, Descriptors.STRING)
    }
  }

  fun getSignatureTypes(signature: String): List<String> {
    val  signatureVisitor = TypesSignatureVisitor()
    SignatureReader(signature).accept(signatureVisitor)
    return signatureVisitor.types
  }

  fun primitiveIsObject(descriptor: String): Boolean {
    return descriptor == Descriptors.BYTE_OBJ || descriptor == Descriptors.BYTE_OBJ_ARRAY ||
      descriptor == Descriptors.BOOLEAN_OBJ || descriptor == Descriptors.BOOLEAN_OBJ_ARRAY ||
      descriptor == Descriptors.CHAR_OBJ || descriptor == Descriptors.CHAR_OBJ_ARRAY ||
      descriptor == Descriptors.SHORT_OBJ || descriptor == Descriptors.SHORT_OBJ_ARRAY ||
      descriptor == Descriptors.FLOAT_OBJ || descriptor == Descriptors.FLOAT_OBJ_ARRAY ||
      descriptor == Descriptors.INTEGER || descriptor == Descriptors.INTEGER_ARRAY ||
      descriptor == Descriptors.LONG_OBJ || descriptor == Descriptors.LONG_OBJ_ARRAY ||
      descriptor == Descriptors.DOUBLE_OBJ || descriptor == Descriptors.DOUBLE_OBJ_ARRAY
  }
}