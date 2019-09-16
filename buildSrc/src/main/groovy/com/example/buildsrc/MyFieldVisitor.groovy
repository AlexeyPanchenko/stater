package com.example.buildsrc

import org.objectweb.asm.FieldVisitor;

class MyFieldVisitor extends FieldVisitor {

  MyFieldVisitor(int api, FieldVisitor fieldVisitor) {
    super(api, fieldVisitor)
  }


}
