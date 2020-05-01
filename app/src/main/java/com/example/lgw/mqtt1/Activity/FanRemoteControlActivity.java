package com.example.lgw.remote.Activity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lgw.remote.R;
import com.example.lgw.remote.remote.Remote;
import com.example.lgw.remote.remote.RemoteDao;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Arrays;
import java.util.List;
public class FanRemoteControlActivity extends Activity implements View.OnClickListener {

    private MqttClient client;//client
    private MqttConnectOptions options;//配置
    String TelephonyIMEI = "";
    MqttConnectThread mqttConnectThread = new MqttConnectThread();//连接服务器任务
    private RemoteDao remotesDao;
    private TextView tv_control;
    String device="艾美特风扇";
    MqttMessage msgMessage = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);
        initView();
        init();
    }
    private void initView() {
        remotesDao = new RemoteDao(this);
        if (! remotesDao.isDataExist()){
            remotesDao.initTable();
        }
        tv_control=(TextView)findViewById(R.id.tv_control);
        tv_control.setText(device);
        findViewById(R.id.btn_turn_on).setOnClickListener(this);
        findViewById(R.id.btn_turn_off).setOnClickListener(this);
        findViewById(R.id.btn_shaking_head).setOnClickListener(this);
        findViewById(R.id.btn_wind_speed).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_turn_on:
                initShock(new String[] {"风速/开"});
                break;
            case R.id.btn_turn_off:
                initShock(new String[] {"风扇关"});
                break;
            case R.id.btn_shaking_head:
                initShock(new String[] {"摇头"});
                break;
            case R.id.btn_wind_speed:
                initShock(new String[] {"风速/开"});
                break;
            default:
                break;
        }
    }
    private void initShock(String[] action) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{0, 100}, -1);
        remotesDao.sqLite_insert(device, Arrays.toString(action));
        List<Remote> Remotes = remotesDao.getRemote(action);
        for (Remote Remote : Remotes){
            msgMessage = new MqttMessage((Remote.code).getBytes());
        }
        try {
            client.publish("/SmartHome/IR_remoter/set", msgMessage);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
    }

    private void init() {
        TelephonyManager mTm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        TelephonyIMEI = mTm.getDeviceId();
        MyMqttInit();//初始化配置MQTT客户端
        mqttConnectThread.start();//执行连接服务器任务
    }


    /*  初始化配置Mqtt  */
    private void MyMqttInit()
    {
        try
        {
            //(1)主机地址(2)客户端ID,一般以客户端唯一标识符(不能够和其它客户端重名)(3)最后一个参数是指数据保存在内存(具体保存什么数据,以后再说,其实现在我也不是很确定)
            //client = new MqttClient("tcp://47.100.126.114:1883",TelephonyIMEI,new MemoryPersistence());
//            client = new MqttClient("tcp://220.180.184.7:1883",TelephonyIMEI,new MemoryPersistence());
            client = new MqttClient("tcp://192.168.43.1:1883", TelephonyIMEI, new MemoryPersistence());
        } catch (MqttException e) {
            e.printStackTrace();
        }

        options = new MqttConnectOptions();//MQTT的连接设置

        options.setCleanSession(true);//设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接

        options.setConnectionTimeout(10);// 设置连接超时时间 单位为秒

        options.setKeepAliveInterval(20);// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制


    }


    /*连接服务器任务*/
    class MqttConnectThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                client.connect(options);//连接服务器,连接不上会阻塞在这
                runOnUiThread(new Runnable() {
                    @Override
                    @SuppressLint("WrongConstant")
                    public void run() {
                        Toast.makeText(getApplicationContext(), "连接成功", 800).show();
                    }
                });
            }
            catch (MqttSecurityException e)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    @SuppressLint("WrongConstant")
                    public void run() {
                        Toast.makeText(getApplicationContext(), "连接失败，安全问题", 800).show();
                    }
                });
            }
            catch (MqttException e)
            {
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

