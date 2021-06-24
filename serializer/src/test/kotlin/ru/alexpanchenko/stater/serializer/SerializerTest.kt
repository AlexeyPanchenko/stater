package ru.alexpanchenko.stater.serializer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SerializerTest {

  @Test
  public void testSerializeClass() {
    CustomClass customClass = new CustomClass(1, "text");
    String serializedClass = StaterSerializer.serialize(customClass);
    assertEquals("{\"count\":1,\"text\":\"text\"}", serializedClass);
  }

  @Test
  public void testSerializeListWithClass() {
    List<CustomClass> list = new ArrayList<>();
    CustomClass customClass = new CustomClass(1, "text");
    list.add(customClass);
    String deserializedClass = StaterSerializer.serialize(list);
    assertEquals("[{\"count\":1,\"text\":\"text\"}]", deserializedClass);
  }

  @Test
  public void testDeserializeClass() {
    CustomClass customClass = new CustomClass(1, "text");
    String customClassString = StaterSerializer.serialize(customClass);
    CustomClass deserializedClass = StaterSerializer.deserialize(customClassString, CustomClass.class);
    assertEquals(customClass, deserializedClass);
  }

  @Test
  public void testDeserializeListClasses() {
    List<CustomClass> list = new ArrayList<>();
    list.add(new CustomClass(1, "text1"));
    list.add(new CustomClass(2, "text2"));
    String listString = StaterSerializer.serialize(list);
    List<CustomClass> deserializedList = StaterSerializer.deserializeTyped(listString, List.class, CustomClass.class);
    assertEquals(deserializedList.size(), 2);
    assertEquals(deserializedList, list);
  }

  @Test
  public void testDeserializeListListClasses() {
    List<CustomClass> list = new ArrayList<>();
    list.add(new CustomClass(1, "text1"));
    list.add(new CustomClass(2, "text2"));
    List<List<CustomClass>> lists = new ArrayList<>();
    lists.add(list);
    String listString = StaterSerializer.serialize(lists);
    List<List<CustomClass>> deserializedList = StaterSerializer.deserializeTyped(
      listString, List.class, List.class, CustomClass.class
    );
    assertEquals(deserializedList.size(), 1);
    assertEquals(deserializedList.get(0).size(), 2);
    assertEquals(deserializedList.get(0), list);
  }

  @Test
  public void testDeserializeListListListClasses() {
    List<CustomClass> list = new ArrayList<>();
    list.add(new CustomClass(1, "text1"));
    list.add(new CustomClass(2, "text2"));
    List<List<CustomClass>> lists = new ArrayList<>();
    lists.add(list);
    List<List<List<CustomClass>>> lists2 = new ArrayList<>();
    lists2.add(lists);
    String listString = StaterSerializer.serialize(lists2);
    List<List<List<CustomClass>>> deserializedList = StaterSerializer.deserializeTyped(
      listString, List.class, List.class, List.class, CustomClass.class
    );
    assertEquals(deserializedList.size(), 1);
    assertEquals(deserializedList.get(0).size(), 1);
    assertEquals(deserializedList.get(0).get(0).size(), 2);
    CustomClass deserializedClass = deserializedList.get(0).get(0).get(0);
    assertEquals(deserializedClass, list.get(0));
  }
}
