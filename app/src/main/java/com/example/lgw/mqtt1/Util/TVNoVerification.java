package com.example.lgw.mqtt1.Util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lgw.mqtt1.Activity.AirConditioningBrandTypesActivity;
import com.example.lgw.mqtt1.Activity.ErrorActivity;
import com.example.lgw.mqtt1.Activity.TVBrandTypesActivity;
import com.example.lgw.mqtt1.Activity.TVRemoteControlActivity;
import com.example.lgw.mqtt1.R;
import com.example.lgw.mqtt1.remote.Remote;
import com.example.lgw.mqtt1.remote.RemoteDao;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TVNoVerification extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_icon)
    ImageButton ibIcon;
    @BindView(R.id.tv_icon)
    TextView tvIcon;
    @BindView(R.id.bt_yes)
    Button btYes;
    @BindView(R.id.bt_no)
    Button btNo;
    public long dataValue = 0;
    public long count = 0;
    @BindView(R.id.ib_icon1)
    ImageButton ibIcon1;
    private RemoteDao ordersDao;
    private MqttClient client;//client
    private MqttConnectOptions options;//配置
    String TelephonyIMEI = "";
    TVNoVerification.MqttConnectThread mqttConnectThread = new TVNoVerification.MqttConnectThread();//连接服务器任务
    MqttMessage msgMessage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_no_verification);
        ordersDao = new RemoteDao(this);
        if (! ordersDao.isDataExist()){
            ordersDao.initTable();
        }
        init();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ib_icon, R.id.bt_yes, R.id.bt_no,R.id.ib_icon1})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_back:
                intent.setClass(TVNoVerification.this, TVBrandTypesActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_icon:
                btYes.setVisibility(View.VISIBLE);
                btNo.setVisibility(View.VISIBLE);
                initShock();
                List<Remote> turnOrders = ordersDao.getTurnOrder();
                for (Remote Remote : turnOrders) {
                    msgMessage = new MqttMessage((Remote.code).getBytes());
                }

                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage);//发送主题为"/test/button"的消息
                } catch (MqttPersistenceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                }
                break;
            case R.id.ib_icon1:
                initShock();
                btYes.setVisibility(View.VISIBLE);
                btNo.setVisibility(View.VISIBLE);
                List<Remote> plusOrders = ordersDao.getPlusOrder();
                for (Remote Remote : plusOrders) {
                    msgMessage = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage);//发送主题为"/test/button"的消息
                } catch (MqttPersistenceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                }
                break;
            case R.id.bt_yes:
                initShock();
                btYes.setVisibility(View.GONE);
                btNo.setVisibility(View.GONE);
                dataValue++;
                if (dataValue > 0) {
                    ibIcon.setVisibility(View.GONE);
                    ibIcon1.setVisibility(View.VISIBLE);
                    tvIcon.setText("加音量");
                }
                if (dataValue > 1) {
                    intent.setClass(TVNoVerification.this, TVRemoteControlActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.bt_no:
                initShock();
                btYes.setVisibility(View.GONE);
                btNo.setVisibility(View.GONE);
                count++;
                if(count>1){
                    intent.setClass(TVNoVerification.this,  ErrorActivity.class);
                    startActivity(intent);
                }
                break;
            default:
        }
    }
    private void initShock() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{0, 100}, -1);
    }

    private void init() {
        //        textView = (TextView) findViewById(R.id.textView1);//显示接收的消息文本框
        TelephonyManager mTm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        TelephonyIMEI = mTm.getDeviceId();
        //Toast.makeText(getApplicationContext(), TelephonyIMEI, 500).show();

        MyMqttInit();//初始化配置MQTT客户端
        mqttConnectThread.start();//执行连接服务器任务
    }

    /*  初始化配置Mqtt  */
    private void MyMqttInit() {
        try {
            //(1)主机地址(2)客户端ID,一般以客户端唯一标识符(不能够和其它客户端重名)(3)最后一个参数是指数据保存在内存(具体保存什么数据,以后再说,其实现在我也不是很确定)
            //client = new MqttClient("tcp://47.100.126.114:1883",TelephonyIMEI,new MemoryPersistence());
            client = new MqttClient("tcp://220.180.184.7:1883", TelephonyIMEI, new MemoryPersistence());
        } catch (MqttException e) {
            e.printStackTrace();
        }

        options = new MqttConnectOptions();//MQTT的连接设置

        options.setCleanSession(true);//设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接


        options.setConnectionTimeout(10);// 设置连接超时时间 单位为秒

        options.setKeepAliveInterval(20);// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制

    }


    /*连接服务器任务*/
    class MqttConnectThread extends Thread {
        @Override
        public void run() {
            try {
                client.connect(options);//连接服务器,连接不上会阻塞在这

                //client.subscribe("/#",0);//设置接收的主题

                //                client.subscribe("test",0);//设置接收的主题//不需要
                // client.subscribe("/test/fengwu",0);//设置接收的主题

                runOnUiThread(new Runnable() {
                    @Override
                    @SuppressLint("WrongConstant")
                    public void run() {
                        Toast.makeText(getApplicationContext(), "连接成功", 800).show();
                    }
                });
            } catch (MqttSecurityException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    @SuppressLint("WrongConstant")
                    public void run() {
                        Toast.makeText(getApplicationContext(), "连接失败，安全问题", 800).show();
                    }
                });
            } catch (MqttException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    @SuppressLint("WrongConstant")
                    public void run() {
                        Toast.makeText(getApplicationContext(), "连接失败，网络问题", 800).show();
                    }
                });
            }
        }
    }
}
