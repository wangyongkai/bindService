package com.test.yooki.testbundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    class MyBinder extends Binder {

        public MyService getService() {
            return MyService.this;
        }
    }

    //通过binder实现了 调用者（client）与 service之间的通信
    private MyBinder binder = new MyBinder();
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
           // Log.d("MyService", "Runnable=====");
            handler.postDelayed(runnable, 2000L);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService", "onBind");
        // TODO: Return the communication channel to the service.
        //  Intent intent1 = new Intent(this, Main2Activity.class);
        // intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(intent1);
        return binder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand");


        handler.post(runnable);

        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("MyService", "onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy");
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("MyService", "onRebind");
        super.onRebind(intent);
    }
}
