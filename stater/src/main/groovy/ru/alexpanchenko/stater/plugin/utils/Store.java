package ru.alexpanchenko.stater.plugin.utils;

import java.util.ArrayList;

import ru.alexpanchenko.stater.plugin.model.SaverField;

public class Store {
  private static final Store ourInstance = new Store();

  public static Store getInstance() {
    return ourInstance;
  }

  public final ArrayList<SaverField> fields = new ArrayList<>();

  private Store() { }
}
