package com.example.lgw.remote.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lgw.remote.Air.Air;
import com.example.lgw.remote.R;
import com.example.lgw.remote.remote.RemoteDao;
import com.example.lgw.remote.utils.TimeUtil;
import com.example.lgw.remote.view.AirConditioningExtendedDialog;
import com.example.lgw.remote.view.AirTimeSetDialog;
import com.example.lgw.remote.view.PushToast;
import com.example.lgw.remote.view.TempControlView;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class AirConditioningRemoteControlActivity extends Activity implements View.OnClickListener {
    private MqttClient client;//client
    private MqttConnectOptions options;//配置
    String TelephonyIMEI = "";
    MqttConnectThread mqttConnectThread = new MqttConnectThread();//连接服务器任务
    private RemoteDao remotesDao;
    private TempControlView tempControl;
    private TextView tv_control;
    private AirConditioningExtendedDialog airConditioningExtendedDialog;
    private MediaPlayer mediaPlayer;
    private boolean mPlaying=true;
    String device = "格力空调";
    Air air = new Air();
    String[] t;
    String s;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioning);
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
        tempControl = findViewById(R.id.temp_control);
        PushToast.getInstance().init(this);
        findViewById(R.id.btn_turn).setOnClickListener(this);
        findViewById(R.id.btn_wind_speed).setOnClickListener(this);
        findViewById(R.id.btn_automatic).setOnClickListener(this);
        findViewById(R.id.btn_off).setOnClickListener(this);
        findViewById(R.id.btn_refrigeration).setOnClickListener(this);
        findViewById(R.id.btn_heating).setOnClickListener(this);
        findViewById(R.id.btn_dehumidification).setOnClickListener(this);
        findViewById(R.id.btn_air_supply).setOnClickListener(this);
        findViewById(R.id.btn_sweep_up_and_down).setOnClickListener(this);
        findViewById(R.id.btn_sweep_left_and_right).setOnClickListener(this);
        findViewById(R.id.btn_more).setOnClickListener(this);
        findViewById(R.id.btn_timing).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_turn:
                t = air.getT();
                t[1] = "开机";
                initShock("开机");
                tempControl.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_off:
                t = air.getT();
                t[1] = "关机";
                initShock("关机");
                tempControl.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_wind_speed:
                t = air.getT();
                if (t[2].equals("4")) {
                    t[2] = "1";
                } else {
                    t[2] = String.valueOf(Integer.parseInt(t[2]) + 1);
                }
                initShock("风速");
                break;
            case R.id.btn_automatic:
