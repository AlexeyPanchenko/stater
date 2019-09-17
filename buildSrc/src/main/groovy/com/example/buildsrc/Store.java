package com.example.buildsrc;

import com.example.buildsrc.model.SaverField;

import java.util.ArrayList;

public class Store {
  private static final Store ourInstance = new Store();

  public static Store getInstance() {
    return ourInstance;
  }

  public final ArrayList<SaverField> fields = new ArrayList<>();

  private Store() {
  }
}
