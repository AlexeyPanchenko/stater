package ru.alexpanchenko.stater.plugin.visitors

import groovy.transform.TypeChecked
import org.objectweb.asm.signature.SignatureVisitor
import ru.alexpanchenko.stater.plugin.utils.Const;

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
