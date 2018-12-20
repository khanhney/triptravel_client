package com.example.apple.triptravel.adapter.hotel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;

public class ListReviewRatingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtTitleRating, txtAuthorRating, txtDateRating, txtDescriptionRating;
    public RatingBar ratingBar;

    private onItemClickListener listener;

    public ListReviewRatingViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitleRating = itemView.findViewById(R.id.txtTitleRating);
        txtAuthorRating = itemView.findViewById(R.id.txtAuthorRating);
        txtDateRating = itemView.findViewById(R.id.txtDateRating);
        txtDescriptionRating = itemView.findViewById(R.id.txtDescriptionRating);
        ratingBar = itemView.findViewById(R.id.ratingBarOfAuthor);

        itemView.setOnClickListener(this);
    }

    public ListReviewRatingViewHolder setListener(onItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void onClick(View v) {
        listener.onCLick(v, getAdapterPosition(), false);
    }
}
