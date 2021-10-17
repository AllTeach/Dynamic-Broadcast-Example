package com.example.dynamicbroadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        int state = 0;
        if (stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            Toast.makeText(context, "Phone state IDLE", Toast.LENGTH_SHORT).show();
        } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            Toast.makeText(context, "Phone state CALL_STATE_OFFHOOK", Toast.LENGTH_SHORT).show();
        } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            Toast.makeText(context, "Phone state CALL_STATE_RINGING", Toast.LENGTH_SHORT).show();

        }
    }
}