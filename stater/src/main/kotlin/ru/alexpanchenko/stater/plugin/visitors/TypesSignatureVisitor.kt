package ru.alexpanchenko.stater.plugin.visitors

import org.objectweb.asm.signature.SignatureVisitor
import ru.alexpanchenko.stater.plugin.utils.Const

class TypesSignatureVisitor : SignatureVisitor(Const.ASM_VERSION) {

  val types = ArrayList<String>()

  override fun visitClassType(name: String) {
    types.add(name)
    super.visitClassType(name)
  }
}