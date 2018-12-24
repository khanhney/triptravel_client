package com.example.apple.triptravel.adapter.hotel;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.fragments.HotelDetailFragment;
import com.example.apple.triptravel.models.hotel.DataHotel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DealHotelViewHolder dealViewHolder, int i) {
        final DataHotel deal = list.get(i);

        Picasso.get().load(Utils.BASE_URL +  deal.getImage()).into(dealViewHolder.img);
        dealViewHolder.txtTitle.setText(deal.getTitle());
        dealViewHolder.txtPrice.setText(currencyFormat(deal.getPrice()) + " VNĐ");

        String description;
        if(deal.getDescription().length() > 30){
            description = deal.getDescription().substring(0, 40) + "...";
        }else {
            description = deal.getDescription();
        }

        dealViewHolder.txtDescription.setText(description);
        dealViewHolder.txtDealLeft.setText(Utils.convertDateToString(deal.getUpdateAt()));

        dealViewHolder.setListener(new onItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA_HOTEL", deal);
                HotelDetailFragment hotelDetailFragment = new HotelDetailFragment();
                hotelDetailFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, hotelDetailFragment, "hotel_detail_fragment")
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(Double.parseDouble(amount));
    }

}
