package com.example.stater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import ru.alexpanchenko.stater.StateSaver
import ru.alexpanchenko.stater.StateType
import ru.alexpanchenko.stater.Stater
import java.io.Serializable

@StateSaver
class MainActivity : AppCompatActivity() {

  @Stater(StateType.INT)
  var a: Int = 2

  @Stater(StateType.SERIALIZABLE)
  private val serModels: SerModel? = null

  @Stater(StateType.INT)
  private val aParamObj: Int? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}

