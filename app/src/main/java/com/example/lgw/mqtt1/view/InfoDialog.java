package com.example.lgw.mqtt1.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.lgw.mqtt1.R;


import butterknife.OnClick;

public class InfoDialog extends Dialog implements View.OnClickListener {

    private Button btnChanel1;

    private Button btnChanel2;

    private Button btnChanel3;

    private Button btnChanel4;

    private Button btnChanel5;

    private Button btnChanel6;

    private Button btnChanel7;

    private Button btnChanel8;

    private Button btnChanel9;

    private Button btnChanelInput;

    private Button btnChanel0;

    private Button btnReturn;
    public InfoDialog(Context context) {
        super(context, R.style.MyDialog);
    }
    private onBtn_chanel0OnclickListener btn_chanel0OnclickListener;
    private onBtn_chanel1OnclickListener btn_chanel1OnclickListener;
    private onBtn_chanel2OnclickListener btn_chanel2OnclickListener;
    private onBtn_chanel3OnclickListener btn_chanel3OnclickListener;
    private onBtn_chanel4OnclickListener btn_chanel4OnclickListener;
    private onBtn_chanel5OnclickListener btn_chanel5OnclickListener;
    private onBtn_chanel6OnclickListener btn_chanel6OnclickListener;
    private onBtn_chanel7OnclickListener btn_chanel7OnclickListener;
    private onBtn_chanel8OnclickListener btn_chanel8OnclickListener;
    private onBtn_chanel9OnclickListener btn_chanel9OnclickListener;
    private onBtn_chanel_inputOnclickListener btn_chanel_inputOnclickListener;
    private onBtn_returnOnclickListener btn_returnOnclickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tv_change_station);
        intView();
        initData();
        //按空白处能取消动画
        setCanceledOnTouchOutside(true);
        //用户可以点击后退键关闭 Dialog
        setCancelable(true);
        //调节dialog大小
        initDiaglog();

    }
    private void intView() {
        btnChanel1=(Button)findViewById(R.id.btn_chanel1);
        btnChanel2=(Button)findViewById(R.id.btn_chanel2);
        btnChanel3=(Button)findViewById(R.id.btn_chanel3);
        btnChanel4=(Button)findViewById(R.id.btn_chanel4);
        btnChanel5=(Button)findViewById(R.id.btn_chanel5);
        btnChanel6=(Button)findViewById(R.id.btn_chanel6);
        btnChanel7=(Button)findViewById(R.id.btn_chanel7);
        btnChanel8=(Button)findViewById(R.id.btn_chanel8);
        btnChanel9=(Button)findViewById(R.id.btn_chanel9);
        btnChanel0=(Button)findViewById(R.id.btn_chanel0);
        btnChanelInput=(Button)findViewById(R.id.btn_chanel_input);
        btnReturn=(Button)findViewById(R.id.btn_return);
    }
    private void initData() {
        btnChanel1.setOnClickListener(this);
        btnChanel2.setOnClickListener(this);
        btnChanel3.setOnClickListener(this);
        btnChanel4.setOnClickListener(this);
        btnChanel5.setOnClickListener(this);
        btnChanel6.setOnClickListener(this);
        btnChanel7.setOnClickListener(this);
        btnChanel8.setOnClickListener(this);
        btnChanel9.setOnClickListener(this);
        btnChanel0.setOnClickListener(this);
        btnChanelInput.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
    }



    private void initDiaglog() {
        getWindow().setGravity(Gravity.CENTER); //显示在中央
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);
    }





    public void setBtn_chanel0OnclickListener(onBtn_chanel0OnclickListener onBtn_chanel0OnclickListener) {
        this.btn_chanel0OnclickListener = onBtn_chanel0OnclickListener;
    }

    public void setBtn_chanel1OnclickListener(onBtn_chanel1OnclickListener onBtn_chanel1OnclickListener) {
        this.btn_chanel1OnclickListener = onBtn_chanel1OnclickListener;
    }

    public void setBtn_chanel2OnclickListener(onBtn_chanel2OnclickListener onBtn_chanel2OnclickListener) {

        this.btn_chanel2OnclickListener = onBtn_chanel2OnclickListener;

    }

    public void setBtn_chanel3OnclickListener(onBtn_chanel3OnclickListener onBtn_chanel3OnclickListener) {

        this.btn_chanel3OnclickListener = onBtn_chanel3OnclickListener;

    }

    public void setBtn_chanel4OnclickListener(onBtn_chanel4OnclickListener onBtn_chanel4OnclickListener) {

        this.btn_chanel4OnclickListener = onBtn_chanel4OnclickListener;

    }

    public void setBtn_chanel5OnclickListener(onBtn_chanel5OnclickListener onBtn_chanel5OnclickListener) {

        this.btn_chanel5OnclickListener = onBtn_chanel5OnclickListener;

    }

    public void setBtn_chanel6OnclickListener(onBtn_chanel6OnclickListener onBtn_chanel6OnclickListener) {

        this.btn_chanel6OnclickListener = onBtn_chanel6OnclickListener;

    }

    public void setBtn_chanel7OnclickListener(onBtn_chanel7OnclickListener onBtn_chanel7OnclickListener) {

        this.btn_chanel7OnclickListener = onBtn_chanel7OnclickListener;

    }

    public void setBtn_chanel8OnclickListener(onBtn_chanel8OnclickListener onBtn_chanel8OnclickListener) {

        this.btn_chanel8OnclickListener = onBtn_chanel8OnclickListener;

    }

    public void setBtn_chanel9OnclickListener(onBtn_chanel9OnclickListener onBtn_chanel9OnclickListener) {

        this.btn_chanel9OnclickListener = onBtn_chanel9OnclickListener;

    }

    public void setBtn_chanel_inputOnclickListener(onBtn_chanel_inputOnclickListener onBtn_chanel_inputOnclickListener) {

        this.btn_chanel_inputOnclickListener = onBtn_chanel_inputOnclickListener;

    }

    public void setBtn_returnOnclickListener(onBtn_returnOnclickListener onBtn_returnOnclickListener) {

        this.btn_returnOnclickListener = onBtn_returnOnclickListener;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_chanel1:
                if (btn_chanel1OnclickListener != null) {
                    btn_chanel1OnclickListener.onBtn_chanel1Click();
                }
                break;
            case R.id.btn_chanel2:
                if (btn_chanel2OnclickListener != null) {
                    btn_chanel2OnclickListener.onBtn_chanel2Click();
                }
                break;
            case R.id.btn_chanel3:
                if (btn_chanel3OnclickListener != null) {
                    btn_chanel3OnclickListener.onBtn_chanel3Click();
                }
                break;
            case R.id.btn_chanel4:
                if (btn_chanel4OnclickListener != null) {
                    btn_chanel4OnclickListener.onBtn_chanel4Click();
                }
                break;
            case R.id.btn_chanel5:
                if (btn_chanel5OnclickListener != null) {
                    btn_chanel5OnclickListener.onBtn_chanel5Click();
                }
                break;
            case R.id.btn_chanel6:
                if (btn_chanel6OnclickListener != null) {
                    btn_chanel6OnclickListener.onBtn_chanel6Click();
                }
                break;
            case R.id.btn_chanel7:
                if (btn_chanel7OnclickListener != null) {
                    btn_chanel7OnclickListener.onBtn_chanel7Click();
                }
                break;
            case R.id.btn_chanel8:
                if (btn_chanel8OnclickListener != null) {
                    btn_chanel8OnclickListener.onBtn_chanel8Click();
                }
                break;
            case R.id.btn_chanel9:
                if (btn_chanel9OnclickListener != null) {
                    btn_chanel9OnclickListener.onBtn_chanel9Click();
                }
                break;
            case R.id.btn_chanel_input:
                if (btn_chanel_inputOnclickListener != null) {
                    btn_chanel_inputOnclickListener.onBtn_chanel_inputClick();
                }
                break;
            case R.id.btn_chanel0:
                if (btn_chanel0OnclickListener != null) {
                    btn_chanel0OnclickListener.onBtn_chanel0Click();
                }
                break;
            case R.id.btn_return:
                if (btn_returnOnclickListener != null) {
                    btn_returnOnclickListener.onBtn_returnClick();
                }
                break;
            default:
        }
    }

    public interface onBtn_chanel0OnclickListener {
        public void onBtn_chanel0Click();
    }

    public interface onBtn_chanel1OnclickListener {
        public void onBtn_chanel1Click();
    }

    public interface onBtn_chanel2OnclickListener {
        public void onBtn_chanel2Click();
    }

    public interface onBtn_chanel3OnclickListener {
        public void onBtn_chanel3Click();
    }

    public interface onBtn_chanel4OnclickListener {
        public void onBtn_chanel4Click();
    }

    public interface onBtn_chanel5OnclickListener {
        public void onBtn_chanel5Click();
    }

    public interface onBtn_chanel6OnclickListener {
        public void onBtn_chanel6Click();
    }

    public interface onBtn_chanel7OnclickListener {
        public void onBtn_chanel7Click();
    }

    public interface onBtn_chanel8OnclickListener {
        public void onBtn_chanel8Click();
    }

    public interface onBtn_chanel9OnclickListener {
        public void onBtn_chanel9Click();
    }

    public interface onBtn_chanel_inputOnclickListener {
        public void onBtn_chanel_inputClick();
    }

    public interface onBtn_returnOnclickListener {
        public void onBtn_returnClick();
    }
}
