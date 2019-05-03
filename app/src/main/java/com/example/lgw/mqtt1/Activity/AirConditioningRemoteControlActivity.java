package com.example.lgw.mqtt1.Activity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lgw.mqtt1.Air.Air;
import com.example.lgw.mqtt1.R;
import com.example.lgw.mqtt1.remote.RemoteDao;
import com.example.lgw.mqtt1.view.AirConditioningExtendedDialog;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AirConditioningRemoteControlActivity extends Activity implements View.OnClickListener {
    @BindView(R.id.tv_control)
    TextView tvControl;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.btn_turn)
    Button btnTurn;
    @BindView(R.id.ll_less)
    LinearLayout llLess;
    @BindView(R.id.ll_plus)
    LinearLayout llPlus;
    @BindView(R.id.btn_off)
    Button btnOff;
    @BindView(R.id.btn_refrigeration)
    Button btnRefrigeration;
    @BindView(R.id.btn_heating)
    Button btnHeating;
    @BindView(R.id.btn_dehumidification)
    Button btnDehumidification;
    @BindView(R.id.btn_air_supply)
    Button btnAirSupply;
    @BindView(R.id.btn_sweep_up_and_down)
    Button btnSweepUpAndDown;
    @BindView(R.id.btn_sweep_left_and_right)
    Button btnSweepLeftAndRight;
    @BindView(R.id.btn_timing)
    Button btnTiming;
    @BindView(R.id.btn_more)
    Button btnMore;
    private MqttClient client;//client
    private MqttConnectOptions options;//配置
    String TelephonyIMEI = "";
    MqttConnectThread mqttConnectThread = new MqttConnectThread();//连接服务器任务
    private RemoteDao ordersDao;
    private AirConditioningExtendedDialog airConditioningExtendedDialog;
    MqttMessage msgMessage = null;
    Air airControl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioning);
        ButterKnife.bind(this);
        initView();
        airControl = new Air();
        init();
    }

    private void initView() {
        ordersDao = new RemoteDao(this);
        ordersDao.initTable();
        tvControl.setText("格力空调 airmate_fan_weizhi");
        btnMore.setOnClickListener(this);
    }

    String[] t;
    String s;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_more:
                airConditioningExtendedDialog = new AirConditioningExtendedDialog(AirConditioningRemoteControlActivity.this);
                airConditioningExtendedDialog.setBtn1OnclickListener(new AirConditioningExtendedDialog.onBtn1OnclickListener() {
                    @Override
                    public void onBtn1Click() {
                        initShock();
                        Air air = new Air();
                        String[] t = air.getT();
                        t[9] = "健康";
                        air.setT(t);
                        String s = air.S();
                        tvTemperature.setText(t[5]);
                        Log.e("s", "" + s);
                        MqttMessage msgMessage = null;
                        msgMessage = new MqttMessage(s.getBytes());
                        try {
                            Log.e("s", "" + msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        } catch (MqttPersistenceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MqttException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                        }

                    }
                });
                airConditioningExtendedDialog.setBtn2OnclickListener(new AirConditioningExtendedDialog.onBtn2OnclickListener() {
                    @Override
                    public void onBtn2Click() {
                        initShock();
                        Air air = new Air();
                        String[] t = air.getT();
                        t[10] = "干燥";
                        air.setT(t);
                        String s = air.S();
                        Log.e("s", "" + s);
                        tvTemperature.setText(t[5]);
                        MqttMessage msgMessage = null;
                        msgMessage = new MqttMessage(s.getBytes());
                        try {
                            Log.e("s", "" + msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        } catch (MqttPersistenceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MqttException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                        }
                    }
                });
                airConditioningExtendedDialog.setBtn3OnclickListener(new AirConditioningExtendedDialog.onBtn3OnclickListener() {
                    @Override
                    public void onBtn3Click() {
                        initShock();
                        Air air = new Air();
                        String[] t = air.getT();
                        t[8] = "亮";
                        air.setT(t);
                        String s = air.S();
                        Log.e("s", "" + s);
                        MqttMessage msgMessage = null;
                        msgMessage = new MqttMessage(s.getBytes());
                        try {
                            Log.e("s", "" + msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        } catch (MqttPersistenceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MqttException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                        }
                    }
                });
                airConditioningExtendedDialog.setBtn4OnclickListener(new AirConditioningExtendedDialog.onBtn4OnclickListener() {
                    @Override
                    public void onBtn4Click() {
                        initShock();
                        airControl = new Air();
                        String[] t = airControl.getT();
                        t[7] = "超强";
                        airControl.setT(t);
                        String s = airControl.S();
                        Log.e("s", "" + s);
                        MqttMessage msgMessage = null;
                        msgMessage = new MqttMessage(s.getBytes());
                        try {
                            Log.e("s", "" + msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        } catch (MqttPersistenceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MqttException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                        }

                    }
                });
                airConditioningExtendedDialog.setBtn5OnclickListener(new AirConditioningExtendedDialog.onBtn5OnclickListener() {
                    @Override
                    public void onBtn5Click() {
                        initShock();
                        Air air = new Air();
                        String[] t = air.getT();
                        t[11] = "换气";
                        air.setT(t);
                        String s = air.S();
                        Log.e("s", "" + s);
                        MqttMessage msgMessage = null;
                        msgMessage = new MqttMessage(s.getBytes());
                        try {
                            Log.e("s", "" + msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        } catch (MqttPersistenceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MqttException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                        }

                    }
                });
                airConditioningExtendedDialog.setBtn6OnclickListener(new AirConditioningExtendedDialog.onBtn6OnclickListener() {
                    @Override
                    public void onBtn6Click() {
                        initShock();
                        Air air = new Air();
                        String[] t = air.getT();
                        t[14] = "不显示";
                        air.setT(t);
                        String s = air.S();
                        Log.e("s", "" + s);
                        MqttMessage msgMessage = null;
                        msgMessage = new MqttMessage(s.getBytes());
                        try {
                            Log.e("s", "" + msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        } catch (MqttPersistenceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MqttException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                        }
                    }
                });
                airConditioningExtendedDialog.setBtn7OnclickListener(new AirConditioningExtendedDialog.onBtn7OnclickListener() {
                    @Override
                    public void onBtn7Click() {
                        initShock();
                        Air air = new Air();
                        String[] t = air.getT();
                        t[2] = "换气";
                        air.setT(t);
                        String s = air.S();
                        Log.e("s", "" + s);
                        MqttMessage msgMessage = null;
                        msgMessage = new MqttMessage(s.getBytes());
                        try {
                            Log.e("s", "" + msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        } catch (MqttPersistenceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MqttException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                        }

                    }
                });
                airConditioningExtendedDialog.setBtn8OnclickListener(new AirConditioningExtendedDialog.onBtn8OnclickListener() {
                    @Override
                    public void onBtn8Click() {
                        initShock();
                        Air air = new Air();
                        String[] t = air.getT();
                        t[3] = "扫风";
                        air.setT(t);
                        String s = air.S();
                        Log.e("s", "" + s);
                        MqttMessage msgMessage = null;
                        msgMessage = new MqttMessage(s.getBytes());
                        try {
                            Log.e("s", "" + msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        } catch (MqttPersistenceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MqttException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                        }
                    }
                });
                airConditioningExtendedDialog.setBtn9OnclickListener(new AirConditioningExtendedDialog.onBtn9OnclickListener() {
                    @Override
                    public void onBtn9Click() {
                        initShock();
                        Air air = new Air();
                        String[] t = air.getT();
                        t[4] = "睡眠";
                        air.setT(t);
                        String s = air.S();
                        Log.e("s", "" + s);
                        MqttMessage msgMessage = null;
                        msgMessage = new MqttMessage(s.getBytes());
                        try {
                            Log.e("s", "" + msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                            client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        } catch (MqttPersistenceException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MqttException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                        }

                    }
                });
                airConditioningExtendedDialog.show();
                break;
            default:
                break;
        }
    }

    private void initShock() {
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(new long[]{0, 100}, -1);
        }
    }

    private void setMode(String mode) {

        t = airControl.getT();
        t[0] = mode;
        airControl.setT(t);
        s = airControl.S();
        tvTemperature.setText(t[5]);
    }

    private void clearControl() {
        String dataValue = airControl.getDataValue();
        dataValue = "";
        airControl.setDataValue(dataValue);


    }

    private void init() {
        //        textView = (TextView) findViewById(R.id.textView1);//显示接收的消息文本框
        TelephonyManager mTm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        TelephonyIMEI = mTm.getDeviceId();
        //Toast.makeText(getApplicationContext(), TelephonyIMEI, 500).show();

        MyMqttInit();//初始化配置MQTT客户端
        mqttConnectThread.start();//执行连接服务器任务
    }

    /*按钮触摸事件*/
    //    private OnTouchListener buttonTouch = new OnTouchListener() {
    //
    //        @Override
    //        public boolean onTouch(View v, MotionEvent event)
    //        {
    //            MqttMessage msgMessage = null;//Mqtt消息变量
    //            if (event.getAction() == MotionEvent.ACTION_DOWN) //按下
    //            {
    //                msgMessage = new MqttMessage("1".getBytes());
    //            }
    //            else if (event.getAction() == MotionEvent.ACTION_UP) //松开
    //            {
    //                msgMessage = new MqttMessage("0".getBytes());
    //            }
    //
    //            try
    //            {
    //                //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
    //                client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息
    //            } catch (MqttPersistenceException e) {
    //                // TODO Auto-generated catch block
    //                e.printStackTrace();
    //            } catch (MqttException e) {
    //                // TODO Auto-generated catch block
    //                e.printStackTrace();
    //            }
    //            catch (Exception e) {
    //                //其余的状态msgMessage = null;所以加了这个catch (Exception e)
    //            }
    //
    //            return false;
    //        }
    //    };

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

        //        options.setUserName("username");//设置连接的用户名(自己的服务器没有设置用户名)

        //        options.setPassword("password".toCharArray());//设置连接的密码(自己的服务器没有设置密码)

        options.setConnectionTimeout(10);// 设置连接超时时间 单位为秒

        options.setKeepAliveInterval(20);// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制

        //        client.setCallback(new MqttCallback() {
        ////            @Override//获取消息会执行这里--arg0是主题,arg1是消息
        ////            public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
        ////                // TODO Auto-generated method stub
        ////                final String topic = arg0;//主题
        ////                final String msgString = arg1.toString();//消息
        ////
        ////                runOnUiThread(new Runnable() {//因为操作的是主界面的控件所以用刷新UI的线程,最好用handle哈,我这里怎么简单怎么写
        ////                    public void run() {
        ////                        //Toast.makeText(getApplicationContext(),"主题:"+topic+"消息:"+msgString, 500).show();
        ////                        textView.setText("主题:"+topic+"\n消息:"+msgString);
        ////                    }
        ////                });
        ////            }
        ////            @Override//订阅主题后会执行到这里
        ////            public void deliveryComplete(IMqttDeliveryToken arg0) {
        ////                // TODO Auto-generated method stub
        ////            }
        //            @Override//连接丢失后，会执行这里
        //            public void connectionLost(Throwable arg0) {
        //                // TODO Auto-generated method stub
        //
        //            }
        //        });
    }

    @OnClick({R.id.btn_heating, R.id.btn_dehumidification, R.id.btn_air_supply, R.id.btn_sweep_up_and_down, R.id.btn_sweep_left_and_right, R.id.btn_timing, R.id.btn_more,R.id.btn_off,R.id.ll_less,R.id.ll_plus,R.id.btn_refrigeration})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_heating:
                tvTemperature.setText("30");
                initShock();
                setMode("制热");
                MqttMessage hot = new MqttMessage(s.getBytes());
                try {
                    client.publish("/SmartHome/IR_remoter/set", hot);//发送主题为"/test/button"的消息
                    client.publish("/SmartHome/IR_remoter/set", hot);//发送主题为"/test/button"的消息
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
            case R.id.btn_dehumidification:
                initShock();
                setMode("除湿");
                MqttMessage cleathot = new MqttMessage(s.getBytes());
                try {
                    client.publish("/SmartHome/IR_remoter/set", cleathot);//发送主题为"/test/button"的消息
                    client.publish("/SmartHome/IR_remoter/set", cleathot);//发送主题为"/test/button"的消息
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
            case R.id.btn_air_supply:
                initShock();
                setMode("送风");
                tvTemperature.setText(t[5]);
                MqttMessage sendwind = new MqttMessage(s.getBytes());
                try {

                    client.publish("/SmartHome/IR_remoter/set", sendwind);//发送主题为"/test/button"的消息
                    client.publish("/SmartHome/IR_remoter/set", sendwind);//发送主题为"/test/button"的消息
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
            case R.id.btn_sweep_up_and_down:
                initShock();
                Air air3 = new Air();
                String[] t3 = air3.getT();
                t3[12] = "有";
                air3.setT(t3);
                String s3 = air3.S();
                Log.e("s", "" + s3);

                 msgMessage = new MqttMessage(s3.getBytes());
                try {
                    Log.e("s", "" + msgMessage);
                    client.publish("/SmartHome/IR_remoter/set", msgMessage);
                    client.publish("/SmartHome/IR_remoter/set", msgMessage);
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
            case R.id.btn_sweep_left_and_right:
                initShock();
                airControl = new Air();
                t = airControl.getT();
                t[13] = "有";
                airControl.setT(t);
                s = airControl.S();
                Log.e("s", "" + s);
                msgMessage = new MqttMessage(s.getBytes());
                try {

                    client.publish("/SmartHome/IR_remoter/set", msgMessage);
                    client.publish("/SmartHome/IR_remoter/set", msgMessage);
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
            case R.id.btn_timing:
                initShock();
                Air air = new Air();
                t = air.getT();
                if ("24".equals(t[6])) {
                    Log.e("24", "tgh");
                } else {
                    t[6] = String.valueOf(Integer.parseInt(t[6]) + 0.5);
                    air.setT(t);
                    String s = air.S();
                    Log.e("s", "" + s);
                    msgMessage = new MqttMessage(s.getBytes());
                    try {

                        client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        client.publish("/SmartHome/IR_remoter/set", msgMessage);
                    } catch (MqttPersistenceException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (MqttException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e) {
                        //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                    }
                }
                break;
            case R.id.btn_turn:
                initShock();
                t = airControl.getT();
                t[1] = "开机";
                airControl.setT(t);
                s = airControl.S();
                tvTemperature.setText(t[5]);
                Log.e("open", String.valueOf(airControl.S().length()));
                msgMessage = new MqttMessage(s.getBytes());
                try {
                    client.publish("/SmartHome/IR_remoter/set", msgMessage);//发送主题为"/test/button"的消息
                    client.publish("/SmartHome/IR_remoter/set",msgMessage);//发送主题为"/test/button"的消息
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
            case R.id.btn_off:
                initShock();
                t = airControl.getT();
                t[1] = "关机";
                airControl.setT(t);
                String code = "{\"IR_CODE\":\"9044,4472, 672,536,672,536,668,1644, 668,536,672,1640,668,1640,668,540,668,536,672, 536,668,540,668,1644,668,1640,668,536,672,536,668, 540,668,540,668,536,672,536,668,540,672,536,668,1640,672,1640,692,516,668,540,668,540,668,536,672,536,696,512,696,1612,696,516,692,1616,696,512,696,512,692,1620,692,512,692,19964,668,540,668,536,672,536,648,560,668,544,664,540,644,564,644,560,648,560,644,564,644,564,644,564,644,564,644,560,648,564,644,560,644,564,644,560,648,560,648,560,648,560,648,560,648,560,644,564,644,564,644,564,644,560,648,1664,644,564,644,1664,644,564,644,1668,644,600\"}";
                MqttMessage mqttMessageoff = new MqttMessage(code.getBytes());
                try {
                    client.publish("/SmartHome/IR_remoter/set", mqttMessageoff);//off发送主题为"/test/button"的消息
                    client.publish("/SmartHome/IR_remoter/set", mqttMessageoff);//发送主题为"/test/button"的消息
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
            case R.id.btn_less:
                initShock();
                t = airControl.getT();
                if ("16".equals(t[5])) {
                    Log.e("23", "tgh");
                } else {
                    t[5] = String.valueOf(Integer.parseInt(t[5]) - 1);
                    t[15] = String.valueOf(Integer.parseInt(t[5]));
                    tvTemperature.setText(t[5]);

                    airControl.setT(t);
                    clearControl();
                    s = airControl.S();
                    Log.e("TAG", "" + airControl.S().length());
                    //   msgMessage.clearPayload();
                    msgMessage = new MqttMessage(s.getBytes());
                    try {

                        client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        client.publish("/SmartHome/IR_remoter/set", msgMessage);
                    } catch (MqttPersistenceException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (MqttException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e) {
                        //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                    }
                }
                break;
            case R.id.btn_refrigeration:
                initShock();
                setMode("制冷");
                MqttMessage msgMessagecool = new MqttMessage(s.getBytes());
                try {
                    client.publish("/SmartHome/IR_remoter/set", msgMessagecool);//发送主题为"/test/button"的消息
                    client.publish("/SmartHome/IR_remoter/set", msgMessagecool);//发送主题为"/test/button"的消息
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
            case R.id.ll_plus:
                initShock();
                t = airControl.getT();
                if (t[5].equals("30")) {
                    Log.e("23", "tgh");
                } else {
                    t[5] = String.valueOf(Integer.parseInt(t[5]) + 1);
                    t[15] = String.valueOf(Integer.parseInt(t[5]));
                    tvTemperature.setText(t[5]);
                    airControl.setT(t);
                    s = airControl.S();
                    Log.e("TAG", "" + s);
                    msgMessage = new MqttMessage(s.getBytes());
                    try {
                        client.publish("/SmartHome/IR_remoter/set", msgMessage);
                        client.publish("/SmartHome/IR_remoter/set", msgMessage);
                    } catch (MqttPersistenceException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (MqttException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e) {
                        //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                    }
                }
                break;
                default:
        }
    }


    /*连接服务器任务*/
    class MqttConnectThread extends Thread {
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
