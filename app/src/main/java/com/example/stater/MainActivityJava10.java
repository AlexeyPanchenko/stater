package com.example.stater;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ru.alexpanchenko.stater.State;

public class MainActivityJava10 extends AppCompatActivity {

  @State
  private CustomClass customClass;

  @State
  private List<List<List<CustomClass>>> customClasssss;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView textView = findViewById(R.id.text);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
  }
}
