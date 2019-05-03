package com.example.lgw.mqtt1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lgw.mqtt1.R;
import com.example.lgw.mqtt1.Util.AirVerification;
import com.example.lgw.mqtt1.adapter.AirConditioningBrandTypesAdapter;
import com.example.lgw.mqtt1.adapter.BaseAdapter;
import com.example.lgw.mqtt1.bean.BrandTypes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AirConditioningBrandTypesActivity extends Activity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_bt)
    RecyclerView recyclerView;
    private List<BrandTypes> datas = new ArrayList<BrandTypes>();
    private AirConditioningBrandTypesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioning_brand_types);
        ButterKnife.bind(this);
        initView();
        initData();
        initRecyView();
    }
    private void initView() {
        tvTitle.setText("格力空调型号");
    }

    private void initData() {
        //准备数据集合
        datas.add(new BrandTypes(R.drawable.air_conditioning_noselect, "格力空调", "tv_sony_rm_sd021"));
        datas.add(new BrandTypes(R.drawable.air_conditioning_noselect, "格力空调", "ddwqfefdwd"));
        datas.add(new BrandTypes(R.drawable.air_conditioning_noselect, "格力空调", "ddefewqdwd"));
    }

    private void initRecyView() {
        //设置LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(AirConditioningBrandTypesActivity.this, LinearLayoutManager.VERTICAL, false));
        //        recyclerView.scrollToPosition(datas.size()-1);
        //添加RecyclerView的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(AirConditioningBrandTypesActivity.this, DividerItemDecoration.VERTICAL));

        adapter = new AirConditioningBrandTypesAdapter(AirConditioningBrandTypesActivity.this, R.layout.item_air_conditioning_brand_types, datas);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClikListener(new MyOnclick());
    }
    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        Intent intent = new Intent(AirConditioningBrandTypesActivity.this, AirConditioningBrandActivity.class);
        startActivity(intent);
    }
    private class MyOnclick implements BaseAdapter.OnItemClikListener {
        @Override
        public void OnItemClick(View view, int pos) {
            Intent intent = new Intent(AirConditioningBrandTypesActivity.this, AirVerification.class);
            startActivity(intent);
        }
    }
}
