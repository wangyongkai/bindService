package com.test.yooki.testbundservice;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyApplication", "onCreate");
    }
}
