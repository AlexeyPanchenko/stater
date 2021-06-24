package ru.alexpanchenko.stater.serializer

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

object StaterSerializer {

  private val gson = Gson()

  @JvmStatic
  fun serialize(obj: Any?): String {
    return gson.toJson(obj)
  }

  /**
   * @param json - string presentation of object
   * @param currentClass - object classes that you need.
   * @return object of [T] type.
   */
  @JvmStatic
  fun <T> deserialize(json: String?, currentClass: Class<T>?): T {
    return gson.fromJson(json, TypeToken.getParameterized(currentClass).type)
  }

  /**
   * [.deserialize] for typed object.
   * @param json - string presentation of object
   * @param classes - object classes. Add all classes of your typed object by order:
   * `List<List<String>> -> deserializeTyped(json, List.class, List.class, String.class)`
   * @return object of [T] type.
   */
  @JvmStatic
  fun <T> deserializeTyped(json: String?, vararg classes: Class<*>?): T {
    val list: LinkedList<Class<*>> = LinkedList(listOf(*classes))
    return gson.fromJson(json, getType(list))
  }

  private fun getType(classes: Queue<Class<*>>): Type? {
    val currentClass: Class<*> = classes.poll() ?: return null
    val type: Type = getType(classes) ?: return TypeToken.getParameterized(currentClass).type
    return TypeToken.getParameterized(currentClass, type).type
  }
}