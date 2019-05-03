package com.example.lgw.mqtt1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lgw.mqtt1.R;
import com.example.lgw.mqtt1.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;

public class FanBrandActivity extends Activity {
    private TextView tv_title;
    private ImageView iv_back;
    private RecyclerView recyclerView;
    private ArrayList<String> datas=new ArrayList<>();
    private MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_brand);
        initView();
        initRecyclerView();
        initEvent();
        initData();

    }

    private void initEvent() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FanBrandActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        adapter = new MyRecyclerViewAdapter(FanBrandActivity.this, datas);
        recyclerView.setAdapter(adapter);
        //设置LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(FanBrandActivity.this,LinearLayoutManager.VERTICAL,false));
        //        recyclerView.scrollToPosition(datas.size()-1);
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String data) {
                Intent intent = new Intent(FanBrandActivity.this, FanBrandTypesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        recyclerView=(RecyclerView)findViewById(R.id.rv_b);
        tv_title=(TextView)findViewById(R.id.tv_title);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        tv_title.setText("选择风扇品牌");

    }

    private void initData() {
        //准备数据集合

        datas.add("艾美克");

    }
}