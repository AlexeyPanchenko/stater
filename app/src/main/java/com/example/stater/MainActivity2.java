package com.example.stater;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import ru.alexpanchenko.stater.StateSaver;
import ru.alexpanchenko.stater.StateType;
import ru.alexpanchenko.stater.Stater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@StateSaver
public class MainActivity2 extends AppCompatActivity {

  @Stater(StateType.INT)
  private int aParam = 0;

  @Stater(StateType.INT)
  private Integer aParamObj = null;

  @Stater(StateType.LONG)
  private long dParam = 5L;

  @Stater(StateType.LONG)
  private Long dParamO = 5L;

  @Stater(StateType.LONG_ARRAY)
  private long[] longs;

  @Stater(StateType.LONG_ARRAY)
  private Long[] longsO;

  @Stater(StateType.BOOLEAN)
  private boolean eParam = false;

  @Stater(StateType.STRING)
  private String bqParam = "asd";

  @Stater(StateType.PARCELABLE)
  private ParModel parModel = new ParModel("a", "2", 3, true);

  @Stater(StateType.STRING_ARRAY_LIST)
  private ArrayList<String> serModel;

  @Stater(StateType.BUNDLE)
  private Bundle bbb;

  @Stater(StateType.INT_ARRAY)
  private int[] ints;

  @Stater(StateType.INT_ARRAY_LIST)
  private ArrayList<Integer> integers;

  @Stater(StateType.STRING_ARRAY)
  private String[] strings;

  @Stater(StateType.PARCELABLE_ARRAY_LIST)
  private ArrayList<ParModel> parModels;

  @Stater(StateType.PARCELABLE_ARRAY)
  private ParModel[] parModelsArray;

  @Stater(StateType.CHAR_SEQUENCE)
  private CharSequence charSequence;

  @Stater(StateType.CHAR_SEQUENCE_ARRAY)
  private CharSequence[] charSequences;

  @Stater(StateType.CHAR_SEQUENCE_ARRAY_LIST)
  private ArrayList<CharSequence> charSequenceArrayList;

  @Stater(StateType.CHAR_SEQUENCE_ARRAY_LIST)
  private List<CharSequence> charSequenceList;

  @Stater(StateType.BOOLEAN_ARRAY)
  private boolean[] booleans;

  @Stater(StateType.BOOLEAN_ARRAY)
  private Boolean[] booleansObj;

  @Stater(StateType.BOOLEAN)
  private Boolean booleanObj = true;

  @Stater(StateType.BYTE)
  private byte aByte;

  @Stater(StateType.BYTE)
  private Byte aByteObj;

  @Stater(StateType.BYTE_ARRAY)
  private byte[] bytes;

  @Stater(StateType.BYTE_ARRAY)
  private Byte[] bytesObj;

  @Stater(StateType.CHAR)
  private char aChar;

  @Stater(StateType.CHAR)
  private Character character;

  @Stater(StateType.CHAR_ARRAY)
  private char[] chars;

  @Stater(StateType.CHAR_ARRAY)
  private Character[] characters;

  @Stater(StateType.FLOAT)
  private float aFloat;

  @Stater(StateType.FLOAT)
  private Float aFloatObj;

  @Stater(StateType.FLOAT_ARRAY)
  private float[] floats;

  @Stater(StateType.FLOAT_ARRAY)
  private Float[] floatsObj;

  @Stater(StateType.SHORT)
  private short aShort;

  @Stater(StateType.SHORT)
  private Short aShortObj;

  @Stater(StateType.SHORT_ARRAY)
  private short[] shorts;

  @Stater(StateType.SHORT_ARRAY)
  private Short[] shortObj;

  @Stater(StateType.DOUBLE)
  private double aDouble;

  @Stater(StateType.DOUBLE)
  private Double aDoubleO;

  @Stater(StateType.DOUBLE_ARRAY)
  private double[] doubles;

  @Stater(StateType.DOUBLE_ARRAY)
  private Double[] doublesO;

  @Stater(StateType.SERIALIZABLE)
  private SerModel serModels;

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

  @Stater(StateType.INT)
  private int bParam = 5;

  private int cParam = 15;

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("asd", booleansObj);
  }
}
