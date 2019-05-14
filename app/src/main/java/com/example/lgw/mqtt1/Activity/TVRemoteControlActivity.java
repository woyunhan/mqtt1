package com.example.lgw.mqtt1.Activity;


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

import com.example.lgw.mqtt1.R;
import com.example.lgw.mqtt1.remote.Remote;
import com.example.lgw.mqtt1.remote.RemoteDao;
import com.example.lgw.mqtt1.view.InfoDialog;
import com.example.lgw.mqtt1.view.TVExtendedDialog;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.List;



import butterknife.OnClick;


public class TVRemoteControlActivity extends Activity implements View.OnClickListener {

    private MqttClient client;//client
    private MqttConnectOptions options;//配置
    String TelephonyIMEI = "";
    MqttConnectThread mqttConnectThread = new MqttConnectThread();//连接服务器任务
    private RemoteDao ordersDao;
    private TextView tv_control;
    private InfoDialog infoDialog;
    private TVExtendedDialog tvExtendedDialog;
    private Button btn_turn;
    private Button btn_menu;
    private Button btn_tvav;
    private Button btn_chanel;
    private Button btn_reminisce;
    private Button btn_mute;
    private Button btn_collection;
    private Button btn_drop_out;
    private Button btn_more;
    private RelativeLayout rl_plus;
    private RelativeLayout rl_less;
    private RelativeLayout rl_previous;
    private RelativeLayout rl_next;
    private Button btn_up;
    private Button btn_down;
    private Button btn_left;
    private Button btn_right;
    private TextView tv_ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        initView();
        initOnClickListener();
        init();
    }


    private void initView() {
        ordersDao = new RemoteDao(this);
        if (!ordersDao.isDataExist()) {
            ordersDao.initTable();
        }
        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_control = (TextView) findViewById(R.id.tv_control);
        tv_control.setText("索尼电视 tv_sony_rm_sd021");
        btn_turn = (Button) findViewById(R.id.btn_turn);
        btn_menu = (Button) findViewById(R.id.btn_menu);
        btn_tvav = (Button) findViewById(R.id.btn_tvav);
        btn_chanel = (Button) findViewById(R.id.btn_chanel);
        btn_reminisce = (Button) findViewById(R.id.btn_reminisce);
        btn_mute = (Button) findViewById(R.id.btn_mute);
        btn_collection = (Button) findViewById(R.id.btn_collection);
        btn_drop_out = (Button) findViewById(R.id.btn_drop_out);
        btn_more = (Button) findViewById(R.id.btn_more);
        rl_plus = (RelativeLayout) findViewById(R.id.rl_plus);
        rl_less = (RelativeLayout) findViewById(R.id.rl_less);
        rl_previous = (RelativeLayout) findViewById(R.id.rl_previous);
        rl_next = (RelativeLayout) findViewById(R.id.rl_next);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_down = (Button) findViewById(R.id.btn_down);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_right = (Button) findViewById(R.id.btn_right);


    }

    private void initOnClickListener() {
        tv_ok.setOnClickListener(this);
        btn_turn.setOnClickListener(this);
        btn_menu.setOnClickListener(this);
        btn_tvav.setOnClickListener(this);
        btn_chanel.setOnClickListener(this);
        btn_reminisce.setOnClickListener(this);
        btn_mute.setOnClickListener(this);
        btn_collection.setOnClickListener(this);
        btn_drop_out.setOnClickListener(this);
        btn_more.setOnClickListener(this);
        rl_plus.setOnClickListener(this);
        rl_less.setOnClickListener(this);
        rl_previous.setOnClickListener(this);
        rl_next.setOnClickListener(this);
        btn_up.setOnClickListener(this);
        btn_down.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MqttMessage msgMessage = null;
        switch (view.getId()) {
            case R.id.btn_turn:
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
            case R.id.btn_menu:
                initShock();
                List<Remote> menuOrders = ordersDao.getMenuOrder();
                for (Remote Remote : menuOrders) {
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
            case R.id.btn_tvav:
                initShock();
                List<Remote> tvavOrders = ordersDao.getTvavOrder();
                for (Remote Remote : tvavOrders) {
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
            case R.id.btn_more:
                tvExtendedDialog = new TVExtendedDialog(TVRemoteControlActivity.this);
                tvExtendedDialog.setBtn1OnclickListener(new TVExtendedDialog.onBtn1OnclickListener() {
                    @Override
                    public void onBtn1Click() {
                        initShock();
                        MqttMessage msgMessage = null;
                        List<Remote> btn1Orders = ordersDao.getBtn1Order();
                        for (Remote Remote : btn1Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn2OnclickListener(new TVExtendedDialog.onBtn2OnclickListener() {
                    @Override
                    public void onBtn2Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn2Orders = ordersDao.getBtn2Order();
                        for (Remote Remote : btn2Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn3OnclickListener(new TVExtendedDialog.onBtn3OnclickListener() {
                    @Override
                    public void onBtn3Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn3Orders = ordersDao.getBtn3Order();
                        for (Remote Remote : btn3Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn4OnclickListener(new TVExtendedDialog.onBtn4OnclickListener() {
                    @Override
                    public void onBtn4Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn4Orders = ordersDao.getBtn4Order();
                        for (Remote Remote : btn4Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn5OnclickListener(new TVExtendedDialog.onBtn5OnclickListener() {
                    @Override
                    public void onBtn5Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn5Orders = ordersDao.getBtn5Order();
                        for (Remote Remote : btn5Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn6OnclickListener(new TVExtendedDialog.onBtn6OnclickListener() {
                    @Override
                    public void onBtn6Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn6Orders = ordersDao.getBtn6Order();
                        for (Remote Remote : btn6Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn7OnclickListener(new TVExtendedDialog.onBtn7OnclickListener() {
                    @Override
                    public void onBtn7Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn7Orders = ordersDao.getBtn7Order();
                        for (Remote Remote : btn7Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn8OnclickListener(new TVExtendedDialog.onBtn8OnclickListener() {
                    @Override
                    public void onBtn8Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn8Orders = ordersDao.getBtn8Order();
                        for (Remote Remote : btn8Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn9OnclickListener(new TVExtendedDialog.onBtn9OnclickListener() {
                    @Override
                    public void onBtn9Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn9Orders = ordersDao.getBtn9Order();
                        for (Remote Remote : btn9Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn10OnclickListener(new TVExtendedDialog.onBtn10OnclickListener() {
                    @Override
                    public void onBtn10Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn10Orders = ordersDao.getBtn10Order();
                        for (Remote Remote : btn10Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn11OnclickListener(new TVExtendedDialog.onBtn11OnclickListener() {
                    @Override
                    public void onBtn11Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn11Orders = ordersDao.getBtn11Order();
                        for (Remote Remote : btn11Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn12OnclickListener(new TVExtendedDialog.onBtn12OnclickListener() {
                    @Override
                    public void onBtn12Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn12Orders = ordersDao.getBtn12Order();
                        for (Remote Remote : btn12Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn13OnclickListener(new TVExtendedDialog.onBtn13OnclickListener() {
                    @Override
                    public void onBtn13Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn13Orders = ordersDao.getBtn13Order();
                        for (Remote Remote : btn13Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn14OnclickListener(new TVExtendedDialog.onBtn14OnclickListener() {
                    @Override
                    public void onBtn14Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn14Orders = ordersDao.getBtn14Order();
                        for (Remote Remote : btn14Orders) {
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

                    }
                });
                tvExtendedDialog.setBtn15OnclickListener(new TVExtendedDialog.onBtn15OnclickListener() {
                    @Override
                    public void onBtn15Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> btn15Orders = ordersDao.getBtn15Order();
                        for (Remote Remote : btn15Orders) {
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

                    }
                });
                tvExtendedDialog.show();
                break;
            case R.id.btn_reminisce:
                Vibrator vibrator3 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator3.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage3 = null;
                List<Remote> reminiseceOrders = ordersDao.getReminisceOrder();
                for (Remote Remote : reminiseceOrders) {
                    msgMessage3 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage3);//发送主题为"/test/button"的消息
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
            case R.id.btn_mute:
                Vibrator vibrator4 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator4.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage4 = null;
                List<Remote> muteOrders = ordersDao.getMuteOrder();
                for (Remote Remote : muteOrders) {
                    msgMessage4 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage4);//发送主题为"/test/button"的消息
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
            case R.id.tv_ok:
                Vibrator vibrator5 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator5.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage5 = null;
                List<Remote> okOrders = ordersDao.getOkOrder();
                for (Remote Remote : okOrders) {
                    msgMessage5 = new MqttMessage((Remote.code).getBytes());
                }
                Log.e("tag", "" + msgMessage5);
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage5);//发送主题为"/test/button"的消息
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
            case R.id.btn_drop_out:
                Vibrator vibrator6 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator6.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage6 = null;
                List<Remote> drop_outOrders = ordersDao.getDrop_outOrder();
                for (Remote Remote : drop_outOrders) {
                    msgMessage6 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage6);//发送主题为"/test/button"的消息
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
            case R.id.btn_chanel:
                infoDialog = new InfoDialog(TVRemoteControlActivity.this);
                infoDialog.setBtn_chanel0OnclickListener(new InfoDialog.onBtn_chanel0OnclickListener() {
                    @Override
                    public void onBtn_chanel0Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(new long[]{0, 100}, -1);
                        }
                        MqttMessage msgMessage = null;
                        List<Remote> zeroOrders = ordersDao.getZeroOrder();
                        for (Remote Remote : zeroOrders) {
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

                    }
                });
                infoDialog.setBtn_chanel1OnclickListener(new InfoDialog.onBtn_chanel1OnclickListener() {
                    @Override
                    public void onBtn_chanel1Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> oneOrders = ordersDao.getOneOrder();
                        for (Remote Remote : oneOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_chanel2OnclickListener(new InfoDialog.onBtn_chanel2OnclickListener() {
                    @Override
                    public void onBtn_chanel2Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> twoOrders = ordersDao.getTwoOrder();
                        for (Remote Remote : twoOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_chanel3OnclickListener(new InfoDialog.onBtn_chanel3OnclickListener() {
                    @Override
                    public void onBtn_chanel3Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> threeOrders = ordersDao.getThreeOrder();
                        for (Remote Remote : threeOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_chanel4OnclickListener(new InfoDialog.onBtn_chanel4OnclickListener() {
                    @Override
                    public void onBtn_chanel4Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> fourOrders = ordersDao.getFourOrder();
                        for (Remote Remote : fourOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_chanel5OnclickListener(new InfoDialog.onBtn_chanel5OnclickListener() {
                    @Override
                    public void onBtn_chanel5Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> fiveOrders = ordersDao.getFiveOrder();
                        for (Remote Remote : fiveOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_chanel6OnclickListener(new InfoDialog.onBtn_chanel6OnclickListener() {
                    @Override
                    public void onBtn_chanel6Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> sixOrders = ordersDao.getSixOrder();
                        for (Remote Remote : sixOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_chanel7OnclickListener(new InfoDialog.onBtn_chanel7OnclickListener() {
                    @Override
                    public void onBtn_chanel7Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> sevenOrders = ordersDao.getSevenOrder();
                        for (Remote Remote : sevenOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_chanel8OnclickListener(new InfoDialog.onBtn_chanel8OnclickListener() {
                    @Override
                    public void onBtn_chanel8Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> eightOrders = ordersDao.getEightOrder();
                        for (Remote Remote : eightOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_chanel9OnclickListener(new InfoDialog.onBtn_chanel9OnclickListener() {
                    @Override
                    public void onBtn_chanel9Click() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> nineOrders = ordersDao.getNineOrder();
                        for (Remote Remote : nineOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_chanel_inputOnclickListener(new InfoDialog.onBtn_chanel_inputOnclickListener() {
                    @Override
                    public void onBtn_chanel_inputClick() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.setBtn_returnOnclickListener(new InfoDialog.onBtn_returnOnclickListener() {
                    @Override
                    public void onBtn_returnClick() {
                        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                        vibrator.vibrate(new long[]{0, 100}, -1);
                        MqttMessage msgMessage = null;
                        List<Remote> returnOrders = ordersDao.getReturnOrder();
                        for (Remote Remote : returnOrders) {
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
                        //infoDialog.dismiss();
                    }
                });
                infoDialog.show();

                break;
            case R.id.rl_plus:
                Vibrator vibrator7 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator7.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage7 = null;
                List<Remote> plusOrders = ordersDao.getPlusOrder();
                for (Remote Remote : plusOrders) {
                    msgMessage7 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage7);//发送主题为"/test/button"的消息
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
            case R.id.rl_less:
                Vibrator vibrator8 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator8.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage8 = null;
                List<Remote> lessOrders = ordersDao.getLessOrder();
                for (Remote Remote : lessOrders) {
                    msgMessage8 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage8);//发送主题为"/test/button"的消息
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
            case R.id.rl_previous:
                Vibrator vibrator9 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator9.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage9 = null;
                List<Remote> previousOrders = ordersDao.getPreviousOrder();
                for (Remote Remote : previousOrders) {
                    msgMessage9 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage9);//发送主题为"/test/button"的消息
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
            case R.id.rl_next:
                Vibrator vibrator10 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator10.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage10 = null;
                List<Remote> nextOrders = ordersDao.getNextOrder();
                for (Remote Remote : nextOrders) {
                    msgMessage10 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage10);//发送主题为"/test/button"的消息
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
            case R.id.btn_up:
                Vibrator vibrator11 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator11.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage11 = null;
                List<Remote> upOrders = ordersDao.getUpOrder();
                for (Remote Remote : upOrders) {
                    msgMessage11 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage11);//发送主题为"/test/button"的消息
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
            case R.id.btn_down:
                Vibrator vibrator12 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator12.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage12 = null;
                List<Remote> downOrders = ordersDao.getDownOrder();
                for (Remote Remote : downOrders) {
                    msgMessage12 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage12);//发送主题为"/test/button"的消息
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
            case R.id.btn_left:
                Vibrator vibrator13 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator13.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage13 = null;
                List<Remote> leftOrders = ordersDao.getLeftOrder();
                for (Remote Remote : leftOrders) {
                    msgMessage13 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息请注意
                    client.publish("/SmartHome/IR_remoter/set", msgMessage13);//发送主题为"/test/button"的消息
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
            case R.id.btn_right:
                Vibrator vibrator14 = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator14.vibrate(new long[]{0, 100}, -1);
                MqttMessage msgMessage14 = null;
                List<Remote> rightOrders = ordersDao.getRightOrder();
                for (Remote Remote : rightOrders) {
                    msgMessage14 = new MqttMessage((Remote.code).getBytes());
                }
                try {
                    //client.publish("/test/button",msgMessage);//发送主题为"/test/button"的消息
                    //client.publish("inTopic",msgMessage);//发送主题为"/test/button"的消息
                    client.publish("/SmartHome/IR_remoter/set", msgMessage14);//发送主题为"/test/button"的消息
                } catch (MqttPersistenceException e) {

                    e.printStackTrace();
                } catch (MqttException e) {

                    e.printStackTrace();
                } catch (Exception e) {
                    //其余的状态msgMessage = null;所以加了这个catch (Exception e)
                }
                break;

            default:
                break;
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

