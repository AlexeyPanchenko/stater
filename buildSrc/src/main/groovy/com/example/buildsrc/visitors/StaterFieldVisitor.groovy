package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import groovy.transform.TypeChecked
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.FieldVisitor;

@TypeChecked
class StaterFieldVisitor extends FieldVisitor {

  StaterFieldVisitor(FieldVisitor fieldVisitor) {
    super(Const.ASM_VERSION, fieldVisitor)
  }

  @Override
  AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
    println("descriptor=$descriptor, visible=$visible")
    AnnotationVisitor av = super.visitAnnotation(descriptor, visible)
    if (descriptor == "Lcom/example/stater/Stater;") {
      return new StaterAnnotationVisitor(av)
    }
    return av
  }
}
