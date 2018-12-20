package com.example.apple.triptravel.adapter.flights;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;
import com.example.apple.triptravel.models.Flights;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListPopularDestinationsAdapter extends RecyclerView.Adapter<ListPopularDestinationsViewHolder> {

    Context context;
    List<Flights> list;

    public ListPopularDestinationsAdapter(Context context, List<Flights> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListPopularDestinationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ListPopularDestinationsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_card_view_recyclerview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListPopularDestinationsViewHolder listPopularDestinationsViewHolder, int i) {
        final Flights flights = list.get(i);

        Picasso.get().load(flights.getImgURL()).into(listPopularDestinationsViewHolder.img);
        listPopularDestinationsViewHolder.txt.setText(flights.getTitle());

        listPopularDestinationsViewHolder.setListener(new onItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                Toast.makeText(context, "Ban vua click vao: " + flights.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
