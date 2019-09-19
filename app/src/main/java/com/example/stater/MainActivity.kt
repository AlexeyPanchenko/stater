package com.example.stater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
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
  private var intVar: Int = 2

  @State(StateType.FLOAT_ARRAY)
  private val floats: FloatArray = floatArrayOf(1.0f)

  @State(StateType.FLOAT_ARRAY)
  private val floatsObj: Array<Float?> = arrayOf()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    text.setText(intVar.toString())
    button.setOnClickListener {
      intVar++
      text.setText(intVar.toString())
    }
  }
}

