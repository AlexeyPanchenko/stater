package com.example.stater;

import androidx.fragment.app.Fragment;

@StateSaver
public class Fragm extends Fragment {

  @Stater(int.class)
  private int param1 = 0;

  @Stater(int.class)
  private int param2 = 0;
}
