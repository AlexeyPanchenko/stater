package ru.alexpanchenko.stater.plugin.visitors

import com.android.annotations.NonNull
import groovy.transform.TypeChecked
import ru.alexpanchenko.stater.plugin.model.SaverField
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.Const
import ru.alexpanchenko.stater.plugin.utils.Descriptors
import ru.alexpanchenko.stater.plugin.utils.StateTypeDeterminator
import stater.org.objectweb.asm.AnnotationVisitor
import stater.org.objectweb.asm.FieldVisitor

@TypeChecked
class StaterFieldVisitor extends FieldVisitor {

  private final String name
  private final String descriptor
  private final String signature
  private final String owner
  private final StateTypeDeterminator typeDeterminator

  StaterFieldVisitor(
      @NonNull FieldVisitor fieldVisitor,
      @NonNull String name,
      @NonNull String descriptor,
      @NonNull String signature,
      @NonNull String owner,
      @NonNull StateTypeDeterminator typeDeterminator
  ) {
    super(Const.ASM_VERSION, fieldVisitor)
    this.name = name
    this.descriptor = descriptor
    this.signature = signature
    this.owner = owner
    this.typeDeterminator = typeDeterminator
  }

  @Override
  AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
    AnnotationVisitor av = super.visitAnnotation(descriptor, visible)
    if (descriptor == Descriptors.STATE) {
      StateType type = typeDeterminator.determinate(this.descriptor, this.signature)
      SaverField field = new SaverField(this.name, this.descriptor, this.owner, type)
      Const.stateFields.add(field)
    }
    return av
  }

}