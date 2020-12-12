package com.example.userinterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 리싸이클러 뷰에 레이아웃을 설정해 준다. 이 레이아웃은 xml로 만들어 준 내용을 사용한다.
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager); // 아이템들을 목록으로 만들어 준다. 목록은 ArrayList 객체를 사용한다.
        ArrayList<Schools> schoolsInfo = new ArrayList<>();
        schoolsInfo.add(new Schools(R.drawable.dgu_img, "동국대학교", "http://www.dongguk.edu/mbs/kr/index.jsp"));
        schoolsInfo.add(new Schools(R.drawable.seoul_img, "서울대학교", "http://www.snu.ac.kr/index.html"));
        schoolsInfo.add(new Schools(R.drawable.yonsei_img, "연세대학교", "https://www.yonsei.ac.kr/"));
        schoolsInfo.add(new Schools(R.drawable.korea_univ, "고려대학교", "http://www.korea.ac.kr/"));
        schoolsInfo.add(new Schools(R.drawable.dgu_img, "2017112167 이수진", "http://www.korea.ac.kr/"));// 목록을 어댑터에 연결해 준다.
        MyAdapter myAdapter = new MyAdapter(schoolsInfo); // 어댑터를 뷰에 연결해 준다.
        myRecyclerView.setAdapter(myAdapter);
    }
}