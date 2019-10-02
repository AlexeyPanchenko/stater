package com.example.buildsrc.utils

import groovy.transform.TypeChecked
import javassist.ClassPool
import javassist.CtClass;

/**
 * Utils for work with classes hierarchy.
 */
class ClassHierarchyUtils {

  /**
   * @return true if class with className or one of his parent extends one of parents, false otherwise.
   */
  @TypeChecked
  static boolean containsParent(String className, String... parents) {
    try {
      ClassPool pool = ClassPool.getDefault()
      CtClass currentClass = pool.get(className.replace("/", "."))

      while (currentClass != null) {
        if (parents.contains(currentClass.name)) {
          return true
        }
        currentClass = currentClass.superclass
      }
      return false
    } catch (Exception e) {
      e.printStackTrace()
      return false
    }
  }

  /**
   * @return true if class with className or one of his parent implement interfacePckg, false otherwise.
   */
  @TypeChecked
  static boolean containsInterface(String className, String interfacePckg) {
    try {
      ClassPool pool = ClassPool.getDefault()
      CtClass currentClass = pool.get(className.replace("/", "."))

      while (currentClass != null) {
        for (CtClass currentInterface : currentClass.interfaces) {
          if (currentInterface.name == interfacePckg) {
            return true
          }
        }
        currentClass = currentClass.superclass
      }
      return false
    } catch (Exception e) {
      e.printStackTrace()
      return false
    }
  }
}
