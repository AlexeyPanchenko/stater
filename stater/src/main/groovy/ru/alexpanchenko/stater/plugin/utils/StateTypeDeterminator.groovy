package ru.alexpanchenko.stater.plugin.utils

import com.android.annotations.NonNull
import com.android.annotations.Nullable
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.objectweb.asm.Type
import org.objectweb.asm.signature.SignatureReader
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.visitors.TypesSignatureVisitor

/**
 * Mapper, witch define {@link StateType} by descriptor and signature of field.
 */
@TypeChecked
@CompileStatic
class StateTypeDeterminator {

  @NonNull
  StateType determinate(@NonNull final String descriptor, @NonNull final String signature) {
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
    if (ClassHierarchyUtils.containsInterface(className, Packages.PARCELABLE)) {
      if (descriptor.startsWith("[")) {
        return StateType.PARCELABLE_ARRAY
      }
      return StateType.PARCELABLE
    }
    if (ClassHierarchyUtils.containsInterface(className, Packages.SERIALIZABLE)) {
      return StateType.SERIALIZABLE
    }
    if (ClassHierarchyUtils.containsInterface(className, Packages.IBINDER)) {
      return StateType.IBINDER
    }
    throw new IllegalStateException("Impossible to define correct type of your variable with descriptor $descriptor and signature $signature")
  }

  @Nullable
  private StateType getPrimitiveType(@NonNull String descriptor) {
    switch (descriptor) {
      case Descriptors.BOOLEAN:
      case Descriptors.BOOLEAN_OBJ:
        return StateType.BOOLEAN
      case Descriptors.BOOLEAN_ARRAY:
      case Descriptors.BOOLEAN_OBJ_ARRAY:
        return StateType.BOOLEAN_ARRAY
      case Descriptors.BYTE:
      case Descriptors.BYTE_OBJ:
        return StateType.BYTE
      case Descriptors.BYTE_ARRAY:
      case Descriptors.BYTE_OBJ_ARRAY:
        return StateType.BYTE_ARRAY
      case Descriptors.CHAR:
      case Descriptors.CHAR_OBJ:
        return StateType.CHAR
      case Descriptors.CHAR_ARRAY:
      case Descriptors.CHAR_OBJ_ARRAY:
        return StateType.CHAR_ARRAY
      case Descriptors.SHORT:
      case Descriptors.SHORT_OBJ:
        return StateType.SHORT
      case Descriptors.SHORT_ARRAY:
      case Descriptors.SHORT_OBJ_ARRAY:
        return StateType.SHORT_ARRAY
      case Descriptors.INT:
      case Descriptors.INTEGER:
        return StateType.INT
      case Descriptors.INT_ARRAY:
      case Descriptors.INTEGER_ARRAY:
        return StateType.INT_ARRAY
      case Descriptors.FLOAT:
      case Descriptors.FLOAT_OBJ:
        return StateType.FLOAT
      case Descriptors.FLOAT_ARRAY:
      case Descriptors.FLOAT_OBJ_ARRAY:
        return StateType.FLOAT_ARRAY
      case Descriptors.LONG:
      case Descriptors.LONG_OBJ:
        return StateType.LONG
      case Descriptors.LONG_ARRAY:
      case Descriptors.LONG_OBJ_ARRAY:
        return StateType.LONG_ARRAY
      case Descriptors.DOUBLE:
      case Descriptors.DOUBLE_OBJ:
        return StateType.DOUBLE
      case Descriptors.DOUBLE_ARRAY:
      case Descriptors.DOUBLE_OBJ_ARRAY:
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
      case Descriptors.BUNDLE:
        return StateType.BUNDLE
      case Descriptors.IBINDER:
        return StateType.IBINDER
    }
    return null
  }

  @Nullable
  private StateType getGenericType(@NonNull String signature) {
    List<String> signatureTypes = getSignatureTypes(signature)
    if (signatureTypes.isEmpty() || signatureTypes.size() < 2 || signatureTypes.size() > 2) {
      throw new IllegalStateException("Wrong Generic signature $signature")
    }
    String containerType = signatureTypes.get(0)
    if (containerType != Types.LIST && containerType != Types.ARRAY_LIST) {
      throw new IllegalStateException("Stater can save only $Types.LIST or $Types.ARRAY_LIST Generic. Yout signature $signature is not correct")
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
    if (genericType == Types.PARCELABLE || ClassHierarchyUtils.containsInterface(genericType, Packages.PARCELABLE)) {
      return StateType.PARCELABLE_ARRAY_LIST
    }
    return null
  }

  @NonNull
  private List<String> getSignatureTypes(@NonNull String signature) {
    TypesSignatureVisitor signatureVisitor = new TypesSignatureVisitor()
    new SignatureReader(signature).accept(signatureVisitor)
    return signatureVisitor.types
  }

}
