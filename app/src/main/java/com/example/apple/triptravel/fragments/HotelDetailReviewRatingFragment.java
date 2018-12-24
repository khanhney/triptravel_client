package com.example.apple.triptravel.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.hotel.ListReviewRatingAdapter;
import com.example.apple.triptravel.common.UserCommon;
import com.example.apple.triptravel.interfaces.GetHotelService;
import com.example.apple.triptravel.models.ReviewRating;
import com.example.apple.triptravel.models.hotel.DataHotelRating;
import com.example.apple.triptravel.models.hotel.HotelRating;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDetailReviewRatingFragment extends Fragment {

    private RecyclerView recyclerViewReviewRating;
    private RecyclerView.LayoutManager managerReviewRating;
    private ListReviewRatingAdapter adapterReviewRating;
    private List<DataHotelRating> listReviewRating;

    private LinearLayout linearLayout;
    private String ID_HOTEL;

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

        View view = inflater.inflate(R.layout.fragment_hotel_detail_review, container, false);

        service = UserCommon.getHotelService();

        Toolbar toolbar = view.findViewById(R.id.toolbar_HotelDetail);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        linearLayout = view.findViewById(R.id.lnLayoutWirte);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("HOTEL_ID", ID_HOTEL);
                HotelDetailReviewRatingWriteFragment writeFragment = new HotelDetailReviewRatingWriteFragment();
                writeFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, writeFragment, "hotel_detail_review_rating_write_fragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        getIDHotel();

        init(view);

        return view;
    }

    private void getIDHotel() {
        ID_HOTEL = getArguments().getString("ID_Hotel");
    }

    private void init(View view) {
        recyclerViewReviewRating = view.findViewById(R.id.rlvListReviewRating);
        recyclerViewReviewRating.setHasFixedSize(true);
        managerReviewRating = new LinearLayoutManager(getContext());
        recyclerViewReviewRating.setLayoutManager(managerReviewRating);
        listReviewRating = new ArrayList<>();

        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getContext()).build();
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        service.getListHotelRating(ID_HOTEL).enqueue(new Callback<HotelRating>() {
            @Override
            public void onResponse(Call<HotelRating> call, Response<HotelRating> response) {
                if (response.isSuccessful()){
                    if (response.body().getError()){
                        Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 1000);
                    }
                    listReviewRating.addAll(response.body().getData());

                    adapterReviewRating.notifyDataSetChanged();
                }
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<HotelRating> call, Throwable t) {
                Log.e("ERROR_HOTEL_RATING", t.getMessage());
                dialog.dismiss();
            }
        });


        adapterReviewRating = new ListReviewRatingAdapter(getContext(), listReviewRating);
        recyclerViewReviewRating.setAdapter(adapterReviewRating);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HotelDetailFragment()).commit();
            getActivity().getSupportFragmentManager().popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }


}
