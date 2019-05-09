package com.gary.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OrderedBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"有序广播信息接收了  12121",Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
