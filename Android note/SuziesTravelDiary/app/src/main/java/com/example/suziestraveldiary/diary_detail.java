package com.example.suziestraveldiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class diary_detail extends AppCompatActivity {
    Button create2Button;
    String latitude;
    String longitude;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);

        TextView title_view = findViewById(R.id.title_text);
        TextView detail_view = findViewById(R.id.detail_text);
        TextView latitude_view = findViewById(R.id.latitude_text);
        TextView longitude_view = findViewById(R.id.longitude_text);
        ImageView image_view = findViewById(R.id.photo_img);

        title_view.setText("");
        detail_view.setText("");
        longitude_view.setText("");
        latitude_view.setText("");

        Intent secondIntent = getIntent();
        String travel_pic = secondIntent.getStringExtra("travel_pic");
        String title_text = secondIntent.getStringExtra("title_text");
        String detail_text = secondIntent.getStringExtra("detail_text");
        String latitude_int = secondIntent.getStringExtra("latitude_int");
        String longitude_int = secondIntent.getStringExtra("longitude_int");
        latitude=latitude_int;
        longitude=longitude_int;
        title = title_text;
        title_view.setText(title_text);
        detail_view.setText(detail_text);
        longitude_view.setText(longitude_int);
        latitude_view.setText(latitude_int);
        Bitmap bitmap = StringToBitmap(travel_pic);
        //경로를 통해 비트맵으로 전환
        image_view.setImageBitmap(bitmap);

        create2Button = (Button) findViewById(R.id.map_button);
        create2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), activity_diary_map.class);
                intent2.putExtra("latitude_int", latitude);
                intent2.putExtra("longitude_int", longitude);
                intent2.putExtra("title_text", title);


                startActivityForResult(intent2, 1001);
            }
        });
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

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}