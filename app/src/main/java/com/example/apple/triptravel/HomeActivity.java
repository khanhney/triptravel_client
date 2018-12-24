package com.example.apple.triptravel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.apple.triptravel.fragments.AirplanFragment;
import com.example.apple.triptravel.fragments.ChatFragment;
import com.example.apple.triptravel.fragments.HotelFragment;
import com.example.apple.triptravel.fragments.ProfileFragment;
import com.example.apple.triptravel.fragments.TripFragment;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addControls();
        addEvents();

//        add default screen
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TripFragment()).commit();
    }

    private void addEvents() {
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectFragment = null;

                switch (menuItem.getItemId()){
                    case R.id.navigation_trips:
                        selectFragment = new TripFragment();
                        break;

                    case R.id.navigation_airplanes:
                        selectFragment = new AirplanFragment();
                        break;

                    case R.id.navigation_chatbot:
                        selectFragment = new ChatFragment();
                        break;

                    case R.id.navigation_hotels:
                        selectFragment = new HotelFragment();
                        break;

                    case R.id.navigation_profiles:
                        selectFragment = new ProfileFragment();
                        break;
                }

                if (selectFragment != null){
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, selectFragment).commit();
                }

                return true;
            }
        });
    }

    private void showLogoutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Log out");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setIcon(R.drawable.logout);
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences =HomeActivity.this.getSharedPreferences("INFO_USER_AND_TOKEN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("Token");
                editor.apply();

                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });

        builder.show();
    }

    @Override
    public void onBackPressed() {
        showLogoutDialog();
    }

    private void addControls() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }
}
