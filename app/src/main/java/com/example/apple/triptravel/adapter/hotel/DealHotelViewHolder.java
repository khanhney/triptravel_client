package com.example.apple.triptravel.adapter.hotel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;

public class DealHotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView img;
    public TextView txtTitle, txtPrice, txtDescription, txtDealLeft;

    private onItemClickListener listener;

    public DealHotelViewHolder(@NonNull View itemView) {
        super(itemView);
        img             = itemView.findViewById(R.id.img_deal_rl);
        txtTitle        = itemView.findViewById(R.id.txt_deal_title_rl);
        txtPrice        = itemView.findViewById(R.id.txt_deal_price_rl);
        txtDescription  = itemView.findViewById(R.id.txt_deal_description_rl);
        txtDealLeft     = itemView.findViewById(R.id.txt_deal_dealleft_rl);

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
