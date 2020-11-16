package com.example.note3on1102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
        BroadcastReceiver br;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        br = new MyReceiver();
        // 브로드캐스트 리시브 인스턴스 생성
        // 인텐트 필터 인슽턴스 생성
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction("com.course.mysecondreceiver.CUSTOM");
        this.registerReceiver(br, filter);
    }
    // 사용자가 정의한 intent를 시스템에 브로드캐스트함.
        public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.course.mysecondreceiver.CUSTOM");
        sendBroadcast(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(br);
        // 시스템의 인텐트 필터 제거
    }
}
