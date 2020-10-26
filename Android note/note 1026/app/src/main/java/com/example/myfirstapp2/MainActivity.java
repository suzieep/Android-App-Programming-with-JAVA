package com.example.myfirstapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.MyFirstApp2.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
    } /** Called when the user taps the Send button */

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);//페이지 간 값 전달 모듈 intent
        EditText editText = (EditText) findViewById(R.id.editText); //edittext 텍스트 입력 수정
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message); startActivity(intent);
    }
}