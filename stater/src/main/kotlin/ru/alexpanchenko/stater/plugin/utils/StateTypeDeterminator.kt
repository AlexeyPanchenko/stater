package ru.alexpanchenko.stater.plugin.utils

import javassist.ClassPool
import org.objectweb.asm.Type
import ru.alexpanchenko.stater.plugin.model.StateType

class StateTypeDeterminator(
  private val classPool: ClassPool,
  private val withCustomSerializer: Boolean
) {

  @Throws(IllegalStateException::class)
  fun determinate(
    descriptor: String,
    signature: String?
  ): StateType {
    val primitiveType: StateType? = getPrimitiveType(descriptor)
    if (primitiveType != null) {
      return primitiveType
    }
    if (signature != null) {
      val genericType: StateType? = getGenericType(signature)
      if (genericType != null) {
        return genericType
      }
    }
    val className: String = Type.getType(descriptor).className
      .replace("[", "")
      .replace("]", "")
    if (ClassHierarchyUtils.containsInterface(classPool, className, Packages.PARCELABLE)) {
      if (descriptor.startsWith("[")) {
        return StateType.PARCELABLE_ARRAY
      }
      return StateType.PARCELABLE
    }
    if (ClassHierarchyUtils.containsInterface(classPool, className, Packages.SERIALIZABLE)) {
      return StateType.SERIALIZABLE
    }
    if (ClassHierarchyUtils.containsInterface(classPool, className, Packages.IBINDER)) {
      return StateType.IBINDER
    }
    if (withCustomSerializer) {
      return StateType.CUSTOM
    }
    throw IllegalStateException("Impossible to define correct type of your variable with descriptor $descriptor and signature $signature")
  }

  private fun getPrimitiveType(descriptor: String): StateType? {
    when (descriptor) {
      Descriptors.BOOLEAN -> return StateType.BOOLEAN
      Descriptors.BOOLEAN_ARRAY -> return StateType.BOOLEAN_ARRAY
      Descriptors.BYTE -> return StateType.BYTE
      Descriptors.BYTE_ARRAY -> return StateType.BYTE_ARRAY
      Descriptors.CHAR -> return StateType.CHAR
      Descriptors.CHAR_ARRAY -> return StateType.CHAR_ARRAY
      Descriptors.SHORT -> return StateType.SHORT
      Descriptors.SHORT_ARRAY -> return StateType.SHORT_ARRAY
      Descriptors.INT -> return StateType.INT
      Descriptors.INT_ARRAY -> return StateType.INT_ARRAY
      Descriptors.FLOAT -> return StateType.FLOAT
      Descriptors.FLOAT_ARRAY -> return StateType.FLOAT_ARRAY
      Descriptors.LONG -> return StateType.LONG
      Descriptors.LONG_ARRAY -> return StateType.LONG_ARRAY
      Descriptors.DOUBLE -> return StateType.DOUBLE
      Descriptors.DOUBLE_ARRAY -> return StateType.DOUBLE_ARRAY
      Descriptors.STRING -> return StateType.STRING
      Descriptors.STRING_ARRAY -> return StateType.STRING_ARRAY
      Descriptors.CHAR_SEQUENCE -> return StateType.CHAR_SEQUENCE
      Descriptors.CHAR_SEQUENCE_ARRAY -> return StateType.CHAR_SEQUENCE_ARRAY
      Descriptors.SERIALIZABLE -> return StateType.SERIALIZABLE
      Descriptors.PARCELABLE -> return StateType.PARCELABLE
      Descriptors.PARCELABLE_ARRAY -> return StateType.PARCELABLE_ARRAY
      Descriptors.BUNDLE -> return StateType.BUNDLE
      Descriptors.IBINDER -> return StateType.IBINDER
    }
    if (MethodDescriptorUtils.primitiveIsObject(descriptor)) {
      return StateType.SERIALIZABLE
    }
    return null
  }

  private fun getGenericType(signature: String): StateType? {
    val signatureTypes: List<String> = MethodDescriptorUtils.getSignatureTypes(signature)
    if (signatureTypes.isEmpty() || signatureTypes.size < 2 || (signatureTypes.size > 2 && !withCustomSerializer)) {
      throw IllegalStateException("Wrong Generic signature $signature")
    }
    val containerType: String = signatureTypes[0]
    if (containerType != Types.LIST && containerType != Types.ARRAY_LIST && !withCustomSerializer) {
      throw IllegalStateException ("Stater can save only $Types.LIST or $Types.ARRAY_LIST Generic. Your signature $signature is not correct")
    }
    val genericType: String = signatureTypes[1]
    when(genericType) {
      Types.INTEGER -> return StateType.INT_ARRAY_LIST
      Types.STRING -> return StateType.STRING_ARRAY_LIST
      Types.CHAR_SEQUENCE -> return StateType.CHAR_SEQUENCE_ARRAY_LIST
    }
    if (genericType == Types.PARCELABLE || ClassHierarchyUtils.containsInterface(classPool, genericType, Packages.PARCELABLE)) {
      return StateType.PARCELABLE_ARRAY_LIST
    }
    return null
  }
}