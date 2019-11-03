package ru.alexpanchenko.stater.serializer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Serializer {

  private static Serializer instance;
  private final Gson gson = new Gson();

  private Serializer() { }

  public static Serializer getInstance() {
    if (instance == null) {
      instance = new Serializer();
    }
    return instance;
  }

  public static String serialize(Object obj) {
    return getInstance().gson.toJson(obj);
  }

  /**
   * @param json - string presentation of object
   * @param classes - object classes. First {@link Class} is require for define type.
   *                If you have a generic type, add all classes by order:
   *                {@code List<List<String>> -> deserialize(json, List.class, List.class, String.class)}
   * @return object of {@link T} type.
   */
  public static <T> T deserialize(String json, Class... classes) {
    LinkedList<Class> list = new LinkedList<>(Arrays.asList(classes));
    return getInstance().gson.fromJson(json, getType(list));
  }

  private static Type getType(Queue<Class> classes) {
    Class currentClass = classes.poll();
    if (currentClass == null) {
      return null;
    }
    Type type = getType(classes);
    if (type == null) {
      return TypeToken.getParameterized(currentClass).getType();
    }
    return TypeToken.getParameterized(currentClass, type).getType();
  }
}
