package com.example.note21102;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.Gravity;
import android.widget.Toast;

public class DummyService extends Service {
    public DummyService() {

    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
        Toast toast = Toast.makeText(this, "Service is started", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0); toast.show();
        return START_STICKY;
        // continue running until it is explicitly stopped.
        }
        @Override
        public void onDestroy() {
        super.onDestroy();
        Toast toast = Toast.makeText(this, "Service is destroyed", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }
}