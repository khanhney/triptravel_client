package com.example.apple.triptravel.adapter.trip;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;

public class ListTripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView img;
    public TextView txt;

    onItemClickListener listener;

    public ListTripViewHolder(@NonNull View itemView) {
        super(itemView);

        img = itemView.findViewById(R.id.img_list_card_recyclerview);
        txt = itemView.findViewById(R.id.txt_list_card_recyclerview);
        itemView.setOnClickListener(this);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onCLick(v, getAdapterPosition(), false);
    }
}
