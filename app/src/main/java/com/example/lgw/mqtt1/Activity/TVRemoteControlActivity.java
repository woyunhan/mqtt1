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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lgw.remote.R;
import com.example.lgw.remote.remote.Remote;
import com.example.lgw.remote.remote.RemoteDao;
import com.example.lgw.remote.view.InfoDialog;
import com.example.lgw.remote.view.PushToast;
import com.example.lgw.remote.view.TVExtendedDialog;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Arrays;
import java.util.List;





public class TVRemoteControlActivity  extends Activity implements View.OnClickListener {

    private MqttClient client;//client
    private MqttConnectOptions options;//配置
    String TelephonyIMEI = "";
    MqttConnectThread mqttConnectThread = new MqttConnectThread();//连接服务器任务
    private RemoteDao remotesDao;
    private TextView tv_control;
    private InfoDialog infoDialog;
    private TVExtendedDialog tvExtendedDialog;
    MqttMessage msgMessage = null;
    String device="索尼电视";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        initView();
        init();
    }


    private void initView() {
        remotesDao = new RemoteDao(this);
        if (!remotesDao.isDataExist()) {
            remotesDao.initTable();
        }
        tv_control = (TextView) findViewById(R.id.tv_control);
        tv_control.setText(device);
        findViewById(R.id.btn_turn).setOnClickListener(this);
        findViewById(R.id.btn_menu).setOnClickListener(this);
        findViewById(R.id.rl_plus).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.rl_less).setOnClickListener(this);
        findViewById(R.id.btn_less).setOnClickListener(this);
        findViewById(R.id.tv_ok).setOnClickListener(this);
        findViewById(R.id.btn_up).setOnClickListener(this);
        findViewById(R.id.btn_down).setOnClickListener(this);
        findViewById(R.id.btn_right).setOnClickListener(this);
        findViewById(R.id.btn_left).setOnClickListener(this);
        findViewById(R.id.rl_next).setOnClickListener(this);
        findViewById(R.id.rl_previous).setOnClickListener(this);
        findViewById(R.id.btn_tvav).setOnClickListener(this);
        findViewById(R.id.btn_chanel).setOnClickListener(this);
        findViewById(R.id.btn_reminisce).setOnClickListener(this);
        findViewById(R.id.btn_mute).setOnClickListener(this);
        findViewById(R.id.btn_collection).setOnClickListener(this);
        findViewById(R.id.btn_drop_out).setOnClickListener(this);
        findViewById(R.id.btn_more).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_turn:
                initShock(new String[] {"开/关"});
                break;
            case R.id.btn_menu:
                initShock(new String[] {"主菜单"});
                break;
            case R.id.btn_tvav:
                initShock(new String[] {"输入选择"});
                break;
            case R.id.btn_more:
                tvExtendedDialog = new TVExtendedDialog(TVRemoteControlActivity.this);
                tvExtendedDialog.setBtn1OnclickListener(new TVExtendedDialog.onBtn1OnclickListener() {
                    @Override
                    public void onBtn1Click() {
                        initShock(new String[] {"声音切换"});
                    }
                });
                tvExtendedDialog.setBtn2OnclickListener(new TVExtendedDialog.onBtn2OnclickListener() {
                    @Override
                    public void onBtn2Click() {
                        initShock(new String[] {"SEN"});
                    }
                });
                tvExtendedDialog.setBtn3OnclickListener(new TVExtendedDialog.onBtn3OnclickListener() {
                    @Override
                    public void onBtn3Click() {
                        initShock(new String[] {"屏显"});
                    }
                });
                tvExtendedDialog.setBtn4OnclickListener(new TVExtendedDialog.onBtn4OnclickListener() {
                    @Override
                    public void onBtn4Click() {
                        initShock(new String[] {"电子节目指南"});
                    }
                });
                tvExtendedDialog.setBtn5OnclickListener(new TVExtendedDialog.onBtn5OnclickListener() {
                    @Override
                    public void onBtn5Click() {
                        initShock(new String[] {"数字"});
                    }
                });
                tvExtendedDialog.setBtn6OnclickListener(new TVExtendedDialog.onBtn6OnclickListener() {
                    @Override
                    public void onBtn6Click() {
                        initShock(new String[] {"双画面"});
                    }
                });
                tvExtendedDialog.setBtn7OnclickListener(new TVExtendedDialog.onBtn7OnclickListener() {
                    @Override
                    public void onBtn7Click() {
                        initShock(new String[] {"相框模式"});
                    }
                });
                tvExtendedDialog.setBtn8OnclickListener(new TVExtendedDialog.onBtn8OnclickListener() {
                    @Override
                    public void onBtn8Click() {
                        initShock(new String[] {"红"});
                    }
                });
                tvExtendedDialog.setBtn9OnclickListener(new TVExtendedDialog.onBtn9OnclickListener() {
                    @Override
                    public void onBtn9Click() {
                        initShock(new String[] {"绿"});
                    }
                });
                tvExtendedDialog.setBtn10OnclickListener(new TVExtendedDialog.onBtn10OnclickListener() {
                    @Override
                    public void onBtn10Click() {
                        initShock(new String[] {"黄"});
                    }
                });
                tvExtendedDialog.setBtn11OnclickListener(new TVExtendedDialog.onBtn11OnclickListener() {
                    @Override
                    public void onBtn11Click() {
                        initShock(new String[] {"蓝"});
                    }
                });
                tvExtendedDialog.setBtn12OnclickListener(new TVExtendedDialog.onBtn12OnclickListener() {
                    @Override
                    public void onBtn12Click() {
                        initShock(new String[] {"3D"});
                    }
                });
                tvExtendedDialog.setBtn13OnclickListener(new TVExtendedDialog.onBtn13OnclickListener() {
                    @Override
                    public void onBtn13Click() {
                        initShock(new String[] {"字幕设定"});
                    }
                });
                tvExtendedDialog.setBtn14OnclickListener(new TVExtendedDialog.onBtn14OnclickListener() {
                    @Override
                    public void onBtn14Click() {
                        initShock(new String[] {"同步菜单"});
                    }
                });
                tvExtendedDialog.setBtn15OnclickListener(new TVExtendedDialog.onBtn15OnclickListener() {
                    @Override
                    public void onBtn15Click() {
                        initShock(new String[] {"选项"});
                    }
                });
                tvExtendedDialog.show();
                break;
            case R.id.btn_reminisce:
                initShock(new String[] {"回看"});
                break;
            case R.id.btn_mute:
                initShock(new String[] {"静音"});
                break;
            case R.id.tv_ok:
                initShock(new String[] {"中键"});
                break;
            case R.id.btn_drop_out:
                initShock(new String[] {"返回"});
                break;
            case R.id.btn_chanel:
                infoDialog = new InfoDialog(TVRemoteControlActivity.this);
                infoDialog.setBtn_chanel0OnclickListener(new InfoDialog.onBtn_chanel0OnclickListener() {
                    @Override
                    public void onBtn_chanel0Click() {
                        initShock(new String[] {"0频道"});
                    }
                });
                infoDialog.setBtn_chanel1OnclickListener(new InfoDialog.onBtn_chanel1OnclickListener() {
                    @Override
                    public void onBtn_chanel1Click() {
                        initShock(new String[] {"1频道"});
                    }
                });
                infoDialog.setBtn_chanel2OnclickListener(new InfoDialog.onBtn_chanel2OnclickListener() {
                    @Override
                    public void onBtn_chanel2Click() {
                        initShock(new String[] {"2频道"});
                    }
                });
                infoDialog.setBtn_chanel3OnclickListener(new InfoDialog.onBtn_chanel3OnclickListener() {
                    @Override
                    public void onBtn_chanel3Click() {
                        initShock(new String[] {"3频道"});
                    }
                });
                infoDialog.setBtn_chanel4OnclickListener(new InfoDialog.onBtn_chanel4OnclickListener() {
                    @Override
                    public void onBtn_chanel4Click() {
                        initShock(new String[] {"4频道"});
                    }
                });
                infoDialog.setBtn_chanel5OnclickListener(new InfoDialog.onBtn_chanel5OnclickListener() {
                    @Override
                    public void onBtn_chanel5Click() {
                        initShock(new String[] {"5频道"});
                    }
                });
                infoDialog.setBtn_chanel6OnclickListener(new InfoDialog.onBtn_chanel6OnclickListener() {
                    @Override
                    public void onBtn_chanel6Click() {
                        initShock(new String[] {"6频道"});
                    }
                });
                infoDialog.setBtn_chanel7OnclickListener(new InfoDialog.onBtn_chanel7OnclickListener() {
                    @Override
                    public void onBtn_chanel7Click() {
                        initShock(new String[] {"7频道"});
                    }
                });
                infoDialog.setBtn_chanel8OnclickListener(new InfoDialog.onBtn_chanel8OnclickListener() {
                    @Override
                    public void onBtn_chanel8Click() {
                        initShock(new String[] {"8频道"});
                    }
                });
                infoDialog.setBtn_chanel9OnclickListener(new InfoDialog.onBtn_chanel9OnclickListener() {
                    @Override
                    public void onBtn_chanel9Click() {
                        initShock(new String[] {"9频道"});
                    }
                });
                infoDialog.setBtn_chanel_inputOnclickListener(new InfoDialog.onBtn_chanel_inputOnclickListener() {
                    @Override
                    public void onBtn_chanel_inputClick() {
                        initShock(new String[] {"输入选择"});
                    }
                });
                infoDialog.setBtn_returnOnclickListener(new InfoDialog.onBtn_returnOnclickListener() {
                    @Override
                    public void onBtn_returnClick() {
                        initShock(new String[] {"返回"});
                    }
                });
                infoDialog.show();
                break;
            case R.id.btn_plus:
                initShock(new String[] {"音量加"});
                break;
            case R.id.btn_less:
                initShock(new String[] {"音量减"});
                break;
            case R.id.rl_previous:
                initShock(new String[] {"频道减"});
                break;
            case R.id.rl_next:
                initShock(new String[] {"频道加"});
                break;
            case R.id.btn_up:
                initShock(new String[] {"上键"});
                break;
            case R.id.btn_down:
                initShock(new String[] {"下键"});
                break;
            case R.id.btn_left:
                initShock(new String[] {"左键"});
                break;
            case R.id.btn_right:
                initShock(new String[] {"右键"});
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
            Log.e("TV",msgMessage.toString());
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
        //        textView = (TextView) findViewById(R.id.textView1);//显示接收的消息文本框
        TelephonyManager mTm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
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
//            client = new MqttClient("tcp://220.180.184.7:1883", TelephonyIMEI, new MemoryPersistence());
            client = new MqttClient("tcp://192.168.43.52:1883", TelephonyIMEI, new MemoryPersistence());
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

