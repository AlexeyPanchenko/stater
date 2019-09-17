package com.example.stater;

import android.os.Bundle;
import android.widget.TextView;

import com.example.stater.lib.StateSaver;
import com.example.stater.lib.StateType;
import com.example.stater.lib.Stater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@StateSaver
public class MainActivity2 extends AppCompatActivity {

    @Stater(StateType.INT)
    private int aParam = 0;

    @Stater(StateType.LONG)
    private long dParam = 5L;

    @Stater(StateType.BOOLEAN)
    private boolean eParam = false;

    @Stater(StateType.STRING)
    private String bqParam = "asd";

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

    @Stater(StateType.INT)
    private int bParam = 5;

    private int cParam = 15;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
