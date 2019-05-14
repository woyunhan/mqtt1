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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
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
public class FanRemoteControlActivity extends Activity implements View.OnClickListener {

    private MqttClient client;//client
    private MqttConnectOptions options;//配置
    String TelephonyIMEI = "";
    MqttConnectThread mqttConnectThread = new MqttConnectThread();//连接服务器任务
    private RemoteDao ordersDao;
    private TextView tv_control;
    private Button btn_turn_off;
    private Button btn_shaking_head;
    private Button btn_wind_speed;
    MqttMessage msgMessage = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);
        initView();
        initOnClickListener();
        init();
    }




    private void initView() {
        ordersDao = new RemoteDao(this);
        if (! ordersDao.isDataExist()){
            ordersDao.initTable();
        }
        tv_control=(TextView)findViewById(R.id.tv_control);
        tv_control.setText("艾美克风扇 airmate_fan_weizhi");
        btn_turn_off=(Button)findViewById(R.id.btn_turn_off);
        btn_shaking_head=(Button)findViewById(R.id.btn_shaking_head);
        btn_wind_speed=(Button)findViewById(R.id.btn_wind_speed);
    }
    private void initOnClickListener() {
        btn_turn_off.setOnClickListener(this);
        btn_shaking_head.setOnClickListener(this);
        btn_wind_speed.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_turn_off:
                initShock();

                List<Remote> turn_offOrders = ordersDao.getTurn_offOrder();
                for (Remote Remote : turn_offOrders){
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
            case R.id.btn_shaking_head:
                initShock();
                List<Remote> shaking_headOrders = ordersDao.getShaking_headOrder();
                for (Remote Remote : shaking_headOrders){
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
            case R.id.btn_wind_speed:
                initShock();
                List<Remote> wind_speedOrders = ordersDao.getWind_speedOrder();
                for (Remote Remote : wind_speedOrders){
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
    private void MyMqttInit()
    {
        try
        {
            //(1)主机地址(2)客户端ID,一般以客户端唯一标识符(不能够和其它客户端重名)(3)最后一个参数是指数据保存在内存(具体保存什么数据,以后再说,其实现在我也不是很确定)
            //client = new MqttClient("tcp://47.100.126.114:1883",TelephonyIMEI,new MemoryPersistence());
            client = new MqttClient("tcp://220.180.184.7:1883",TelephonyIMEI,new MemoryPersistence());
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
    class MqttConnectThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
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

