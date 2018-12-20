package com.example.apple.triptravel.adapter.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.fragments.HotelDetailFragment;
import com.example.apple.triptravel.fragments.TripDetailFragment;
import com.example.apple.triptravel.models.Deal;
import com.example.apple.triptravel.models.hotel.DataHotel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class DealHotelAdapter extends RecyclerView.Adapter<DealHotelViewHolder> {
    Context context;
    List<DataHotel> list;

    public DealHotelAdapter(Context context, List<DataHotel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DealHotelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DealHotelViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_card_deal_rl, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DealHotelViewHolder dealViewHolder, int i) {
        final DataHotel deal = list.get(i);

        Picasso.get().load(Utils.BASE_URL +  deal.getImage()).into(dealViewHolder.img);
        dealViewHolder.txtTitle.setText(deal.getTitle().substring(0, 10) + "...");
        dealViewHolder.txtPrice.setText(currencyFormat(deal.getPrice()) + " VNĐ");

        String description = null;
        if(deal.getDescription().length() > 30){
            description = deal.getDescription().substring(0, 40) + "...";
        }else {
            description = deal.getDescription();
        }

        dealViewHolder.txtDescription.setText(description);
        dealViewHolder.txtDealLeft.setText(deal.getUpdateAt() + "");

        dealViewHolder.setListener(new onItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA_HOTEL", deal);
                HotelDetailFragment hotelDetailFragment = new HotelDetailFragment();
                hotelDetailFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hotelDetailFragment).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(Double.parseDouble(amount));
    }
}
