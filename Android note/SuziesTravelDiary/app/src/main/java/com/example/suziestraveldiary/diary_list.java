package com.example.suziestraveldiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class diary_list extends AppCompatActivity {
    ArrayList<Data_Model> movieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this,movieDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getMovieName(),
                        Toast.LENGTH_LONG).show();

                Intent intent4 = new Intent(getApplicationContext(),diary_detail.class);
                intent4.putExtra("travel_pic", R.drawable.ic_launcher_background);
                intent4.putExtra("title_text", myAdapter.getItem(position).getMovieName());
                intent4.putExtra("detail_text", "이것이 메시지의 vaule입니다.");
                intent4.putExtra("latitude_int", 9);
                intent4.putExtra("longitude_int", 7);

                startActivityForResult(intent4,1001);
            }


        });
    }

    public void InitializeMovieData()
    {
        movieDataList = new ArrayList<Data_Model>();

        movieDataList.add(new Data_Model(R.drawable.ic_launcher_background, "미션임파서블","15세 이상관람가"));
        movieDataList.add(new Data_Model(R.drawable.ic_launcher_background, "아저씨","19세 이상관람가"));
        movieDataList.add(new Data_Model(R.drawable.ic_launcher_background, "어벤져스","12세 이상관람가"));
    }
}