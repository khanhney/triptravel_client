package com.example.apple.triptravel.adapter.hotel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;
import com.example.apple.triptravel.models.ReviewRating;
import com.example.apple.triptravel.models.hotel.DataHotelRating;

import java.util.List;

public class ListReviewRatingAdapter extends RecyclerView.Adapter<ListReviewRatingViewHolder> {

    Context context;
    List<DataHotelRating> list;

    public ListReviewRatingAdapter(Context context, List<DataHotelRating> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListReviewRatingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ListReviewRatingViewHolder(LayoutInflater.from(context).inflate(R.layout.item_review_rating, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListReviewRatingViewHolder listReviewRatingViewHolder, int i) {
        final DataHotelRating rating = list.get(i);
        listReviewRatingViewHolder.txtTitleRating.setText(rating.getTitle());
        listReviewRatingViewHolder.txtAuthorRating.setText(rating.getUser().getUsername());
        listReviewRatingViewHolder.txtDateRating.setText(rating.getUpdateAt());
        listReviewRatingViewHolder.txtDescriptionRating.setText(rating.getComment());
        listReviewRatingViewHolder.ratingBar.setRating((float) rating.getStar());

        listReviewRatingViewHolder.setListener(new onItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                Toast.makeText(context, "Author: " + rating.getUser().getUsername(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
