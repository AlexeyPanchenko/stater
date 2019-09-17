package com.example.stater;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@StateSaver
public class MainActivity2 extends AppCompatActivity {

    @Stater(int.class)
    private int aParam = 0;

    @Stater(int.class)
    private int dParam = 5;

    @Stater(int.class)
    private int eParam = 5;

    @Stater(int.class)
    private int bqParam = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView =  findViewById(R.id.text);
        textView.setText(String.valueOf(aParam));
        findViewById(R.id.button).setOnClickListener(v -> {
            aParam ++;
            textView.setText(String.valueOf(aParam));
        });
    }

    @Stater(int.class)
    private int bParam = 5;

    private int cParam = 15;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
