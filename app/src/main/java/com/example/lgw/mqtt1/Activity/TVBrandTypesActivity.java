package com.example.lgw.mqtt1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lgw.mqtt1.R;
import com.example.lgw.mqtt1.Util.TVJudge;
import com.example.lgw.mqtt1.adapter.BaseAdapter;
import com.example.lgw.mqtt1.adapter.TvBrandTypesAdapter;
import com.example.lgw.mqtt1.bean.BrandTypes;

import java.util.ArrayList;
import java.util.List;

public class TVBrandTypesActivity extends Activity{
    private TextView tv_title;
    private ImageView iv_back;
    private RecyclerView recyclerView;
    private List<BrandTypes> datas=new ArrayList<BrandTypes>();
    private TvBrandTypesAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_types);
        initView();
        initData();
        initEvent();
        initRecyView();


    }

    private void initEvent() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TVBrandTypesActivity.this, TVBrandActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        recyclerView=(RecyclerView)findViewById(R.id.rv_bt);
        tv_title=(TextView)findViewById(R.id.tv_title);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        tv_title.setText("索尼电视型号");

    }

    private void initData() {
        //准备数据集合

        datas.add(new BrandTypes(R.drawable.tv_noselect,"索尼电视","tv_sony_rm_sd021"));
        datas.add(new BrandTypes(R.drawable.tv_noselect,"索尼电视","ddwqfefdwd"));
        datas.add(new BrandTypes(R.drawable.tv_noselect,"索尼电视","ddefewqdwd"));

    }
    private  void  initRecyView()
    {
        //设置LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(TVBrandTypesActivity.this,LinearLayoutManager.VERTICAL,false));
        //        recyclerView.scrollToPosition(datas.size()-1);

        //添加RecyclerView的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(TVBrandTypesActivity.this, DividerItemDecoration.VERTICAL));

        adapter = new TvBrandTypesAdapter(TVBrandTypesActivity.this,R.layout.item_brand_types, datas);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClikListener(new MyOnclick());
    }

    private class MyOnclick implements BaseAdapter.OnItemClikListener {
        @Override
        public void OnItemClick(View view, int pos) {
            Intent intent = new Intent(TVBrandTypesActivity.this, TVJudge.class);
            if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                startActivity(intent);
            }else {
                Log.e("tag","找不到TVJudge");
            }
        }
    }
}
