package com.example.suziestraveldiary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Data_Model> sample;

    public MyAdapter(Context context, ArrayList<Data_Model> data) {
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
    public Data_Model getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.activity_single_list, null);

        ImageView image = (ImageView) view.findViewById(R.id.image_);
        TextView title = (TextView) view.findViewById(R.id.title_);
        TextView detail = (TextView) view.findViewById(R.id.detail_);


        Bitmap bitmap = BitmapFactory.decodeFile(sample.get(position).getImage());
        //경로를 통해 비트맵으로 전환
        image.setImageBitmap(bitmap);
        // 이미지 뷰에 비트맵 넣기
        title.setText(sample.get(position).getTitleName());
        detail.setText(sample.get(position).getDetail());


        return view;
    }
}