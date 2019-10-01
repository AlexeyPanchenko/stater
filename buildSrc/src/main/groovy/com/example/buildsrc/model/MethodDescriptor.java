package com.example.buildsrc.model;

public class MethodDescriptor {
  public final String method;
  public final String descriptor;

  public MethodDescriptor(String method, String descriptor) {
    this.method = method;
    this.descriptor = descriptor;
  }

  public boolean isValid() {
    return method != null && !method.isEmpty() && descriptor != null && !descriptor.isEmpty();
  }

  @Override
  public String toString() {
    return "MethodDescriptor{" +
      "method='" + method + '\'' +
      ", descriptor='" + descriptor + '\'' +
      '}';
  }
}
