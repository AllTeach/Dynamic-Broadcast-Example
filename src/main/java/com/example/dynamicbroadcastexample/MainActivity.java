package com.example.dynamicbroadcastexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PHONE_STATE = 1;
    private HeadSetReceiver headSetReceiver;
    private PhoneStateReceiver phoneReceiver;
    private boolean isRegistered = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.headSetReceiver = new HeadSetReceiver();
        this.phoneReceiver = new PhoneStateReceiver();


    }



    @Override
    protected void onStart() {
        super.onStart();
        if (checkPermission() )
        {
            IntentFilter phoneFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
            registerReceiver(phoneReceiver,phoneFilter);
            isRegistered = true;

        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isRegistered)
        {
            unregisterReceiver(phoneReceiver);
            isRegistered=false;
        }
    }

    // check if permission is granted from accessing phone state
    private boolean checkPermission()
    {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_STATE) ==
                PackageManager.PERMISSION_GRANTED)
                return true;


            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.READ_PHONE_STATE },
                    REQUEST_CODE_PHONE_STATE);
        return false;


    }

    // register / unregister headset receiver
    public void headsetReceiverClick(View view)
    {
        Button b = (Button)view;
        String str = b.getText().toString();

        // create the intent filter and
        // register  receiver dynamically
        // Register for the headset plugged/unplugged event:
        IntentFilter headSetFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);

        // this means we register the receiver
        if(str.equals(getString(R.string.headset_register))) {
            b.setText(R.string.headset_unregister);
            registerReceiver(headSetReceiver,headSetFilter);

        }
        // this means we unregister the receiver
        else {
            b.setText(getString(R.string.headset_register));
            unregisterReceiver(headSetReceiver);
        }



    }

    // reach here after user accepted or declined permission request

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {


            case REQUEST_CODE_PHONE_STATE:
                // this means permission granted
                // register receiver
                // set the flag = true
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "permission granted for phone state", Toast.LENGTH_SHORT).show();
                    IntentFilter phoneFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
                    registerReceiver(phoneReceiver,phoneFilter);
                    isRegistered = true;

                } else
                    Toast.makeText(this, "permission denied  for phone state", Toast.LENGTH_SHORT).show();
                break;

        }
    }




}