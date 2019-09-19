package com.example.stater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.alexpanchenko.stater.Stater
import ru.alexpanchenko.stater.StateType
import ru.alexpanchenko.stater.State

@Stater
class MainActivity : AppCompatActivity() {

  @State(StateType.INT)
  var a: Int = 2

  @State(StateType.SERIALIZABLE)
  private val serModels: SerModel? = null

  @State(StateType.INT)
  private val aParamObj: Int? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}

