package com.gary.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*广播动态注册
1. 自定义一个类继承BroadcastReceiver，在onReceiver方法里交代要做的事情（方法里不要写太多代码和逻辑，广播接收器是不允许写线程的，当运行了较长时间没有结束系统会报错。
                        大概十秒左右没有结束系统会崩溃）
2.注册广播接收器（范例里是创建了一个接收器动态注册监听网络状态，这里使用一个简单的例子，可以和ManiActivity里的自定义广播发送结合）
            intentFilter = new IntentFilter();  //创建实例
        intentFilter.addAction("com.example.broadcast.MY_BROACAST"); //添加监听的信息
        secondReceiver = new SecondReceiver();  //创建继承BroadcastReceiver的Receiver实例
        registerReceiver(secondReceiver,intentFilter);  //创建广播接收器   Receiver实例，IntentFilter实例
3.注册完成后要记得在onDestroy()方法里调用方法取消注册（避免内存占用）
            unregisterReceiver(secondReceiver); //取消广播     参数是Receiver实例，即取消广播接收器

   发送广播： Intent intent = new Intent("com.example.broadcast.MY_BROADCAST");
               sendBroadcastReceiver(intent)  发送标准广播（无序广播）
                sendOrderedBroadcastReceiver(intent,null)  发送有序广播

二/本地广播
方法同上 区别在于local    通过 localBroadcastManager = LocalBroadcastManager.getInstance(this)获取的实例localBroadcastManager调用  注册广播 和 发送广播

 */
public class SecondActivity extends AppCompatActivity{

    private Button bt;
    private Button bt_local;
    private IntentFilter intentFilter;
    private SecondReceiver secondReceiver;
   // private NetworkChangeReceiver  networkChangeReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
         view();
    }

    private void view() {

        //2.注册广播接收器
        intentFilter = new IntentFilter();  //创建实例
        intentFilter.addAction("com.example.broadcast.MY_BROACAST"); //添加监听的信息
        //不采用网络监听代码,此处采用随意的信息
        // intentFilter.addAction("android.net.CONNECTIVITY_CHANGE"); 网络监听重点在这条标签信息
        secondReceiver = new SecondReceiver();  //创建继承BroadcastReceiver的Receiver实例
        registerReceiver(secondReceiver,intentFilter);  //创建广播接收器   Receiver实例，IntentFilter实例

        //发送广播
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.broadcast.MY_BROACAST");
                Toast.makeText(SecondActivity.this,"泰伯坦星呼叫中",Toast.LENGTH_SHORT).show();
                sendBroadcast(intent);
                // sendOrderedBroadcast(intent,null);   有序广播
            }
        });

        //跳转页面进入本地广播测试页
        bt_local = findViewById(R.id.bt_local);
        bt_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,LocalActivity.class);
                startActivity(intent);
            }
        });
    }

    //3.调用unregisterReceiver()方法取消注册   就是上面创建方法加  un 非的意思
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(secondReceiver); //取消广播     参数是Receiver实例，即取消广播接收器

    }

    //1.创建一个BroadcastReceiver类继承BroadcastReceiver
    class SecondReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"汽车人，出发",Toast.LENGTH_SHORT).show();
        }
    }
//    class NetworkChangeReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context,"网络有变动",Toast.LENGTH_SHORT).show();
//        }
//    }

}
