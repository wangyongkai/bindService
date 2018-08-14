package com.test.yooki.testbundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


/**
 * Running activities (most recent first):
 * TaskRecord{14dacfad0 #1045 A=com.test.yooki.testbundservice U=0 StackId=1 sz=1}
 * Run #2: ActivityRecord{4857feed0 u0 com.test.yooki.testbundservice/.Main3Activity t1045}
 * TaskRecord{11181f2d0 #1044 A=com.test.yooki.testbundservice U=0 StackId=1 sz=1}
 * Run #1: ActivityRecord{1d37566d0 u0 com.test.yooki.testbundservice/.Main2Activity t1044}
 * TaskRecord{f4ca1ead0 #1043 A=com.test.yooki.testbundservice U=0 StackId=1 sz=1}
 * Run #0: ActivityRecord{62a05ded0 u0 com.test.yooki.testbundservice/.MainActivity t1043}
 * <p>
 * mResumedActivity: ActivityRecord{4857feed0 u0 com.test.yooki.testbundservice/.Main3Activity t1045}
 * mLastPausedActivity: ActivityRecord{1d37566d0 u0 com.test.yooki.testbundservice/.Main2Activity t1044}
 */
public class Main3Activity extends AppCompatActivity {

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("Main3Activity", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("Main3Activity", "onServiceDisconnected");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Log.d("Main3Activity", "onCreate");
        findViewById(R.id.bund3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(new Intent(Main3Activity.this, MyService.class), serviceConnection, BIND_AUTO_CREATE);
                //  finish();
            }
        });


        findViewById(R.id.unbund3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
//                Intent intent1 = new Intent(Main3Activity.this, Main2Activity.class);
//                //  intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main3Activity", "onDestroy");
    }
}
