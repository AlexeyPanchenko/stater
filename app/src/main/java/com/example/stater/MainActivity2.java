package com.example.stater;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Stater(int.class)
    private int aParam = 0;

    //@Override
    //protected void onCreate(@Nullable Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);
    //    TextView textView =  findViewById(R.id.text);
    //    textView.setText(String.valueOf(aParam));
    //    findViewById(R.id.button).setOnClickListener(v -> {
    //        aParam ++;
    //        textView.setText(String.valueOf(aParam));
    //    });
    //}

    //@Override
    //protected void onSaveInstanceState(@NonNull Bundle outState) {
    //    super.onSaveInstanceState(outState);
    //}
}
