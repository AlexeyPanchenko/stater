package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import groovy.transform.TypeChecked
import org.objectweb.asm.AnnotationVisitor;

@TypeChecked
class StaterAnnotationVisitor extends AnnotationVisitor {

  StaterAnnotationVisitor(AnnotationVisitor annotationVisitor) {
    super(Const.ASM_VERSION, annotationVisitor)
  }

  @Override
  void visit(String name, Object value) {
    println("StaterAnnotationVisitor: name=$name, value=$value")
    super.visit(name, value)
  }

  @Override
  void visitEnum(String name, String descriptor, String value) {
    println("StaterAnnotationVisitor: name=$name, descriptor=$descriptor, value=$value")
    super.visitEnum(name, descriptor, value)
  }
}
