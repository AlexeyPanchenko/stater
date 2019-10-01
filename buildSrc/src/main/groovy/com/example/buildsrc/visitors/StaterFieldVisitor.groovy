package com.example.buildsrc.visitors

import groovy.transform.TypeChecked
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.FieldVisitor
import com.example.buildsrc.utils.Const
import com.example.buildsrc.utils.Descriptors

@TypeChecked
class StaterFieldVisitor extends FieldVisitor {

  private final String name
  private final String descriptor
  private final String owner

  StaterFieldVisitor(FieldVisitor fieldVisitor, String name, String descriptor, String owner) {
    super(Const.ASM_VERSION, fieldVisitor)
    this.name = name
    this.descriptor = descriptor
    this.owner = owner
  }

  @Override
  AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
    AnnotationVisitor av = super.visitAnnotation(descriptor, visible)
    if (descriptor == Descriptors.STATE) {
      return new StateAnnotationVisitor(av, this.name, this.descriptor, this.owner)
    }
    return av
  }

}
