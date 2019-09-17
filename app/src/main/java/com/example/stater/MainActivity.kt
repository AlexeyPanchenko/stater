package com.example.stater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment

@StateSaver
class MainActivity : AppCompatActivity() {

    @Stater(Int::class)
    var a: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

@StateSaver
class F : Fragment() {

    @Stater(Int::class)
    var a: Int = 2

    @Stater(Int::class)
    var b: Int = 2

    @Stater(Int::class)
    var c: Int = 0


}
