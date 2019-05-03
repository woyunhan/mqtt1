package com.example.lgw.mqtt1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lgw.mqtt1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrorActivity extends Activity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        tvTitle.setText("缺少设备");
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        Intent intent = new Intent(ErrorActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
