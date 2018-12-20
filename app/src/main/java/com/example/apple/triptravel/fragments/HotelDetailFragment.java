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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.models.hotel.DataHotel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Random;

public class HotelDetailFragment extends Fragment {

    private TextView txtReview_Hotel_Detail_Page,
            txtTitleHotelDetail, txtPriceHotelDetail, txtDateTimeHotelDetail, txtDescriptionHotelDetail, txtStartHotelDetail;
    private ImageView imgHotelDetail;
    private RatingBar ratingBarHotelDetail;

    private String idHotel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_hotel_detail, container, false);

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
        txtReview_Hotel_Detail_Page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("ID_Hotel", idHotel);
                HotelDetailReviewRatingFragment hotelDetailReviewRatingFragment = new HotelDetailReviewRatingFragment();
                hotelDetailReviewRatingFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hotelDetailReviewRatingFragment).commit();
            }
        });
    }

    private void addControls(View view) {
        txtReview_Hotel_Detail_Page = view.findViewById(R.id.txtReview_Hotel_Detail_Page);
        txtTitleHotelDetail = view.findViewById(R.id.txtTitleHotelDetail);
        txtPriceHotelDetail = view.findViewById(R.id.txtPriceHotelDetail);
        txtDateTimeHotelDetail = view.findViewById(R.id.txtDateTimeHotelDetail);
        txtDescriptionHotelDetail = view.findViewById(R.id.txtDescriptionHotelDetail);
        imgHotelDetail = view.findViewById(R.id.imgHotelDetail);
        ratingBarHotelDetail = view.findViewById(R.id.ratingBarHotelDetail);
        txtStartHotelDetail = view.findViewById(R.id.txtStartHotelDetail);

        Bundle bundle = getArguments();
        if (bundle != null){
            DataHotel dataHotel = (DataHotel) bundle.getSerializable("DATA_HOTEL");

            idHotel = dataHotel.getId();

            txtTitleHotelDetail.setText(dataHotel.getTitle().substring(0, 15) + "...");
            txtPriceHotelDetail.setText(currencyFormat(dataHotel.getPrice()) + " VNĐ");
            txtDateTimeHotelDetail.setText(dataHotel.getUpdateAt());
            txtDescriptionHotelDetail.setText(dataHotel.getDescription());
            txtStartHotelDetail.setText(dataHotel.getReview() + "");
            Picasso.get().load(Utils.BASE_URL +  dataHotel.getImage()).into(imgHotelDetail);

            ratingBarHotelDetail.setRating(dataHotel.getReview());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HotelFragment()).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(Double.parseDouble(amount));
    }
}
