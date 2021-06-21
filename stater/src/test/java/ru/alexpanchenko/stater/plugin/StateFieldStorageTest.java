package ru.alexpanchenko.stater.plugin;

import org.junit.Test;

import ru.alexpanchenko.stater.plugin.model.SaverField;
import ru.alexpanchenko.stater.plugin.model.StateType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StateFieldStorageTest {

  private final SaverField field = new SaverField("n", "d", "s", "o", StateType.BOOLEAN);

  @Test
  public void testAddField() {
    final StateFieldStorage fieldStorage = new StateFieldStorage();
    fieldStorage.add(field);
    assertEquals(fieldStorage.getFields().size(), 1);
    assertEquals(fieldStorage.getFields().get(0), field);
  }

  @Test
  public void testClearField() {
    final StateFieldStorage fieldStorage = new StateFieldStorage();
    fieldStorage.add(field);
    fieldStorage.add(field);
    assertEquals(fieldStorage.getFields().size(), 2);
    fieldStorage.clear();
    assertTrue(fieldStorage.getFields().isEmpty());
  }
}
