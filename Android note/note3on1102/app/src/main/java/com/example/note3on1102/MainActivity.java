package com.example.note3on1102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver br = new MyBroadcastReceiver();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Android App :","On Create");
    }
    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.course.CUSTOM_INTENT");
        sendBroadcast(intent);
    }
}