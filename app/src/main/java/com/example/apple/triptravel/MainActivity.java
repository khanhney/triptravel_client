package com.example.apple.triptravel;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final static int SET_TIMEOUT_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();

                intent.setClass(MainActivity.this, SecondActivity.class);

                startActivity(intent);

                finish();
            }
        }, SET_TIMEOUT_TIME);
    }
}
