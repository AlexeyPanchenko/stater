package com.example.buildsrc.model;

public class SaverField {

  public final String name;
  public final String descriptor;
  public final String owner;
  public final String key;

  public SaverField(String name, String descriptor, String owner) {
    this.name = name;
    this.descriptor = descriptor;
    this.owner = owner;
    this.key = owner + "_" + name;
  }

  @Override
  public String toString() {
    return "SaverField{" +
      "name='" + name + '\'' +
      ", descriptor='" + descriptor + '\'' +
      ", owner='" + owner + '\'' +
      '}';
  }
}
