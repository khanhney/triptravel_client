package com.example.apple.triptravel.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.flights.ListDateTimeOfFlightAdapter;
import com.example.apple.triptravel.models.AirTicket;

import java.util.ArrayList;
import java.util.List;

public class AirPlanFindCreateViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListDateTimeOfFlightAdapter adapter;
    private List<AirTicket> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_airplan_find_create_view, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_FlightFind);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        init(view);

        return  view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.rlvShowDateTime);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        list.add(new AirTicket.Builder()
            .setPlaceFrom("Ho Chi Minh")
            .setPlaceTo("Singapore")
            .setDateTimeFrom("8:00")
            .setDateTimeTo("12:30")
            .setBrand("VN eline")
            .setDateTimeLastAgo("1 hr 30 min")
            .setPrice("$300")
            .build());

        list.add(new AirTicket.Builder()
                .setPlaceFrom("Ha Noi")
                .setPlaceTo("USA")
                .setDateTimeFrom("8:00")
                .setDateTimeTo("12:30")
                .setBrand("Viet jet")
                .setDateTimeLastAgo("1 hr 30 min")
                .setPrice("$200")
                .build());

        list.add(new AirTicket.Builder()
                .setPlaceFrom("Tay Ban Nha")
                .setPlaceTo("Uc")
                .setDateTimeFrom("8:00")
                .setDateTimeTo("12:30")
                .setBrand("VN eline")
                .setDateTimeLastAgo("1 hr 30 min")
                .setPrice("$120")
                .build());

        list.add(new AirTicket.Builder()
                .setPlaceFrom("Nga")
                .setPlaceTo("Singapore")
                .setDateTimeFrom("8:00")
                .setDateTimeTo("12:30")
                .setBrand("VN eline")
                .setDateTimeLastAgo("1 hr 30 min")
                .setPrice("$200")
                .build());

        adapter = new ListDateTimeOfFlightAdapter(getActivity(), list);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            getActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, new AirplanFindFragment(), "trip_detail_fin_create_fragment")
//                    .addToBackStack(null)
//                    .commit();
            getActivity().getSupportFragmentManager().popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }
}
