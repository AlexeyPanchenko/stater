package com.example.stater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.stater.lib.StateSaver
import com.example.stater.lib.StateType
import com.example.stater.lib.Stater

@StateSaver
class MainActivity : AppCompatActivity() {

    @Stater(StateType.INT)
    var a: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

