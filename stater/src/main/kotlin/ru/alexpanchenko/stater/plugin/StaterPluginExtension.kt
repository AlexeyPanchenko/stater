package ru.alexpanchenko.stater.plugin

open class StaterPluginExtension {

  companion object {
    const val NAME = "stater"
  }

  var listener: ((isEnabled: Boolean) -> Unit)? = null
  var customSerializerEnabled = false
    set(value) {
      field = value
      listener?.invoke(value)
    }
}