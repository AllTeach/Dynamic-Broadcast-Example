package com.example.dynamicbroadcastexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private HeadSetReceiver headSetReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.headSetReceiver = new HeadSetReceiver();
    }

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
}