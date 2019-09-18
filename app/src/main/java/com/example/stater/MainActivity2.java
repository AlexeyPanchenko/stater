package com.example.stater;

import android.os.Bundle;
import android.widget.TextView;

import com.example.stater.lib.StateSaver;
import com.example.stater.lib.StateType;
import com.example.stater.lib.Stater;

import java.util.ArrayList;
import java.util.List;

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

    //@Stater(StateType.BOOLEAN_ARRAY)
    //private Boolean[] booleansObj;

    //@Stater(StateType.BOOLEAN)
    //private Boolean booleanObj;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text);
        textView.setText(String.valueOf(parModel.val3));
        findViewById(R.id.button).setOnClickListener(v -> {
            parModel.val3 ++;
            textView.setText(String.valueOf(parModel.val3));
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
