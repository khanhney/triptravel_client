package com.example.apple.triptravel.adapter.trip;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.fragments.HotelDetailFragment;
import com.example.apple.triptravel.fragments.TripDetailFragment;
import com.example.apple.triptravel.models.trip.DataTrip;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListTripAdapter extends RecyclerView.Adapter<ListTripViewHolder> {
    private Context context;
    private List<DataTrip> trips;

    public ListTripAdapter(Context context, List<DataTrip> trips) {
        this.context = context;
        this.trips = trips;
    }

    @NonNull
    @Override
    public ListTripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ListTripViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_card_view_recyclerview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListTripViewHolder listCardViewHolder, int i) {
        final DataTrip trip = trips.get(i);
        Picasso.get().load(Utils.BASE_URL + trip.getImage()).into(listCardViewHolder.img);

        if (trip.getTitle().length() > 25){
            listCardViewHolder.txt.setText(trip.getTitle().substring(0, 25) + "...");

        }else         listCardViewHolder.txt.setText(trip.getTitle());

        listCardViewHolder.setListener(new onItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA_TRIP", trip);
                TripDetailFragment tripDetailFragment = new TripDetailFragment();
                tripDetailFragment.setArguments(bundle);

                ((AppCompatActivity) context)
                        .getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, tripDetailFragment, "trip_fragment")
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
