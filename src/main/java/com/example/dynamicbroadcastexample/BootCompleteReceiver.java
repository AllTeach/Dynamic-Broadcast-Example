package com.example.dynamicbroadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        Toast.makeText(context,"Boot Complete Broadcast Received",Toast.LENGTH_LONG).show();

    }
}