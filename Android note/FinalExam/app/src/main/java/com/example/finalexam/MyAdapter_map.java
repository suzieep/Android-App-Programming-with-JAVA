package com.example.finalexam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter_map extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Data_Model_map> sample;

    public MyAdapter_map(Context context, ArrayList<Data_Model_map> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Data_Model_map getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.activity_map_single, null);

        TextView title = (TextView) view.findViewById(R.id.title_);
        TextView latitude = (TextView) view.findViewById(R.id.lat_);
        TextView longitude = (TextView) view.findViewById(R.id.long_);



        title.setText(sample.get(position).getTitleName());
        latitude.setText("위도 : "+sample.get(position).getLatitude());
        longitude.setText("경도 : "+sample.get(position).getLongitude());


        return view;
    }
}