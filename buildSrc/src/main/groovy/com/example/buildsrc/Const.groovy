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
  public static final String GET_INT = "getInt"
}

@TypeChecked
class Types {
  public static final String INT = "I"
  public static final String STRING = "java/lang/String"
  public static final String BUNDLE = "android/os/Bundle"
  public static final String STATE_SAVER = "com/example/stater/StateSaver"
  public static final String STATER = "com/example/stater/Stater"
}

@TypeChecked
class Descriptors {
  public static final String STRING = "L${Types.STRING};"
  public static final String BUNDLE = "L${Types.BUNDLE};"
  public static final String STATE_SAVER = "L${Types.STATE_SAVER};"
  public static final String STATER = "L${Types.STATER};"
  public static final String VOID = "V"
  public static final String ON_CREATE = "(${BUNDLE})${VOID}"
  public static final String ON_SAVED_INSTANCE_STATE = "(${BUNDLE})${VOID}"
}
