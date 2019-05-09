package com.gary.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ThirdBroadcastReceiver extends BroadcastReceiver {
//不使用
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"无序广播2：静态注册启用 ", Toast.LENGTH_SHORT).show();

    }
}
