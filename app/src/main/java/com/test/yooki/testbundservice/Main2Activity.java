package com.test.yooki.testbundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("MainActivity", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("MainActivity", "onServiceDisconnected");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.e("Main2Activity", "UserManager.sUserId=" + UserManager.sUserId);

        findViewById(R.id.tz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, Main3Activity.class));
                // SharedPreferences sharedPreferences = Main2Activity.this.getSharedPreferences("test", MODE_PRIVATE);
                // Log.e("Main2Activity", "sharedPreferences=" + sharedPreferences.getInt("aaa", 100));
//                SharedPreferences sharedPreferences = Main2Activity.this.getSharedPreferences("test", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt("aaa", 333);
//                editor.commit();
//                Log.e("Main2Activity", "sharedPreferences=" + sharedPreferences.getInt("aaa", 100));


            }
        });


        findViewById(R.id.bund2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(new Intent(Main2Activity.this, MyService.class), serviceConnection, BIND_AUTO_CREATE);
            }
        });


        findViewById(R.id.unbund2).setOnClickListener(new View.OnClickListener() {
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
