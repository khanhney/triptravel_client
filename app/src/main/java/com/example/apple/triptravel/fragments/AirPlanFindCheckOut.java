package com.example.apple.triptravel.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.apple.triptravel.R;

public class AirPlanFindCheckOut extends Fragment {
    
    private Button btnPayNow;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_flights_checkout, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_FlightFind);
        btnPayNow = view.findViewById(R.id.btnPayNow);
        
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new TripFragment(), "trip_fragment")
                        .addToBackStack(null)
                        .commit();

//                getActivity().getSupportFragmentManager().popBackStack("trip_fragment", 0);
                Toast.makeText(getActivity(), "Đặt vé thành công!", Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AirPlanFindCreateViewFragment()).commit();
            getActivity().getSupportFragmentManager().popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }
}
