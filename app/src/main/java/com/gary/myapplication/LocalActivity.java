package com.gary.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LocalActivity extends AppCompatActivity {

    /*本地广播动态注册
1. 自定义一个类继承BroadcastReceiver，在onReceiver方法里交代要做的事情（方法里不要写太多代码和逻辑，广播接收器是不允许写线程的，当运行了较长时间没有结束系统会报错。
                        大概十秒左右没有结束系统会崩溃）
2.注册广播接收器（范例里是创建了一个接收器动态注册监听网络状态，这里使用一个简单的例子，可以和ManiActivity里的自定义广播发送结合）
        localBroadcastManager = LocalBroadcastManager.getInstance(this);   //通过getInstance(this)获取实例
        intentFilter = new IntentFilter();                                //创建实例
        localReceiver = new LocalReceiver();                              / /创建继承BroadcastReceiver的Receiver实例
        intentFilter.addAction("com.example.broadcast.MY_BROACAST");     / /添加监听的信息
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);  //创建广播接收器 localBroadcastManager调用  注册广播
3.注册完成后要记得在onDestroy()方法里调用方法取消注册（避免内存占用）
            unregisterReceiver(secondReceiver); //取消广播     参数是Receiver实例，即取消接收器

   发送广播： Intent intent = new Intent("com.example.broadcast.MY_BROACAST");    //发送的广播的信息
                localBroadcastManager.sendBroadcast(intent);           // localBroadcastManager调用  发送广播


二/本地广播
方法同上 区别在于local   通过 localBroadcastManager = LocalBroadcastManager.getInstance(this)获取的实例localBroadcastManager调用  注册广播 注销广播 和 发送广播

 */

    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;
    private IntentFilter intentFilter;
    private Button bt_local;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_activity);
        view();
    }

    private void view() {
        //本地广播注册
        localBroadcastManager = LocalBroadcastManager.getInstance(this); //通过getInstance(this)获取实例

        intentFilter = new IntentFilter();
        localReceiver = new LocalReceiver();
        intentFilter.addAction("com.example.broadcast.MY_BROACAST");
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);

        //发送广播
        bt_local = findViewById(R.id.bt_local);
        bt_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcast.MY_BROACAST");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
    

    //1.创建一个类继承BroadcastReceiver
    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"接收到本地广播，本地广播只能在线程内传递",Toast.LENGTH_SHORT).show();
        }
    }
}
