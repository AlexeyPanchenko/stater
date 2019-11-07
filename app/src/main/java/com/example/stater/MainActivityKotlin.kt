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

  @State
  private var customClass = CustomClass(1, "")

  @State
  private val listListListCustomClass: List<List<List<CustomClass>>>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    text.setText(customClass.a.toString())
    button.setOnClickListener {
      customClass.a++
      text.setText(customClass.a.toString())
    }
  }
}

