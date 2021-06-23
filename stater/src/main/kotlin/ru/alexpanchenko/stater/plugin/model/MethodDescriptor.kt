package ru.alexpanchenko.stater.plugin.model

data class MethodDescriptor(
  val method: String?,
  val descriptor: String?,
) {

  fun isValid(): Boolean {
    return !method.isNullOrEmpty() && !descriptor.isNullOrEmpty()
  }
}