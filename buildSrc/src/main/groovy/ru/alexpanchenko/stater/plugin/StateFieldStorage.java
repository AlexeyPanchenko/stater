package ru.alexpanchenko.stater.plugin;

import com.android.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.alexpanchenko.stater.plugin.model.SaverField;

public final class StateFieldStorage {

  private final ArrayList<SaverField> saverFields = new ArrayList<>();

  public List<SaverField> getFields() {
    return saverFields;
  }

  public void add(@NonNull SaverField field) {
    saverFields.add(field);
  }

  public void clear() {
    saverFields.clear();
  }
}
