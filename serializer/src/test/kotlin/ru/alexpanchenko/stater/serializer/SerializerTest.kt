package ru.alexpanchenko.stater.serializer

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.alexpanchenko.stater.serializer.StaterSerializer.deserialize
import ru.alexpanchenko.stater.serializer.StaterSerializer.deserializeTyped
import ru.alexpanchenko.stater.serializer.StaterSerializer.serialize
import java.util.*

class SerializerTest {
  @Test
  fun testSerializeClass() {
    val customClass = CustomClass(1, "text")
    val serializedClass: String = serialize(customClass)
    assertEquals("{\"count\":1,\"text\":\"text\"}", serializedClass)
  }

  @Test
  fun testSerializeListWithClass() {
    val list: MutableList<CustomClass> = ArrayList()
    val customClass = CustomClass(1, "text")
    list.add(customClass)
    val deserializedClass = serialize(list)
    assertEquals("[{\"count\":1,\"text\":\"text\"}]", deserializedClass)
  }

  @Test
  fun testDeserializeClass() {
    val customClass = CustomClass(1, "text")
    val customClassString: String = serialize(customClass)
    val deserializedClass: CustomClass = deserialize(customClassString, CustomClass::class.java)
    assertEquals(customClass, deserializedClass)
  }

  @Test
  fun testDeserializeListClasses() {
    val list: MutableList<CustomClass> = ArrayList()
    list.add(CustomClass(1, "text1"))
    list.add(CustomClass(2, "text2"))
    val listString: String = serialize(list)
    val deserializedList: List<CustomClass> = deserializeTyped(listString, MutableList::class.java, CustomClass::class.java)
    assertEquals(deserializedList.size.toLong(), 2)
    assertEquals(deserializedList, list)
  }

  @Test
  fun testDeserializeListListClasses() {
    val list: MutableList<CustomClass> = ArrayList()
    list.add(CustomClass(1, "text1"))
    list.add(CustomClass(2, "text2"))
    val lists: MutableList<List<CustomClass>> = ArrayList()
    lists.add(list)
    val listString: String = serialize(lists)
    val deserializedList: List<List<CustomClass>> = deserializeTyped(
      listString, MutableList::class.java, MutableList::class.java, CustomClass::class.java
    )
    assertEquals(deserializedList.size.toLong(), 1)
    assertEquals(deserializedList[0].size.toLong(), 2)
    assertEquals(deserializedList[0], list)
  }

  @Test
  fun testDeserializeListListListClasses() {
    val list: MutableList<CustomClass> = ArrayList()
    list.add(CustomClass(1, "text1"))
    list.add(CustomClass(2, "text2"))
    val lists: MutableList<List<CustomClass>> = ArrayList()
    lists.add(list)
    val lists2: MutableList<List<List<CustomClass>>> = ArrayList()
    lists2.add(lists)
    val listString: String = serialize(lists2)
    val deserializedList: List<List<List<CustomClass>>> = deserializeTyped(
      listString,
      MutableList::class.java,
      MutableList::class.java,
      MutableList::class.java,
      CustomClass::class.java
    )
    assertEquals(deserializedList.size.toLong(), 1)
    assertEquals(deserializedList[0].size.toLong(), 1)
    assertEquals(deserializedList[0][0].size.toLong(), 2)
    val deserializedClass = deserializedList[0][0][0]
    assertEquals(deserializedClass, list[0])
  }
}