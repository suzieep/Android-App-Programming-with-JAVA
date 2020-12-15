package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class CallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        TextView myText = (TextView) findViewById(R.id.textView2);
        Uri uri = getIntent().getData();
        myText.setText(uri.toString() + " is accepted.");
    }
}