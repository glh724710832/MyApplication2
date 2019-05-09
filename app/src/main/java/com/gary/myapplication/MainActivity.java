package com.gary.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
/*一/静态注册的步骤
     1注册广播.自定义类继承BroadcastReceiver类，重写onReceiver方法，接收广播（onReceiver方法在可以根据自己的需求设置代码）
     2.在Mainfest里申明 <receiver,并在 <intent-filter>里申明action属性
     3.在Activity中用按钮或TextView发送广播
            Intent intent = new Intent("action标签信息")
                        备注：8.0后静态注册被弃用，如果要使用需要设置权限在intent里面加  component（“广播接收者的包名”，“广播接收者路径”）
                                intent.setComponent(new ComponentName( "",""  ));

           一/ sendBroadcastReceiver(intent)  发送标准广播（无序广播）
                        备注： 因为权限问题，所以一次只有一个广播能响应，在动态注册里不受影响。
            二/
            sendOrderedBroadcastReceiver(intent,null)  发送有序广播
                         备注：第二个参数是权限相关字符串，传入null即可
                                   1. 优先级：  有序广播在<receiver里可以设置priority    <intent-filter android:priority="1000">
                                              priority的最大值是多少呢？
                                              优先级的范围是：-2147483647到2147483647
                                              刚好它的长度范围是Integer类型数据的最大值4294967296（2的32次方）。
                                   2.abortBroadcast()方法  将广播截断（在广播接收器里onReceiver方法里调用）
 */
    private TextView tv;
    private TextView tv_ordered;
    private Button  bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view();

    }

    private void view() {

        tv =findViewById(R.id.tv);
        tv_ordered =findViewById(R.id.tv_ordered);
        bt =findViewById(R.id.bt);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcast.MY_BROACAST");
                intent.setComponent(new ComponentName("com.gary.myapplication","com.gary.myapplication.AnotherBroadcastReceiver"));
                sendBroadcast(intent);
            }
        });

        tv_ordered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcast.MY_BROADCAST");
                intent.setComponent(new ComponentName("com.gary.myapplication","com.gary.myapplication.OrderedBroadcastReceiver"));
                sendOrderedBroadcast(intent,null);
            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}
