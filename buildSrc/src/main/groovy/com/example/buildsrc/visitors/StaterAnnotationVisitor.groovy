package com.example.buildsrc.visitors

import com.example.buildsrc.Const
import com.example.buildsrc.StateType
import com.example.buildsrc.Store
import com.example.buildsrc.model.SaverField
import groovy.transform.TypeChecked
import org.objectweb.asm.AnnotationVisitor

@TypeChecked
class StaterAnnotationVisitor extends AnnotationVisitor {

  private final String name
  private final String descriptor
  private final String owner

  StaterAnnotationVisitor(AnnotationVisitor annotationVisitor, String name, String descriptor, String owner) {
    super(Const.ASM_VERSION, annotationVisitor)
    this.name = name
    this.descriptor = descriptor
    this.owner = owner
  }

  @Override
  void visit(String name, Object value) {
    println("StaterAnnotationVisitor visit: name=$name, value=$value")
//    String typeString = (String) value
//    println("1F=$typeString")
//    SaverField field = new SaverField(this.name, this.descriptor, this.owner, StateType.valueOf(typeString))
//    println("F=$field")
//    Store.instance.fields.add(field)
    super.visit(name, value)
  }

  @Override
  void visitEnum(String name, String descriptor, String value) {
    println("StaterAnnotationVisitor visitEnum: name=$name, descriptor=$descriptor, value=$value")
    String typeString = (String) value
    SaverField field = new SaverField(this.name, this.descriptor, this.owner, StateType.valueOf(typeString))
    Store.instance.fields.add(field)
    super.visitEnum(name, descriptor, value)
  }
}
