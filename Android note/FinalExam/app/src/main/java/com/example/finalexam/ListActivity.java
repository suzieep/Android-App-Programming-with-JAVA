package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ArrayList<Data_Model_call> callDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.CallData();

        ListView listView = (ListView) findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this, callDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+myAdapter.getItem(position).getDetail()));
                startActivity(intent);

            }


        });
    }
    public void CallData()
    {
        callDataList = new ArrayList<Data_Model_call>();

        callDataList.add(new Data_Model_call("학사운영실","02-2260-3833"));
        callDataList.add(new Data_Model_call("정보통신공","02-2260-3833"));
        callDataList.add(new Data_Model_call("학장실","02-2260-3833"));
    }

}