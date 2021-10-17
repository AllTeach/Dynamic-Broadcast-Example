package com.example.dynamicbroadcastexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void headsetReceiverClick(View view)
    {
        Toast.makeText(this,"headset clicked",Toast.LENGTH_SHORT).show();
    }
}