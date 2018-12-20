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
import com.example.apple.triptravel.models.Flights;
import com.example.apple.triptravel.models.hotel.DataHotel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListPopularDestinationsHotelAdapter extends RecyclerView.Adapter<ListPopularDestinationsHotelViewHolder> {

    Context context;
    List<DataHotel> list;

    public ListPopularDestinationsHotelAdapter(Context context, List<DataHotel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListPopularDestinationsHotelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ListPopularDestinationsHotelViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_card_view_recyclerview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListPopularDestinationsHotelViewHolder listPopularDestinationsViewHolder, int i) {
        final DataHotel flights = list.get(i);

        Picasso.get().load(Utils.BASE_URL + flights.getImage()).into(listPopularDestinationsViewHolder.img);
        listPopularDestinationsViewHolder.txt.setText(flights.getTitle().substring(0, 10) + "...");

        listPopularDestinationsViewHolder.setListener(new onItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA_HOTEL", flights);
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
}
