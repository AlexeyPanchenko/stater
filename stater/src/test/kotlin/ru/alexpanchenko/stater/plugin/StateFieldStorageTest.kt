package ru.alexpanchenko.stater.plugin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.alexpanchenko.stater.plugin.model.SaverField
import ru.alexpanchenko.stater.plugin.model.StateType

class StateFieldStorageTest {

  private val field = SaverField("n", "d", "s", "o", StateType.BOOLEAN)

  @Test
  fun testAddField() {
    val fieldStorage = StateFieldStorage()
    fieldStorage.add(field)
    assertEquals(fieldStorage.fields.size.toLong(), 1)
    assertEquals(fieldStorage.fields[0], field)
  }

  @Test
  fun testClearField() {
    val fieldStorage = StateFieldStorage()
    fieldStorage.add(field)
    fieldStorage.add(field)
    assertEquals(fieldStorage.fields.size.toLong(), 2)
    fieldStorage.clear()
    assertTrue(fieldStorage.fields.isEmpty())
  }
}