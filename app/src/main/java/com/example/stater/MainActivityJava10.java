package com.example.stater;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ru.alexpanchenko.stater.State;
import ru.alexpanchenko.stater.serializer.StaterSerializer;

public class MainActivityJava10 extends AppCompatActivity {

  @State
  private CustomClass customClass = new CustomClass(1, "");

  @State
  private List<CustomClass> listCustomClass;

  @State
  private List<List<List<List<List<CustomClass>>>>> listListListCustomClass;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView textView = findViewById(R.id.text);
    textView.setText(customClass.b);

    List<List<String>> list = StaterSerializer.deserializeTyped("jjj", List.class, List.class, String.class);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  <T> T typed1(String json, Class clazz) {
    return (T) new Object();
  }

  <T> T typedMany(String json, Class... classes) {
    return (T) new Object();
  }

}
