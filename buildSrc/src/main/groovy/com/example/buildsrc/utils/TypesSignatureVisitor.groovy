package com.example.buildsrc.utils

import org.objectweb.asm.signature.SignatureVisitor;

/**
 * {@link SignatureVisitor} for define hierarchy of types.
 */
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
