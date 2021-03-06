package com.example.stater;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableModel implements Parcelable {

  public String val1;
  public String val2;
  public int val3;
  public boolean val4;

  public ParcelableModel(String val1, String val2, int val3, boolean val4) {
    this.val1 = val1;
    this.val2 = val2;
    this.val3 = val3;
    this.val4 = val4;
  }

  @Override
  public int describeContents() { return 0; }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.val1);
    dest.writeString(this.val2);
    dest.writeInt(this.val3);
    dest.writeByte(this.val4 ? (byte) 1 : (byte) 0);
  }

  public ParcelableModel() {}

  protected ParcelableModel(Parcel in) {
    this.val1 = in.readString();
    this.val2 = in.readString();
    this.val3 = in.readInt();
    this.val4 = in.readByte() != 0;
  }

  public static final Creator<ParcelableModel> CREATOR = new Creator<ParcelableModel>() {
    @Override
    public ParcelableModel createFromParcel(Parcel source) {return new ParcelableModel(source);}

    @Override
    public ParcelableModel[] newArray(int size) {return new ParcelableModel[size];}
  };
}
