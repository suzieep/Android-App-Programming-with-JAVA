package com.example.note1102;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String message = "Android App Message : ";
    TextView text;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        Log.d(message, " onCreate() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("onCreate !!!");
    }
    /** Called when the activity is about to become visible. */

    @Override
    protected void onStart() { super.onStart();
    Log.d(message, " onStart() operated!!");
    text = (TextView)findViewById(R.id.textView);
    text.setText("onStart !!!"); }
    /** Called when the activity has become visible. */

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(message, " onResume() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("onResume !!!");
    }
    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(message, " onPause() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("onPause !!!");
    } /** Called when the activity is no longer visible. */

    @Override protected void onStop() {
        super.onStop();
        Log.d(message, " onStop() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("onStop !!!");
    }
    /** Called just before the activity is destroyed. */
    @Override public void onDestroy() {
        super.onDestroy();
        Log.d(message, " onDestroy() operated!!");
        text = (TextView)findViewById(R.id.textView);
        text.setText("onDestroy !!!");
    }
}