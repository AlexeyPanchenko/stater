package com.example.stater;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ru.alexpanchenko.stater.State;

public class MainActivityJava4 extends AppCompatActivity {

  @State
  private int aParam = 0;

  @State
  private Integer aParamObj = null;

  @State
  private long dParam = 5L;

  @State
  private Long dParamO = 5L;

  @State
  private long[] longs;

  @State
  private Long[] longsO;

  @State
  private boolean eParam = false;

  @State
  private String bqParam = "asd";

  @State
  private ParcelableModel parModel = new ParcelableModel("a", "2", 3, true);

  @State
  private ArrayList<String> serModel;

  @State
  private Bundle bbb;

  @State
  private int[] ints;

  @State
  private ArrayList<Integer> integers;

  @State
  private String[] strings;

  @State
  private ArrayList<ParcelableModel> parModels;

  @State
  private ParcelableModel[] parModelsArray;

  @State
  private CharSequence charSequence;

  @State
  private CharSequence[] charSequences;

  @State
  private ArrayList<CharSequence> charSequenceArrayList;

  @State
  private List<CharSequence> charSequenceList;

  @State
  private boolean[] booleans;

  @State
  private Boolean[] booleansObj;

  @State
  private Boolean booleanObj = true;

  @State
  private byte aByte;

  @State
  private Byte aByteObj;

  @State
  private byte[] bytes;

  @State
  private Byte[] bytesObj;

  @State
  private char aChar;

  @State
  private Character character;

  @State
  private char[] chars;

  @State
  private Character[] characters;

  @State
  private float aFloat;

  @State
  private Float aFloatObj;

  @State
  private float[] floats;

  @State
  private Float[] floatsObj;

  @State
  private short aShort;

  @State
  private Short aShortObj;

  @State
  private short[] shorts;

  @State
  private Short[] shortObj;

  @State
  private double aDouble;

  @State
  private Double aDoubleO;

  @State
  private double[] doubles;

  @State
  private Double[] doublesO;

  @State
  private SerializableModel serModels;

  @State
  private IBinder iBinde;

  @State
  private ParcelableModelKotlin parcelableModelKotlin;

  @State
  private ParcelableModelJava parcelableModelJava;

  @State
  private SerializableModelJava serializableModelJava;

  @State
  private SerializableModelKotlin serializableModelKotlin;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView textView = findViewById(R.id.text);
    textView.setText(String.valueOf(dParamO));
    findViewById(R.id.button).setOnClickListener(v -> {
      dParamO++;
      textView.setText(String.valueOf(dParamO));
      booleansObj = new Boolean[] {true, true};
    });
    Log.d("TTT", "booleansObj = " + Arrays.toString(booleansObj));
  }

  @State
  private int bParam = 5;

  private int cParam = 15;

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("asd", booleansObj);
  }
}
