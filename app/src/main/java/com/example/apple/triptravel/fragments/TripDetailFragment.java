package com.example.apple.triptravel.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.hotel.ListPopularDestinationsHotelAdapter;
import com.example.apple.triptravel.common.UserCommon;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.interfaces.GetHotelService;
import com.example.apple.triptravel.models.hotel.DataHotel;
import com.example.apple.triptravel.models.hotel.Hotel;
import com.example.apple.triptravel.models.trip.DataTrip;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TripDetailFragment extends Fragment implements OnMapReadyCallback{

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

    private RecyclerView rlNearYou;
    private RecyclerView.LayoutManager layoutManager;
    private ListPopularDestinationsHotelAdapter adapter;
    private List<DataHotel> list;


    //Map view
    private MapView mapTripDetail;
    GoogleMap map;

    private GetHotelService service;


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

        service = UserCommon.getHotelService();

        Toolbar toolbar = view.findViewById(R.id.toolbar_TripDetail);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initDataHotel(view);

        addControls(view);
        addEvents();

        return view;
    }

    private void initDataHotel(View view) {
        rlNearYou = view.findViewById(R.id.rlvListTripHotel);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlNearYou.setLayoutManager(layoutManager);
        rlNearYou.setHasFixedSize(true);

        //popular custom
        list = new ArrayList<>();

        adapter = new ListPopularDestinationsHotelAdapter(getContext(), list);
        rlNearYou.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getContext()).build();
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        service.getListHotel().enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(@NonNull Call<Hotel> call, @NonNull Response<Hotel> response) {
                if (response.isSuccessful()){
                    if (response.body().getError()){
                        Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }else {
                        list.addAll(response.body().getData());

                        adapter.notifyDataSetChanged();

                    }
                }

                dialog.dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<Hotel> call, @NonNull Throwable t) {
                dialog.dismiss();
            }
        });
    }

    private void addEvents() {
        btnBookingTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, new AirplanFindFragment(), "trip_detail_find_fragment")
                        .addToBackStack(null)
                        .commit();
                
//                getActivity().getSupportFragmentManager().popBackStack("trip_fragment", 0);
//                Toast.makeText(getActivity(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addControls(View view) {
        mapTripDetail = view.findViewById(R.id.mapTripDetail);
        if (mapTripDetail != null){
            mapTripDetail.onCreate(null);
            mapTripDetail.onResume();
            mapTripDetail.getMapAsync(this);
        }

        btnBookingTrip = view.findViewById(R.id.btnBookingTrip);

        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);

        Picasso.get().load("https://s25743.pcdn.co/wp-content/uploads/2015/10/wanderers-favorite-destination-2-1024x727.jpg").into(img1);
        Picasso.get().load("https://s25743.pcdn.co/wp-content/uploads/2015/10/wanderers-favorite-destination-5.jpg").into(img2);
        Picasso.get().load("https://s25743.pcdn.co/wp-content/uploads/2015/10/wanderers-favorite-travel-destination-4.jpg").into(img3);
        Picasso.get().load("https://s25743.pcdn.co/wp-content/uploads/2015/10/wanderers-favorite-travel-destination-6.jpg").into(img4);


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
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TripFragment()).commit();
            getActivity().getSupportFragmentManager().popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());

        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        double lat = 10.8441802;
        double lng = 106.7795984;

        map.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .snippet(txtTitleTripDetail.getText().toString())
                .title(txtTitleTripDetail.getText().toString()));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(lat, lng))
                .bearing(15)
                .zoom(15)
                .build();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}