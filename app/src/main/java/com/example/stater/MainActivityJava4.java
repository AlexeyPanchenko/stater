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
import ru.alexpanchenko.stater.StateType;

public class MainActivityJava4 extends AppCompatActivity {

  @State(StateType.INT)
  private int aParam = 0;

  @State(StateType.INT)
  private Integer aParamObj = null;

  @State(StateType.LONG)
  private long dParam = 5L;

  @State(StateType.LONG)
  private Long dParamO = 5L;

  @State(StateType.LONG_ARRAY)
  private long[] longs;

  @State(StateType.LONG_ARRAY)
  private Long[] longsO;

  @State(StateType.BOOLEAN)
  private boolean eParam = false;

  @State(StateType.STRING)
  private String bqParam = "asd";

  @State(StateType.PARCELABLE)
  private ParcelableModel parModel = new ParcelableModel("a", "2", 3, true);

  @State(StateType.STRING_ARRAY_LIST)
  private ArrayList<String> serModel;

  @State(StateType.BUNDLE)
  private Bundle bbb;

  @State(StateType.INT_ARRAY)
  private int[] ints;

  @State(StateType.INT_ARRAY_LIST)
  private ArrayList<Integer> integers;

  @State(StateType.STRING_ARRAY)
  private String[] strings;

  @State(StateType.PARCELABLE_ARRAY_LIST)
  private ArrayList<ParcelableModel> parModels;

  @State(StateType.PARCELABLE_ARRAY)
  private ParcelableModel[] parModelsArray;

  @State(StateType.CHAR_SEQUENCE)
  private CharSequence charSequence;

  @State(StateType.CHAR_SEQUENCE_ARRAY)
  private CharSequence[] charSequences;

  @State(StateType.CHAR_SEQUENCE_ARRAY_LIST)
  private ArrayList<CharSequence> charSequenceArrayList;

  @State(StateType.CHAR_SEQUENCE_ARRAY_LIST)
  private List<CharSequence> charSequenceList;

  @State(StateType.BOOLEAN_ARRAY)
  private boolean[] booleans;

  @State(StateType.BOOLEAN_ARRAY)
  private Boolean[] booleansObj;

  @State(StateType.BOOLEAN)
  private Boolean booleanObj = true;

  @State(StateType.BYTE)
  private byte aByte;

  @State(StateType.BYTE)
  private Byte aByteObj;

  @State(StateType.BYTE_ARRAY)
  private byte[] bytes;

  @State(StateType.BYTE_ARRAY)
  private Byte[] bytesObj;

  @State(StateType.CHAR)
  private char aChar;

  @State(StateType.CHAR)
  private Character character;

  @State(StateType.CHAR_ARRAY)
  private char[] chars;

  @State(StateType.CHAR_ARRAY)
  private Character[] characters;

  @State(StateType.FLOAT)
  private float aFloat;

  @State(StateType.FLOAT)
  private Float aFloatObj;

  @State(StateType.FLOAT_ARRAY)
  private float[] floats;

  @State(StateType.FLOAT_ARRAY)
  private Float[] floatsObj;

  @State(StateType.SHORT)
  private short aShort;

  @State(StateType.SHORT)
  private Short aShortObj;

  @State(StateType.SHORT_ARRAY)
  private short[] shorts;

  @State(StateType.SHORT_ARRAY)
  private Short[] shortObj;

  @State(StateType.DOUBLE)
  private double aDouble;

  @State(StateType.DOUBLE)
  private Double aDoubleO;

  @State(StateType.DOUBLE_ARRAY)
  private double[] doubles;

  @State(StateType.DOUBLE_ARRAY)
  private Double[] doublesO;

  @State(StateType.SERIALIZABLE)
  private SerializableModel serModels;

  @State(StateType.IBINDER)
  private IBinder iBinde;

  @State(StateType.BOOLEAN)
  private ParcelableModelKotlin parcelableModelKotlin;

  @State(StateType.INT)
  private ParcelableModelJava parcelableModelJava;

  @State(StateType.BOOLEAN)
  private SerializableModelJava serializableModelJava;

  @State(StateType.INT)
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

  @State(StateType.INT)
  private int bParam = 5;

  private int cParam = 15;

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("asd", booleansObj);
  }
}
