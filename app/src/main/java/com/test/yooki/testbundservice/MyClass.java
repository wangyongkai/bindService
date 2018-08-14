package com.test.yooki.testbundservice;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MyClass  implements Parcelable{


    protected MyClass(Parcel in) {
    }

    public static final Creator<MyClass> CREATOR = new Creator<MyClass>() {
        @Override
        public MyClass createFromParcel(Parcel in) {
            return new MyClass(in);
        }

        @Override
        public MyClass[] newArray(int size) {
            return new MyClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