//                t = air.getT();
//                t[0] = "自动";
//                initShock("自动");
                break;
            case R.id.btn_refrigeration:
                t = air.getT();
                t[0] = "制冷";
                initShock("制冷");
                break;
            case R.id.btn_heating:
                t = air.getT();
                t[0] = "制热";
                initShock("制热");
                break;
            case R.id.btn_timing:
                t = air.getT();
                if ("24".equals(t[6])) {
                    t[6]="0";
                } else {
                    t[6] = String.valueOf(Integer.parseInt(t[6]) + 0.5);
                }
                initShock("定时");
                break;
            case R.id.btn_dehumidification:
                t = air.getT();
                t[0] = "除湿";
                initShock("除湿");
                break;
            case R.id.btn_air_supply:
                t = air.getT();
                t[0] = "送风";
                initShock("送风");
                break;
            case R.id.btn_sweep_left_and_right:
                t = air.getT();
                if ("无".equals(t[13])) {
                    t[3] = "有";
                    t[13] = "有";
                } else {
                    t[3] = "无";
                    t[13] = "无";
                }
                initShock("左右扫风");
                break;
            case R.id.btn_sweep_up_and_down:
                t = air.getT();
                if ("无".equals(t[12])) {
                    t[3] = "有";
                    t[12] = "有";
                } else {
                    t[3] = "无";
                    t[12] = "无";
                }
                initShock("上下扫风");
                break;
            case R.id.btn_more:
                airConditioningExtendedDialog = new AirConditioningExtendedDialog(AirConditioningRemoteControlActivity.this);
                airConditioningExtendedDialog.setBtn1OnclickListener(new AirConditioningExtendedDialog.onBtn1OnclickListener() {
                    @Override
                    public void onBtn1Click() {
                        initAir();
                        if ("健康".equals(t[9])) {
                            t[9] = "无";
                        } else {
                            t[9] = "健康";
                        }
                    }
                });
                airConditioningExtendedDialog.setBtn2OnclickListener(new AirConditioningExtendedDialog.onBtn2OnclickListener() {
                    @Override
                    public void onBtn2Click() {
                        initAir();
                        if ("干燥".equals(t[10])) {
                            t[10] = "无";
                        } else {
                            t[10] = "干燥";
                        }
                        initShock("干燥");
                    }
                });
                airConditioningExtendedDialog.setBtn3OnclickListener(new AirConditioningExtendedDialog.onBtn3OnclickListener() {
                    @Override
                    public void onBtn3Click() {
                        initAir();
                        if ("灭".equals(t[8])) {
                            t[8] = "亮";
                        } else {
                            t[8] = "灭";
                        }
                        initShock("灯光");
                    }
                });
                airConditioningExtendedDialog.setBtn4OnclickListener(new AirConditioningExtendedDialog.onBtn4OnclickListener() {
                    @Override
                    public void onBtn4Click() {
                        initAir();
                        if ("普通".equals(t[11])) {
                            t[7] = "超强";
                        } else {
                            t[7] = "普通";
                        }
                        initShock("超强");
                    }
                });
                airConditioningExtendedDialog.setBtn5OnclickListener(new AirConditioningExtendedDialog.onBtn5OnclickListener() {
                    @Override
                    public void onBtn5Click() {
                        initAir();
                        if ("无".equals(t[11])) {
                            t[11] = "换气";
                        } else {
                            t[11] = "无";
                        }
                        initShock("换气");
                    }
                });
                airConditioningExtendedDialog.setBtn6OnclickListener(new AirConditioningExtendedDialog.onBtn6OnclickListener() {
                    @Override
                    public void onBtn6Click() {
                        initAir();
                        if ("不显示".equals(t[14])) {
                            t[14] = "显示";
                        } else {
                            t[14] = "不显示";
                        }
                        initShock("显示温度");
                    }
                });
                airConditioningExtendedDialog.show();
                break;
            default:
                break;
        }
    }
    private void init() {
        tempControl.setAngleRate(1);
        tempControl.setTemp(16, 30, 26);
        //设置旋钮是否可旋转
        tempControl.setCanRotate(true);
        tempControl.setOnTempChangeListener(new TempControlView.OnTempChangeListener() {
            @Override
            public void change(int temp) {
                initAir();
                t[5] = String.valueOf(temp);
                t[15] = String.valueOf(temp);
                initShock("调节温度");
                if(temp<20&&(TimeUtil.getTimeInt("MM")>4&&TimeUtil.getTimeInt("MM")<11)){
                    PushToast.getInstance().createToast(device,"使用温馨提示","夏天制冷时，推荐设定温度为26℃以上，在此温度范围内，人体感觉较为舒适，并有利于节能。");
                    initSound(R.raw.air0);
                }
                if(temp>24&&(TimeUtil.getTimeInt("MM")<=4||TimeUtil.getTimeInt("MM")>=11)){
                    PushToast.getInstance().createToast(device,"使用温馨提示","冬天制热时，推荐设定温度为20℃以下，在此温度范围内，人体感觉较为舒适，并有利于节能。");
                    initSound(R.raw.air1);
                }

            }
        });

        tempControl.setOnClickListener(new TempControlView.OnClickListener() {
            @Override
            public void onClick(int temp) {
            }
        });
        TelephonyManager mTm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        TelephonyIMEI = mTm.getDeviceId();
        MyMqttInit();//初始化配置MQTT客户端
        mqttConnectThread.start();//执行连接服务器任务
    }

    private void initSound(int sound) {
        mediaPlayer = MediaPlayer.create(this,sound);
        mediaPlayer.start();
    }

    private void initShock(String action) {
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(new long[]{0, 100}, -1);
        }
        remotesDao.sqLite_insert(device, action);
        air.setT(t);
        s = air.S();
        MqttMessage mqttMessage = new MqttMessage(s.getBytes());
        try {
            Log.e("Air",mqttMessage.toString());
            client.publish("/SmartHome/IR_remoter/set", mqttMessage);
            client.publish("/SmartHome/IR_remoter/set", mqttMessage);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
    }

    public void initAir() {
        Air air = new Air();
        t = air.getT();
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
        public void run() {
            try {
                client.connect(options);//连接服务器,连接不上会阻塞在这
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
