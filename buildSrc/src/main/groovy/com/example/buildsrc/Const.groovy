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

  public static final String PUT_INT = "putInt"
  public static final String PUT_BOOLEAN = "putBoolean"
  public static final String PUT_LONG = "putLong"
  public static final String PUT_STRING = "putString"
  public static final String GET_INT = "getInt"
  public static final String GET_BOOLEAN = "getBoolean"
  public static final String GET_LONG = "getLong"
  public static final String GET_STRING = "getString"
}

@TypeChecked
class Types {
  public static final String INT = "I"
  public static final String BOOLEAN = "Z"
  public static final String LONG = "J"
  public static final String STRING = "java/lang/String"
  public static final String BUNDLE = "android/os/Bundle"
  public static final String STATE_SAVER = "com/example/stater/lib/StateSaver"
  public static final String STATER = "com/example/stater/lib/Stater"
}

@TypeChecked
class Descriptors {
  public static final String INT = "I"
  public static final String BOOLEAN = "Z"
  public static final String LONG = "J"
  public static final String STRING = "L${Types.STRING};"
  public static final String BUNDLE = "L${Types.BUNDLE};"
  public static final String STATE_SAVER = "L${Types.STATE_SAVER};"
  public static final String STATER = "L${Types.STATER};"
  public static final String VOID = "V"
  public static final String ON_CREATE = "(${BUNDLE})${VOID}"
  public static final String ON_SAVED_INSTANCE_STATE = "(${BUNDLE})${VOID}"
}
