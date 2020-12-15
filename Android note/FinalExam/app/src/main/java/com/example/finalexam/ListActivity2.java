package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity2 extends AppCompatActivity {
    ArrayList<Data_Model_map> mapDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        this.MapData();

        ListView listView = (ListView)findViewById(R.id.listView);
        final MyAdapter_map myAdapter = new MyAdapter_map(this,mapDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                Intent intent4 = new Intent(getApplicationContext(), MapsActivity.class);
                intent4.putExtra("title_text", myAdapter.getItem(position).getTitleName());
                intent4.putExtra("lat_text", myAdapter.getItem(position).getLatitude());
                intent4.putExtra("long_text", myAdapter.getItem(position).getLongitude());

                startActivityForResult(intent4, 1001);
            }


        });
    }

    public void MapData()
    {
        mapDataList = new ArrayList<Data_Model_map>();

        mapDataList.add(new Data_Model_map("신공학관","37.558058787929134","126.99840306484138"));
        mapDataList.add(new Data_Model_map("공대학사운영실","37.5589262868377","126.99889548582202"));
        mapDataList.add(new Data_Model_map("기숙사","37.558402166228966","126.99814771755447"));
    }
}