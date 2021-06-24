package ru.alexpanchenko.stater.plugin.model

data class SaverField(
  val name: String,
  val descriptor: String,
  val signature: String?,
  val owner: String?,
  val type: StateType
) {

  val key: String = owner + "_" + name
}