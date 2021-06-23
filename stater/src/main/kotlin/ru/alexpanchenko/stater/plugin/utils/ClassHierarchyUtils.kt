package ru.alexpanchenko.stater.plugin.utils

import javassist.ClassPool
import javassist.CtClass

/**
 * Utils for work with classes hierarchy.
 */
object ClassHierarchyUtils {

  /**
   * @return true if class with className or one of his parent extends one of parents, false otherwise.
   */
  fun containsParent(classPool: ClassPool, className: String?, vararg parents: String): Boolean {
    if (className == null) {
      return false
    }
    try {
      var currentClass: CtClass? = classPool.get(className.replace("/", "."))

      while (currentClass != null) {
        if (parents.contains(currentClass.name)) {
          return true
        }
        currentClass = currentClass.superclass
      }
      return false
    } catch (e: Exception) {
      e.printStackTrace()
      return false
    }
  }

  /**
   * @return true if class with className or one of his parent implement interfacePckg, false otherwise.
   */
  fun containsInterface(classPool: ClassPool, className: String, interfacePckg: String): Boolean {
    try {
      var currentClass: CtClass? = classPool.get(className.replace("/", "."))

      while (currentClass != null) {
        if (currentClass.interfaces.find { it.name == interfacePckg } != null) {
          return true
        }
        currentClass = currentClass.superclass
      }
      return false
    } catch (e: Exception) {
      e.printStackTrace()
      return false
    }
  }
}