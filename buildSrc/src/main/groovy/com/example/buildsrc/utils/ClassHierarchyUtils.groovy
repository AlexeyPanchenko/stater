package com.example.buildsrc.utils

import com.example.buildsrc.model.StateType
import groovy.transform.TypeChecked
import javassist.ClassPool
import javassist.CtClass;

class ClassHierarchyUtils {

  @TypeChecked
  static boolean containsParent(String className, String parent) {
    try {
      ClassPool pool = ClassPool.getDefault()
      CtClass currentClass = pool.get(className.replace("/", "."))

      while (currentClass != null) {
        if (currentClass.name == parent) {
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

//  @TypeChecked
//  static StateType getClassType(String className) {
//    ClassPool pool = ClassPool.getDefault()
//    CtClass currentClass = pool.get(className.replace("/", "."))
//
//    while (currentClass != null) {
//      if (currentClass.name == parent) {
//        return true
//      }
//      currentClass = currentClass.superclass
//    }
//    return false
//  }
}
