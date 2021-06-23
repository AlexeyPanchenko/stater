package ru.alexpanchenko.stater.plugin

import ru.alexpanchenko.stater.plugin.model.SaverField
import java.util.*

class StateFieldStorage {

  private val saverFields = ArrayList<SaverField>()

  val fields: List<SaverField> = saverFields

  fun add(field: SaverField) {
    saverFields.add(field)
  }

  fun clear() {
    saverFields.clear()
  }
}