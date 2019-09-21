package ru.alexpanchenko.stater.plugin.visitors

import groovy.transform.TypeChecked
import org.objectweb.asm.AnnotationVisitor
import ru.alexpanchenko.stater.plugin.model.SaverField
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.Const

@TypeChecked
class StateAnnotationVisitor extends AnnotationVisitor {

  private final String name
  private final String descriptor
  private final String owner

  StateAnnotationVisitor(AnnotationVisitor annotationVisitor, String name, String descriptor, String owner) {
    super(Const.ASM_VERSION, annotationVisitor)
    this.name = name
    this.descriptor = descriptor
    this.owner = owner
  }

  @Override
  void visitEnum(String name, String descriptor, String value) {
    String typeString = (String) value
    SaverField field = new SaverField(this.name, this.descriptor, this.owner, StateType.valueOf(typeString))
    Const.stateFields.add(field)
    super.visitEnum(name, descriptor, value)
  }
}
