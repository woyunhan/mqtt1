package com.example.lgw.mqtt1.Activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lgw.mqtt1.MainActivity;
import com.example.lgw.mqtt1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddActivity extends Activity{
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_tv)
    LinearLayout llTv;
    @BindView(R.id.ll_air_conditioning)
    LinearLayout llAirConditioning;
    @BindView(R.id.ll_fan)
    LinearLayout llFan;
    @BindView(R.id.ll_sound)
    LinearLayout llSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.iv_back, R.id.ll_tv, R.id.ll_air_conditioning, R.id.ll_fan, R.id.ll_sound})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_back:
                intent.setClass(AddActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_tv:
                intent.setClass(AddActivity.this, TVBrandActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_air_conditioning:
                intent.setClass(AddActivity.this, AirConditioningBrandActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_fan:
                intent.setClass(AddActivity.this,FanBrandActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_sound:
                break;
            default:
                Log.e("TAG","error");
                break;
        }
    }
}

