package com.example.suziestraveldiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class diary_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);
/*
        Intent secondIntent = getIntent();
        String travel_pic = secondIntent.getStringExtra("travel_pic");
        String title_text = secondIntent.getStringExtra("title_text");
        String detail_text = secondIntent.getStringExtra("detail_text");
        int latitude_int = secondIntent.getIntExtra("latitude_int", 0);
        int longitude_int = secondIntent.getIntExtra("longitude_int", 0);*/

    }

    public void getPosts(View view) {
        String[] columns = new String[]{"_title", "_details", "_image", "_latitude", "_longitude"};
        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null, null, null, null);
        if (c != null) {
            TextView editMultipleText = findViewById(R.id.detail_text);
            editMultipleText.setText("");
            while (c.moveToNext()) {
                String title = c.getString(0);
                String detail = c.getString(1);
                String image = c.getString(2);
                String latitude = c.getString(3);
                String longitude = c.getString(4);
                editMultipleText.append("id: " + title + "\n number: " + detail + "\n name: " + image + "\n la/long: " + latitude + longitude + "\n");
            }
            editMultipleText.append("\n Total : " + c.getCount());
            c.close();
        }
    }
}