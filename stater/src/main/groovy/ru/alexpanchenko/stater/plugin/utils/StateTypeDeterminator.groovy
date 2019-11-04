package ru.alexpanchenko.stater.plugin.utils

import com.android.annotations.NonNull
import com.android.annotations.Nullable
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import javassist.ClassPool
import ru.alexpanchenko.stater.plugin.model.StateType
import stater.org.objectweb.asm.Type

/**
 * Mapper, witch define {@link StateType} by descriptor and signature of field.
 */
@TypeChecked
@CompileStatic
class StateTypeDeterminator {

  private final ClassPool classPool
  private final boolean withCustomSerializer

  /**
   * @param classPool - pool of all available classes for analyze AST.
   */
  StateTypeDeterminator(@NonNull ClassPool classPool, boolean withCustomSerializer) {
    this.classPool = classPool
    this.withCustomSerializer = withCustomSerializer
  }

  /**
   * Determinate {@link StateType}, using {@param descriptor} and {@param signature}.
   *
   * @throws IllegalStateException if unable to determine type.
   */
  @NonNull
  StateType determinate(
      @NonNull final String descriptor, @NonNull final String signature
  ) throws IllegalStateException {
    final StateType primitiveType = getPrimitiveType(descriptor)
    if (primitiveType != null) {
      return primitiveType
    }
    if (signature != null) {
      final StateType genericType = getGenericType(signature)
      if (genericType != null) {
        return genericType
      }
    }
    String className = Type.getType(descriptor).className
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
    throw new IllegalStateException("Impossible to define correct type of your variable with descriptor $descriptor and signature $signature")
  }

  @Nullable
  private StateType getPrimitiveType(@NonNull String descriptor) {
    switch (descriptor) {
      case Descriptors.BOOLEAN:
        return StateType.BOOLEAN
      case Descriptors.BOOLEAN_ARRAY:
        return StateType.BOOLEAN_ARRAY
      case Descriptors.BYTE:
        return StateType.BYTE
      case Descriptors.BYTE_ARRAY:
        return StateType.BYTE_ARRAY
      case Descriptors.CHAR:
        return StateType.CHAR
      case Descriptors.CHAR_ARRAY:
        return StateType.CHAR_ARRAY
      case Descriptors.SHORT:
        return StateType.SHORT
      case Descriptors.SHORT_ARRAY:
        return StateType.SHORT_ARRAY
      case Descriptors.INT:
        return StateType.INT
      case Descriptors.INT_ARRAY:
        return StateType.INT_ARRAY
      case Descriptors.FLOAT:
        return StateType.FLOAT
      case Descriptors.FLOAT_ARRAY:
        return StateType.FLOAT_ARRAY
      case Descriptors.LONG:
        return StateType.LONG
      case Descriptors.LONG_ARRAY:
        return StateType.LONG_ARRAY
      case Descriptors.DOUBLE:
        return StateType.DOUBLE
      case Descriptors.DOUBLE_ARRAY:
        return StateType.DOUBLE_ARRAY
      case Descriptors.STRING:
        return StateType.STRING
      case Descriptors.STRING_ARRAY:
        return StateType.STRING_ARRAY
      case Descriptors.CHAR_SEQUENCE:
        return StateType.CHAR_SEQUENCE
      case Descriptors.CHAR_SEQUENCE_ARRAY:
        return StateType.CHAR_SEQUENCE_ARRAY
      case Descriptors.SERIALIZABLE:
        return StateType.SERIALIZABLE
      case Descriptors.PARCELABLE:
        return StateType.PARCELABLE
      case Descriptors.PARCELABLE_ARRAY:
        return StateType.PARCELABLE_ARRAY
      case Descriptors.BUNDLE:
        return StateType.BUNDLE
      case Descriptors.IBINDER:
        return StateType.IBINDER
    }
    if (MethodDescriptorUtils.primitiveIsObject(descriptor)) {
      return StateType.SERIALIZABLE
    }
    return null
  }

  @Nullable
  private StateType getGenericType(@NonNull String signature) {
    List<String> signatureTypes = MethodDescriptorUtils.getSignatureTypes(signature)
    if (signatureTypes.isEmpty() || signatureTypes.size() < 2 || (signatureTypes.size() > 2 && !withCustomSerializer)) {
      throw new IllegalStateException("Wrong Generic signature $signature")
    }
    String containerType = signatureTypes.get(0)
    if (containerType != Types.LIST && containerType != Types.ARRAY_LIST && !withCustomSerializer) {
      throw new IllegalStateException("Stater can save only $Types.LIST or $Types.ARRAY_LIST Generic. Your signature $signature is not correct")
    }
    String genericType = signatureTypes.get(1)
    switch (genericType) {
      case Types.INTEGER:
        return StateType.INT_ARRAY_LIST
      case Types.STRING:
        return StateType.STRING_ARRAY_LIST
      case Types.CHAR_SEQUENCE:
        return StateType.CHAR_SEQUENCE_ARRAY_LIST
    }
    if (genericType == Types.PARCELABLE || ClassHierarchyUtils.containsInterface(classPool, genericType, Packages.PARCELABLE)) {
      return StateType.PARCELABLE_ARRAY_LIST
    }
    return null
  }

}
