package com.example.lgw.mqtt1.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.lgw.mqtt1.R;


import butterknife.OnClick;

public class AirConditioningExtendedDialog extends Dialog implements View.OnClickListener {

    private Button btn1;

    private Button btn2;

    private Button btn3;

    private Button btn4;

    private Button btn5;

    private Button btn6;

    private Button btn7;

    private Button btn8;

    private Button btn9;

    public AirConditioningExtendedDialog(Context context) {
        super(context, R.style.MyDialog);
    }
    private onBtn1OnclickListener btn1OnclickListener;
    private onBtn2OnclickListener btn2OnclickListener;
    private onBtn3OnclickListener btn3OnclickListener;
    private onBtn4OnclickListener btn4OnclickListener;
    private onBtn5OnclickListener btn5OnclickListener;
    private onBtn6OnclickListener btn6OnclickListener;
    private onBtn7OnclickListener btn7OnclickListener;
    private onBtn8OnclickListener btn8OnclickListener;
    private onBtn9OnclickListener btn9OnclickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_air_conditioning_extended_button);
        initView();
        //按空白处能取消动画
        setCanceledOnTouchOutside(true);
        //用户可以点击后退键关闭 Dialog
        setCancelable(true);
        //初始化界面数据
        initData();
        getWindow().setGravity(Gravity.BOTTOM); //显示在中央

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);

    }

    private void initView() {
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        btn7=(Button)findViewById(R.id.btn7);
        btn8=(Button)findViewById(R.id.btn8);
        btn9=(Button)findViewById(R.id.btn9);
    }


    private void initData() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }


    public void setBtn1OnclickListener(onBtn1OnclickListener onBtn1OnclickListener) {

        this.btn1OnclickListener = onBtn1OnclickListener;

    }

    public void setBtn2OnclickListener(onBtn2OnclickListener onBtn2OnclickListener) {

        this.btn2OnclickListener = onBtn2OnclickListener;

    }

    public void setBtn3OnclickListener(onBtn3OnclickListener onBtn3OnclickListener) {

        this.btn3OnclickListener = onBtn3OnclickListener;

    }

    public void setBtn4OnclickListener(onBtn4OnclickListener onBtn4OnclickListener) {

        this.btn4OnclickListener = onBtn4OnclickListener;

    }

    public void setBtn5OnclickListener(onBtn5OnclickListener onBtn5OnclickListener) {

        this.btn5OnclickListener = onBtn5OnclickListener;

    }

    public void setBtn6OnclickListener(onBtn6OnclickListener onBtn6OnclickListener) {

        this.btn6OnclickListener = onBtn6OnclickListener;

    }

    public void setBtn7OnclickListener(onBtn7OnclickListener onBtn7OnclickListener) {

        this.btn7OnclickListener = onBtn7OnclickListener;

    }

    public void setBtn8OnclickListener(onBtn8OnclickListener onBtn8OnclickListener) {

        this.btn8OnclickListener = onBtn8OnclickListener;

    }

    public void setBtn9OnclickListener(onBtn9OnclickListener onBtn9OnclickListener) {

        this.btn9OnclickListener = onBtn9OnclickListener;

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                if (btn1OnclickListener != null) {
                    btn1OnclickListener.onBtn1Click();
                }
                break;
            case R.id.btn2:
                if (btn2OnclickListener != null) {
                    btn2OnclickListener.onBtn2Click();
                }
                break;
            case R.id.btn3:
                if (btn3OnclickListener != null) {
                    btn3OnclickListener.onBtn3Click();
                }
                break;
            case R.id.btn4:
                if (btn4OnclickListener != null) {
                    btn4OnclickListener.onBtn4Click();
                }
                break;
            case R.id.btn5:
                if (btn5OnclickListener != null) {
                    btn5OnclickListener.onBtn5Click();
                }
                break;
            case R.id.btn6:
                if (btn6OnclickListener != null) {
                    btn6OnclickListener.onBtn6Click();
                }
                break;
            case R.id.btn7:
                if (btn7OnclickListener != null) {
                    btn7OnclickListener.onBtn7Click();
                }
                break;
            case R.id.btn8:
                if (btn8OnclickListener != null) {
                    btn8OnclickListener.onBtn8Click();
                }
                break;
            case R.id.btn9:
                if (btn9OnclickListener != null) {
                    btn9OnclickListener.onBtn9Click();
                }
                break;
            default:
                Log.e("TAG","error");
        }
    }


    public interface onBtn1OnclickListener {
        public void onBtn1Click();
    }

    public interface onBtn2OnclickListener {
        public void onBtn2Click();
    }

    public interface onBtn3OnclickListener {
        public void onBtn3Click();
    }

    public interface onBtn4OnclickListener {
        public void onBtn4Click();
    }

    public interface onBtn5OnclickListener {
        public void onBtn5Click();
    }

    public interface onBtn6OnclickListener {
        public void onBtn6Click();
    }

    public interface onBtn7OnclickListener {
        public void onBtn7Click();
    }

    public interface onBtn8OnclickListener {
        public void onBtn8Click();
    }

    public interface onBtn9OnclickListener {
        public void onBtn9Click();
    }

}
