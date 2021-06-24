package ru.alexpanchenko.stater.serializer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public final class StaterSerializer {

  private static StaterSerializer instance;
  private final Gson gson = new Gson();

  private StaterSerializer() { }

  static StaterSerializer getInstance() {
    if (instance == null) {
      instance = new StaterSerializer();
    }
    return instance;
  }

  public static String serialize(Object obj) {
    return getInstance().gson.toJson(obj);
  }

  /**
   * @param json - string presentation of object
   * @param currentClass - object classes that you need.
   * @return object of {@link T} type.
   */
  public static <T> T deserialize(String json, Class<T> currentClass) {
    return getInstance().gson.fromJson(json, TypeToken.getParameterized(currentClass).getType());
  }

  /**
   * {@link #deserialize} for typed object.
   * @param json - string presentation of object
   * @param classes - object classes. Add all classes of your typed object by order:
   *                {@code List<List<String>> -> deserializeTyped(json, List.class, List.class, String.class)}
   * @return object of {@link T} type.
   */
  public static <T> T deserializeTyped(String json, Class... classes) {
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
