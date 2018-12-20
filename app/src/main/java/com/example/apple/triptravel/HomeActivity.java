package com.example.apple.triptravel;

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

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectFragment).commit();

                return true;
            }
        });
    }

    private void addControls() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }
}
