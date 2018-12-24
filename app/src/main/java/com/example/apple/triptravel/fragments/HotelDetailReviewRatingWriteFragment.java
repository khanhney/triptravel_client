package com.example.apple.triptravel.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.common.UserCommon;
import com.example.apple.triptravel.interfaces.GetHotelService;
import com.example.apple.triptravel.models.hotel.DataRatingWrite;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDetailReviewRatingWriteFragment extends Fragment {

    private RatingBar ratingBar;
    private EditText editTitleRatingWrite, editRatingReview;
    private Button btnRatingReview;

    private GetHotelService service;
    private int rating;
    private String HOTEL_ID;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_hotel_detail_review_write, container, false);

        service = UserCommon.getHotelService();

        Toolbar toolbar = view.findViewById(R.id.toolbar_HotelDetail);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        addControls(view);
        addEvents(view);

        return view;
    }

    private void addEvents(View view) {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//                Toast.makeText(getActivity(), "rating: " + v, Toast.LENGTH_SHORT).show();
//                rating = Integer.parseInt(v + "");
                ratingBar.setRating(v);
            }
        });


        btnRatingReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title        = editTitleRatingWrite.getText().toString();
                String message      = editRatingReview.getText().toString();
                rating              = (int) ratingBar.getRating();

                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(message)){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ nội dung!", Toast.LENGTH_SHORT).show();
                }else {
                    String token = null;
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("INFO_USER_AND_TOKEN", Context.MODE_PRIVATE);
                    token = sharedPreferences.getString("Token", "");

                    final AlertDialog dialog = new SpotsDialog.Builder().setContext(getContext()).build();
                    dialog.setMessage("Loading...");
                    dialog.setCancelable(false);
                    dialog.show();

                    service.postRatingWrite(token, title, message, rating, HOTEL_ID).enqueue(new Callback<DataRatingWrite>() {
                        @Override
                        public void onResponse(Call<DataRatingWrite> call, Response<DataRatingWrite> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(getContext(), "Bạn đã đánh giá thành công!", Toast.LENGTH_SHORT).show();
                                getActivity().getSupportFragmentManager().popBackStack();
                            }
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<DataRatingWrite> call, Throwable t) {
                            Log.e("ERROR_REVIEW", t.getMessage());
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    private void addControls(View view) {
        ratingBar               = view.findViewById(R.id.ratingBarRatingWrite);
        editTitleRatingWrite    = view.findViewById(R.id.editTitleRatingWrite);
        editRatingReview        = view.findViewById(R.id.editRatingReview);
        btnRatingReview         = view.findViewById(R.id.btnRatingReview);

        HOTEL_ID = getArguments().getString("HOTEL_ID");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            getActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, new HotelDetailReviewRatingFragment())
//                    .commit();

            getActivity().getSupportFragmentManager().popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }
}
