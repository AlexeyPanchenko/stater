package ru.alexpanchenko.stater.serializer;

import java.util.Objects;

public class CustomClass {
  public final int count;
  public final String text;

  public CustomClass(int count, String text) {
    this.count = count;
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomClass that = (CustomClass) o;
    return count == that.count && Objects.equals(text, that.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, text);
  }

  @Override
  public String toString() {
    return "CustomClass{" +
      "count=" + count +
      ", text='" + text + '\'' +
      '}';
  }
}
