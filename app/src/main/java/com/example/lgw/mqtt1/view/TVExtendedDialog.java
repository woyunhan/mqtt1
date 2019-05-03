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

import butterknife.BindView;
import butterknife.OnClick;

public class TVExtendedDialog extends Dialog {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;
    @BindView(R.id.btn10)
    Button btn10;
    @BindView(R.id.btn11)
    Button btn11;
    @BindView(R.id.btn12)
    Button btn12;
    @BindView(R.id.btn13)
    Button btn13;
    @BindView(R.id.btn14)
    Button btn14;
    @BindView(R.id.btn15)
    Button btn15;

    public TVExtendedDialog(Context context) {
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
    private onBtn10OnclickListener btn10OnclickListener;
    private onBtn11OnclickListener btn11OnclickListener;
    private onBtn12OnclickListener btn12OnclickListener;
    private onBtn13OnclickListener btn13OnclickListener;
    private onBtn14OnclickListener btn14OnclickListener;
    private onBtn15OnclickListener btn15OnclickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tv_extended_button);
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
    private void initData() {
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

    public void setBtn10OnclickListener(onBtn10OnclickListener onBtn10OnclickListener) {
        this.btn10OnclickListener = onBtn10OnclickListener;
    }

    public void setBtn11OnclickListener(onBtn11OnclickListener onBtn11OnclickListener) {
        this.btn11OnclickListener = onBtn11OnclickListener;
    }

    public void setBtn12OnclickListener(onBtn12OnclickListener onBtn12OnclickListener) {
        this.btn12OnclickListener = onBtn12OnclickListener;
    }

    public void setBtn13OnclickListener(onBtn13OnclickListener onBtn13OnclickListener) {
        this.btn13OnclickListener = onBtn13OnclickListener;
    }

    public void setBtn14OnclickListener(onBtn14OnclickListener onBtn14OnclickListener) {
        this.btn14OnclickListener = onBtn14OnclickListener;
    }

    public void setBtn15OnclickListener(onBtn15OnclickListener onBtn15OnclickListener) {
        this.btn15OnclickListener = onBtn15OnclickListener;
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.btn10:
                if (btn10OnclickListener != null) {
                    btn10OnclickListener.onBtn10Click();
                }
                break;
            case R.id.btn11:
                if (btn11OnclickListener != null) {
                    btn11OnclickListener.onBtn11Click();
                }
                break;
            case R.id.btn12:
                if (btn12OnclickListener != null) {
                    btn12OnclickListener.onBtn12Click();
                }
                break;
            case R.id.btn13:
                if (btn13OnclickListener != null) {
                    btn13OnclickListener.onBtn13Click();
                }
                break;
            case R.id.btn14:
                if (btn14OnclickListener != null) {
                    btn14OnclickListener.onBtn14Click();
                }
                break;
            case R.id.btn15:
                if (btn15OnclickListener != null) {
                    btn15OnclickListener.onBtn15Click();
                }
                break;
            default:
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

    public interface onBtn10OnclickListener {
        public void onBtn10Click();
    }

    public interface onBtn11OnclickListener {
        public void onBtn11Click();
    }

    public interface onBtn12OnclickListener {
        public void onBtn12Click();
    }

    public interface onBtn13OnclickListener {
        public void onBtn13Click();
    }

    public interface onBtn14OnclickListener {
        public void onBtn14Click();
    }

    public interface onBtn15OnclickListener {
        public void onBtn15Click();
    }
}
