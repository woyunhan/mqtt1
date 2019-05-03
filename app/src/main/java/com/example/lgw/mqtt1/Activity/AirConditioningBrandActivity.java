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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AirConditioningBrandActivity extends Activity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_b)
    RecyclerView recyclerView;
    private ArrayList<String> datas = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioning_brand);
        ButterKnife.bind(this);
        initView();
        initRecyclerView();
        initData();
    }
    private void initRecyclerView() {
        adapter = new MyRecyclerViewAdapter(AirConditioningBrandActivity.this, datas);
        recyclerView.setAdapter(adapter);
        //设置LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(AirConditioningBrandActivity.this, LinearLayoutManager.VERTICAL, false));
        //        recyclerView.scrollToPosition(datas.size()-1);
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String data) {
                Intent intent = new Intent(AirConditioningBrandActivity.this, AirConditioningBrandTypesActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initView() {
        tvTitle.setText("选择空调品牌");
    }
    private void initData() {
        //准备数据集合
        datas.add("格力");
    }
    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        Intent intent = new Intent(AirConditioningBrandActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
