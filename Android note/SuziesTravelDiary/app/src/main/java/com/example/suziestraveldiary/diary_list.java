package com.example.suziestraveldiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class diary_list extends AppCompatActivity {
    ArrayList<Data_Model> movieDataList;
    ArrayList<Data_Model> travelDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        this.getPosts();

        ListView listView = (ListView) findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this, travelDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getTitleName(),
                        Toast.LENGTH_LONG).show();

                Intent intent4 = new Intent(getApplicationContext(), diary_detail.class);
                intent4.putExtra("travel_pic", myAdapter.getItem(position).getImage());
                intent4.putExtra("title_text", myAdapter.getItem(position).getTitleName());
                intent4.putExtra("detail_text", myAdapter.getItem(position).getDetail());
                intent4.putExtra("latitude_int", myAdapter.getItem(position).getLatitude());
                intent4.putExtra("longitude_int", myAdapter.getItem(position).getLongitude());

                startActivityForResult(intent4, 1001);
            }


        });
    }

    public void getPosts() {
        String[] columns = new String[]{"_title", "_details", "_image", "_latitude", "_longitude"};
        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null, null, null, null);
        if (c != null) {
            travelDataList = new ArrayList<Data_Model>();

            while (c.moveToNext()) {
                String title = c.getString(0);
                String detail = c.getString(1);
                String image = c.getString(2);
                String latitude = c.getString(3);
                String longitude = c.getString(4);
                travelDataList.add(new Data_Model(image, title, detail, latitude, longitude));


            }
            c.close();
        }
    }
}
