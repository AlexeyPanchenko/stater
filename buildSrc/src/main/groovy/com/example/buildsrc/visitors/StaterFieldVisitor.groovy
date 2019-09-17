package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import com.example.buildsrc.Descriptors
import com.example.buildsrc.Store
import com.example.buildsrc.model.SaverField
import groovy.transform.TypeChecked
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.FieldVisitor

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
    println("INNER FIELD visitAnnotation: descriptor=$descriptor, visible=$visible")
    AnnotationVisitor av = super.visitAnnotation(descriptor, visible)
    if (descriptor == Descriptors.STATER) {
      Store.instance.fields.add(new SaverField(this.name, this.descriptor, this.owner))
      return new StaterAnnotationVisitor(av)
    }
    return av
  }

}
