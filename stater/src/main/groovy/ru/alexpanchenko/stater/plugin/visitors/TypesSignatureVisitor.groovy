package ru.alexpanchenko.stater.plugin.visitors

import groovy.transform.TypeChecked
import ru.alexpanchenko.stater.plugin.utils.Const
import stater.org.objectweb.asm.signature.SignatureVisitor

/**
 * {@link SignatureVisitor} for define hierarchy of types.
 */
@TypeChecked
class TypesSignatureVisitor extends SignatureVisitor {

  List<String> types = new ArrayList<>()

  TypesSignatureVisitor() {
    super(Const.ASM_VERSION)
  }

  @Override
  void visitClassType(String name) {
    types.add(name)
    super.visitClassType(name)
  }
}
