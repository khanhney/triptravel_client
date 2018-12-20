package com.example.apple.triptravel;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dmax.dialog.SpotsDialog;

public class SecondActivity extends AppCompatActivity {

    Button btnCreateAccount;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);

        textView         = (TextView) findViewById(R.id.txtSignIn);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(SecondActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("INFO_USER_AND_TOKEN", MODE_PRIVATE);
                final Intent intent = new Intent();

                if (sharedPreferences.contains("Token")){
                    intent.setClass(SecondActivity.this, HomeActivity.class);
                }else {
                    intent.setClass(SecondActivity.this, SignUpActivity.class);
                }

                final AlertDialog dialog = new SpotsDialog.Builder().setContext(SecondActivity.this).build();
                dialog.setMessage("Loading...");
                dialog.setCancelable(false);
                dialog.show();

//                final Dialog dialog = new Dialog(SecondActivity.this);
//                dialog.setContentView(R.layout.custom_dialog);
//                dialog.setCancelable(false);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        startActivity(intent);

                    }
                }, 1000);


            }
        });
    }

}
