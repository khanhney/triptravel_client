package com.example.apple.triptravel.adapter.hotel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;

public class ListDateTimeOfHotelViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView
            txtPlaceFrom_Flight,
            txtPlaceTo_Flight,
            txtDateTimeFrom_Flight,
            txtDateTimeTo_Flight,
            txtBrand_Flight,
            txtDateTime_Flight,
            txtPrice_Flight;

    private onItemClickListener listener;

    public ListDateTimeOfHotelViewHoder(@NonNull View itemView) {
        super(itemView);

        txtPlaceFrom_Flight         = itemView.findViewById(R.id.txtPlaceFrom_Flight);
        txtPlaceTo_Flight           = itemView.findViewById(R.id.txtPlaceTo_Flight);
        txtDateTimeFrom_Flight      = itemView.findViewById(R.id.txtDateTimeFrom_Flight);
        txtDateTimeTo_Flight        = itemView.findViewById(R.id.txtDateTimeTo_Flight);
        txtBrand_Flight             = itemView.findViewById(R.id.txtBrand_Flight);
        txtDateTime_Flight          = itemView.findViewById(R.id.txtDateTime_Flight);
        txtPrice_Flight             = itemView.findViewById(R.id.txtPrice_Fight);

        itemView.setOnClickListener(this);
    }

    public ListDateTimeOfHotelViewHoder setListener(onItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void onClick(View v) {
        listener.onCLick(v, getAdapterPosition(), false);
    }
}
