package com.example.android.bloodbank;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "1";
    Button buttonDonor;
    Button buttonReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"Executed till here");

        buttonDonor = (Button) findViewById(R.id.Donor);
        buttonDonor.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,donorActivity.class);
                startActivity(i);
            }
        });

        buttonReceiver = (Button) findViewById(R.id.Receiver);
        buttonReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, receiverActivity.class));
            }
        });
    }
}


