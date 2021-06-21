package ru.alexpanchenko.stater.plugin

import com.android.annotations.NonNull
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@TypeChecked
@CompileStatic
class StaterPluginExtension {

  public static final String NAME = "stater"

  private boolean customSerializerEnabled = false
  private ExtensionChangeListener listener

  void setCustomSerializerEnabled(boolean enable) {
    customSerializerEnabled = enable
    if (listener != null) {
      listener.onChanged()
    }
  }

  boolean getCustomSerializerEnabled() {
    return customSerializerEnabled
  }

  void setExtensionChangeListener(@NonNull ExtensionChangeListener listener) {
    this.listener = listener
  }

}

interface ExtensionChangeListener {
  void onChanged()
}
