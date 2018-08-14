package com.test.yooki.testbundservice;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


/**
 * activity回退后，会自动执行解绑方法
 * 通过startService启动服务，第一次绑定则执行onbind,解绑执行onunbind。第二次绑定则执行onrebind。解绑执行onunbind. 按次数不按是否同一个activity。
 * 如果所有的组件都解绑了，service会ondestory。再次绑定会先执行oncreate 再执行onbind
 * 绑定service不走onStartCommand方法
 * <p>
 *
 *
 * start方式启动服务，再绑定服务，解绑后，没有ondestory,必须调用stopservice方式停止服务后才会ondestory.
 *
 * bindservice方式启动服务。stopservice stopself 均失效
 * 奇怪现象：start方式启动，再绑定，再点击stopservice，此时没有停止服务，但是再解绑后，却ondestory.
 */
public class MainActivity extends AppCompatActivity {
    MyService service;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = ((MyService.MyBinder) (iBinder)).getService();
            Log.e("MainActivity", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("MainActivity", "onServiceDisconnected");

        }
    };

    boolean f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserManager.sUserId = 2;
        Log.e("MainActivity", "UserManager.sUserId=" + UserManager.sUserId);
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memClass = activityManager.getMemoryClass();//64，以m为单位
        int largememClass = activityManager.getLargeMemoryClass();
        Log.e("MainActivity", "memClass=" + memClass + "  largememClass=" + largememClass);
        findViewById(R.id.tz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent1);
//


            }
        });

        findViewById(R.id.bund1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(new Intent(MainActivity.this, MyService.class), serviceConnection, BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });


        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // stopService(new Intent(MainActivity.this, MyService.class));
                service.stopSelf();
            }
        });


        findViewById(R.id.unbund1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
//                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
//                //  intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent1);
            }
        });


    }


}
