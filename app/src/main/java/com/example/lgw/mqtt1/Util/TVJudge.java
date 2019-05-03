package com.example.lgw.mqtt1.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.lgw.mqtt1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TVJudge extends Activity {
    @BindView(R.id.bt_yes)
    Button btYes;
    @BindView(R.id.bt_no)
    Button btNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_judge);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_yes, R.id.bt_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_yes:
                Intent intent = new Intent(TVJudge.this, TVVerification.class);
                startActivity(intent);
                break;
            case R.id.bt_no:
                Intent intent1 = new Intent(TVJudge.this, TVNoVerification.class);
                startActivity(intent1);
                break;
            default:
        }
    }
}
