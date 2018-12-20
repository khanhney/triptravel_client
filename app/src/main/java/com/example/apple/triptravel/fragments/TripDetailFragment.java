package com.example.apple.triptravel.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.models.trip.DataTrip;
import com.squareup.picasso.Picasso;


public class TripDetailFragment extends Fragment{

    private ImageView imagTripDetail;
    private TextView
            txtTitleTripDetail,
            txtSluganTripDetail,
            txtTitleOfDescriptionTripDetail,
            txtDescriptionTripDetail,
            txtTitleOfDescription2TripDetail,
            txtDescription2TripDetail;

    private DataTrip dataTrip;

    private ImageView img1, img2, img3, img4;

    private Button btnBookingTrip;


    //Map view

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_trip_detail, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_TripDetail);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        addControls(view);
        addEvents();

        return view;
    }

    private void addEvents() {
        btnBookingTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AirplanFindFragment()).commit();
            }
        });
    }

    private void addControls(View view) {
        btnBookingTrip = view.findViewById(R.id.btnBookingTrip);


        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);

        Picasso.get().load("https://finduslost.com/wp-content/uploads/2016/07/Santorini-Oia-Greek-Islands-Greece-finduslost-top-travel-destination-2016-selena-jacob-wanderlust-find-us-lost-blog-couple-instagram.jpg").into(img1);
        Picasso.get().load("https://finduslost.com/wp-content/uploads/2016/07/Hofsjokull-Glacier-Iceland-finduslost-top-travel-destination-2016-selena-jacob-wanderlust-find-us-lost-blog-couple-instagram.jpg").into(img2);
        Picasso.get().load("https://finduslost.com/wp-content/uploads/2016/07/Rhone-Alps-Hery-Sur-Alby-France-finduslost-top-travel-destination-2016-selena-jacob-wanderlust-find-us-lost-blog-couple-instagram.jpg").into(img3);
        Picasso.get().load("https://finduslost.com/wp-content/uploads/2016/07/Predjama-Castle-Slovenia-finduslost-top-travel-destination-2016-selena-jacob-wanderlust-find-us-lost-blog-couple-instagram.jpg").into(img4);


        imagTripDetail = view.findViewById(R.id.imgTripDetail);
        txtTitleTripDetail = view.findViewById(R.id.txtTitleTripDetail);
        txtSluganTripDetail = view.findViewById(R.id.txtSluganTripDetail);
        txtTitleOfDescriptionTripDetail = view.findViewById(R.id.txtTitleOfDescriptionTripDetail);
        txtDescriptionTripDetail = view.findViewById(R.id.txtDescriptionTripDetail);
        txtTitleOfDescription2TripDetail = view.findViewById(R.id.txtTitleOfDescription2TripDetail);
        txtDescription2TripDetail = view.findViewById(R.id.txtDescription2TripDetail);

        Bundle bundle = getArguments();
        if (bundle != null) {
            dataTrip = (DataTrip) bundle.getSerializable("DATA_TRIP");

            Picasso.get().load(Utils.BASE_URL + dataTrip.getImage()).into(imagTripDetail);
            txtTitleTripDetail.setText(dataTrip.getTitle().substring(0, 20) + "...");
            txtSluganTripDetail.setText(dataTrip.getDescription().substring(0, 20));
            txtTitleOfDescriptionTripDetail.setText(dataTrip.getTitle());
            txtDescriptionTripDetail.setText(dataTrip.getDescription());
            txtTitleOfDescription2TripDetail.setText(dataTrip.getTitle());
            txtDescription2TripDetail.setText(dataTrip.getDescription().substring(0, 200) + "...");
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TripFragment()).commit();
        }

        return super.onOptionsItemSelected(item);
    }

}