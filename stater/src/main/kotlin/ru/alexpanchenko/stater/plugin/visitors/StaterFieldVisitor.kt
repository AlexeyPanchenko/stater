package ru.alexpanchenko.stater.plugin.visitors

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.FieldVisitor
import ru.alexpanchenko.stater.plugin.StateFieldStorage
import ru.alexpanchenko.stater.plugin.model.SaverField
import ru.alexpanchenko.stater.plugin.model.StateType
import ru.alexpanchenko.stater.plugin.utils.Const
import ru.alexpanchenko.stater.plugin.utils.Descriptors
import ru.alexpanchenko.stater.plugin.utils.StateTypeDeterminator

class StaterFieldVisitor(
  fieldVisitor: FieldVisitor?,
  private val name: String,
  private val descriptor: String,
  private val signature: String?,
  private val owner: String?,
  private val typeDeterminator: StateTypeDeterminator,
  private val fieldStorage: StateFieldStorage,
) : FieldVisitor(Const.ASM_VERSION, fieldVisitor) {

  override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor? {
    val av: AnnotationVisitor? = super.visitAnnotation(descriptor, visible)
    if (descriptor == Descriptors.STATE) {
      val type: StateType = typeDeterminator.determinate(this.descriptor, this.signature)
      val field = SaverField(this.name, this.descriptor, this.signature, this.owner, type)
      fieldStorage.add(field)
    }
    return av
  }
}