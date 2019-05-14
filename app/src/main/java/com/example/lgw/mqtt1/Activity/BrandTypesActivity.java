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
import com.example.lgw.mqtt1.adapter.AirConditioningBrandTypesAdapter;
import com.example.lgw.mqtt1.adapter.BaseAdapter;
import com.example.lgw.mqtt1.bean.BrandTypes;
import java.util.ArrayList;
import java.util.List;
public class BrandTypesActivity extends Activity{
    private TextView tv_title;
    private ImageView iv_back;
    private RecyclerView recyclerView;
    private List<BrandTypes> datas=new ArrayList<BrandTypes>();
    private AirConditioningBrandTypesAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioning_brand_types);
        initView();
        initData();
        initEvent();
        initRecyView();
    }
    private void initEvent() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrandTypesActivity.this, AirConditioningBrandActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        recyclerView=(RecyclerView)findViewById(R.id.rv_bt);
        tv_title=(TextView)findViewById(R.id.tv_title);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        tv_title.setText("格力空调型号");
    }
    private void initData() {
        datas.add(new BrandTypes(R.drawable.air_conditioning_noselect,"格力空调","tv_sony_rm_sd021"));
        datas.add(new BrandTypes(R.drawable.air_conditioning_noselect,"格力空调","ddwqfefdwd"));
        datas.add(new BrandTypes(R.drawable.air_conditioning_noselect,"格力空调","ddefewqdwd"));
    }
    private  void  initRecyView()
    {
        //设置LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(BrandTypesActivity.this,LinearLayoutManager.VERTICAL,false));
        //        recyclerView.scrollToPosition(datas.size()-1);

        //添加RecyclerView的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(BrandTypesActivity.this, DividerItemDecoration.VERTICAL));

        adapter = new AirConditioningBrandTypesAdapter(BrandTypesActivity.this,R.layout.item_air_conditioning_brand_types, datas);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClikListener(new MyOnclick());
    }

    private class MyOnclick implements BaseAdapter.OnItemClikListener {
        @Override
        public void OnItemClick(View view, int pos) {
            Intent intent = new Intent(BrandTypesActivity.this,AirConditioningRemoteControlActivity.class);
            startActivity(intent);
        }
    }
}
