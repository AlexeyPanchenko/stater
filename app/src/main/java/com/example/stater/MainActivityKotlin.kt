package com.example.stater

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.alexpanchenko.stater.State

class MainActivityKotlin : AppCompatActivity() {

  @State
  var a: Int = 2

  @State
  private val serModels: SerializableModel? = null

  @State
  private var intVar: Int = 2

  @State
  private val floats: FloatArray = floatArrayOf(1.0f)

  @State
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

