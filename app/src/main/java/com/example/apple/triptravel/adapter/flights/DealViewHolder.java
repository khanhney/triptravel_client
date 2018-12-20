package com.example.apple.triptravel.adapter.flights;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;

public class DealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView img;
    public TextView txtTitle, txtPrice, txtDescription, txtDealLeft;

    private onItemClickListener listener;

    public DealViewHolder(@NonNull View itemView) {
        super(itemView);
        img             = itemView.findViewById(R.id.img_deal_rl);
        txtTitle        = itemView.findViewById(R.id.txt_deal_title_rl);
        txtPrice        = itemView.findViewById(R.id.txt_deal_price_rl);
        txtDescription  = itemView.findViewById(R.id.txt_deal_description_rl);
        txtDealLeft     = itemView.findViewById(R.id.txt_deal_dealleft_rl);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onCLick(v, getAdapterPosition(), false);
    }
}
