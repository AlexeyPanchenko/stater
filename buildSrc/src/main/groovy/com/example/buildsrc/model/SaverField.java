package com.example.buildsrc.model;

import com.example.buildsrc.StateType;

public class SaverField {

  public final String name;
  public final String descriptor;
  public final String owner;
  public final StateType type;
  public final String key;

  public SaverField(String name, String descriptor, String owner, StateType type) {
    this.name = name;
    this.descriptor = descriptor;
    this.owner = owner;
    this.type = type;
    this.key = owner + "_" + name;
  }

  @Override
  public String toString() {
    return "SaverField{" +
      "name='" + name + '\'' +
      ", descriptor='" + descriptor + '\'' +
      ", owner='" + owner + '\'' +
      ", type=" + type +
      ", key='" + key + '\'' +
      '}';
  }
}
