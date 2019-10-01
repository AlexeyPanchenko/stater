package com.example.buildsrc.model;

import java.util.Objects;

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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SaverField that = (SaverField) o;
    return name.equals(that.name) &&
      descriptor.equals(that.descriptor) &&
      owner.equals(that.owner) &&
      type == that.type &&
      key.equals(that.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, descriptor, owner, type, key);
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
