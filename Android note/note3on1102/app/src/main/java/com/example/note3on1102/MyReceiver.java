package com.example.note3on1102;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Toast toast = Toast.makeText(context, "Something happens.", Toast.LENGTH_LONG); toast.show();
    }
}