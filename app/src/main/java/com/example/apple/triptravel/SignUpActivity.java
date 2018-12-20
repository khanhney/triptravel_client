package com.example.apple.triptravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.triptravel.common.UserCommon;
import com.example.apple.triptravel.interfaces.GetUserService;
import com.example.apple.triptravel.models.sign_up.DataUser;
import com.example.apple.triptravel.models.sign_up.User;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    GetUserService service;

    Toolbar toolbar;
    TextView textView;
    private Button btnSignUpRegisterPage;
    private EditText editEmailSignUpPage, editUsernameSignUpPage, editPasswordSignUpPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        service = UserCommon.signUserUser();

        toolbar = findViewById(R.id.toolbar_signUp);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        addControls();
        addEvents();
    }

    private void addControls() {
        textView                = (TextView) findViewById(R.id.txtSignInRegisterPage);
        btnSignUpRegisterPage   = findViewById(R.id.btnSignUpRegisterPage);
        editEmailSignUpPage     = findViewById(R.id.editEmailSignUpPage);
        editUsernameSignUpPage  = findViewById(R.id.editUsernameSignUpPage);
        editPasswordSignUpPage  = findViewById(R.id.editPasswordSignUpPage);


    }

    private void addEvents() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(SignUpActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });

        btnSignUpRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username         = editUsernameSignUpPage.getText().toString();
                String password         = editPasswordSignUpPage.getText().toString();
                String email            = editEmailSignUpPage.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpActivity.this, "Vui lòng hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    signUp(username, email, password);
                }
            }
        });
    }

    private void signUp(String username, String email, String password) {
        final SpotsDialog.Builder dialog = new SpotsDialog.Builder().setContext(SignUpActivity.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.build().show();

        service.signUpUser(new DataUser(username, email, password)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                try {
                    DataUser user = response.body().getData();
                    if(response.isSuccessful()){
                        if (response.body().getError() == true){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.build().dismiss();
                                    Toast.makeText(SignUpActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                                }
                            }, 1000);
                        }else{
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.build().dismiss();
                                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }, 1000);

                        }
                    }else {
                        Toast.makeText(SignUpActivity.this, "user null", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.e("ERROR_", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR_", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }
}
