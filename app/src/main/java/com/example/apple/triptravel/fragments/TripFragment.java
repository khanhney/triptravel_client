package com.example.apple.triptravel.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.trip.ListTripAdapter;
import com.example.apple.triptravel.common.UserCommon;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.interfaces.GetTripService;
import com.example.apple.triptravel.models.trip.DataTrip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListTripAdapter adapter1, adapter2;
    private List<DataTrip> tripsRelaxation, tripAdvenfuture;
    private RecyclerView.LayoutManager manager1, manager2;

    private RecyclerView rlvListAdventure;

    private ImageView imageView5;
    
    private GetTripService service;

    private TextView txtTitleTrip, txtDescription;
    private DataTrip dataTrip;

    AlertDialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_trip, container, false);
        
        service = UserCommon.getTripService();

        addControls(view);

        tripsRelaxation = new ArrayList<>();
        tripAdvenfuture = new ArrayList<>();

        recyclerView = view.findViewById(R.id.rlvListRelaxation);
        recyclerView.setHasFixedSize(true);
        manager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager1);

        adapter1 = new ListTripAdapter(getActivity(), tripsRelaxation);
        recyclerView.setAdapter(adapter1);

        //custom advenfuture

        rlvListAdventure = view.findViewById(R.id.rlvListAdventure);
        rlvListAdventure.setHasFixedSize(true);
        manager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rlvListAdventure.setLayoutManager(manager2);

        adapter2 = new ListTripAdapter(getActivity(), tripAdvenfuture);
        rlvListAdventure.setAdapter(adapter2);



        addEvents(view);

        return view;
    }

    private void addEvents(View view) {
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TripDetailFragment tripDetailFragment = new TripDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA_TRIP", dataTrip);
                tripDetailFragment.setArguments(bundle);


                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, tripDetailFragment, "trip_detail_fragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void addControls(View view) {
        imageView5 = view.findViewById(R.id.imageView5);
        txtTitleTrip = view.findViewById(R.id.txtTitle);
        txtDescription = view.findViewById(R.id.txtDescription);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            dialog = new SpotsDialog.Builder().setContext(getContext()).build();
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();

            service.getListTrip().enqueue(new Callback<com.example.apple.triptravel.models.trip.Trip>() {
                @Override
                public void onResponse(@NonNull Call<com.example.apple.triptravel.models.trip.Trip> call,
                                       @NonNull final Response<com.example.apple.triptravel.models.trip.Trip> response) {
                    if (response.isSuccessful()){
                        if (response.body().getError()){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }, 1000);
                        }else {
                            Picasso.get().load(Utils.BASE_URL + response.body().getData().get(0).getImage()).into(imageView5);
                            txtTitleTrip.setText(response.body().getData().get(0).getTitle().substring(0, 20) + "...");
                            txtDescription.setText(response.body().getData().get(0).getDescription().substring(0, 20) + "...");
                            dataTrip = response.body().getData().get(0);


                            for (int i = 1; i < response.body().getData().size(); i++){
                                if (i % 2 != 0){
                                    tripsRelaxation.add(response.body().getData().get(i));
                                }else {
                                    tripAdvenfuture.add(response.body().getData().get(i));
                                }
                            }

                            adapter1.notifyDataSetChanged();
                            adapter2.notifyDataSetChanged();

                        }
                    }
                    dialog.dismiss();
                }

                @Override
                public void onFailure(@NonNull Call<com.example.apple.triptravel.models.trip.Trip> call, @NonNull Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Lỗi server", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Log.e("Error__", e.getMessage());
        }
    }
}
