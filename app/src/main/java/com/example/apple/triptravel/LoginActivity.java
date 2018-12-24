package com.example.apple.triptravel;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.triptravel.common.UserCommon;
import com.example.apple.triptravel.interfaces.GetUserService;
import com.example.apple.triptravel.models.login.DataUserLogin;
import com.example.apple.triptravel.models.login.Login;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnLogin;

    private GetUserService service;

    private TextView txtResetPassword, txtUsernameOrEmail, txtPassword;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        service =  UserCommon.signUserUser();

        toolbar = findViewById(R.id.toolbar_login);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        addControls();
        addEvents();

    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(LoginActivity.this, HomeActivity.class);
//
//                startActivity(intent);


                String username_or_email    = txtUsernameOrEmail.getText().toString();
                String password             = txtPassword.getText().toString();

                if (TextUtils.isEmpty(username_or_email) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    login(username_or_email, password);
                }
            }
        });

        txtResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });
    }

    private void login(String username_or_email, String password) {
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(LoginActivity.this).build();
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();


        service.signInUser(username_or_email, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, final Response<Login> response) {
                if (response.isSuccessful()){
                    if (response.body().getError() == true){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }, 1000);
                    }else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                DataUserLogin login = response.body().getData().getDataUser();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Username", login.getUsername());
                                editor.putString("Email", login.getEmail());
                                editor.putString("Password", login.getPassword());
                                editor.putString("ID", login.getId());
                                editor.putString("Token", response.body().getData().getToken());

                                editor.apply();


                                Toast.makeText(LoginActivity.this, "Xin chào: " + login.getUsername(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("User", login);
                                intent.putExtra("TOKEN", response.body().getData().getToken());

                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                        dialog.dismiss();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Lỗi server!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.e("ERROR_LOGIN", t.getMessage());
            }
        });
    }

    private void addControls() {
        txtResetPassword = findViewById(R.id.txtResetPasswordPageLogin);
        btnLogin = (Button) findViewById(R.id.btnLoginPage);
        txtUsernameOrEmail = findViewById(R.id.txtEmailLoginPage);
        txtPassword = findViewById(R.id.txtPasswordLoginPage);

        sharedPreferences = getSharedPreferences("INFO_USER_AND_TOKEN",
                MODE_PRIVATE);

        if (sharedPreferences.contains("Username")){
            txtUsernameOrEmail.setText(sharedPreferences.getString("Username", ""));
        }

        String token = sharedPreferences.getString("Token", "");

        if (sharedPreferences.contains("Token") && !TextUtils.isEmpty(token)){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            String username = sharedPreferences.getString("Username", "");
            String email    = sharedPreferences.getString("Email", "");
            String password = sharedPreferences.getString("Password", "");
            String id       = sharedPreferences.getString("ID", "");


            DataUserLogin login = new DataUserLogin(id, username, email, password);
            intent.putExtra("User", login);
            startActivity(intent);
            finish();
        }
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
