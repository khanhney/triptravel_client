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
import com.example.apple.triptravel.models.Deal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DealAdapter extends RecyclerView.Adapter<DealViewHolder> {
    Context context;
    List<Deal> list;

    public DealAdapter(Context context, List<Deal> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DealViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_card_deal_rl, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder dealViewHolder, int i) {
        final Deal deal = list.get(i);

        Picasso.get().load(deal.getImgUrl()).into(dealViewHolder.img);
        dealViewHolder.txtTitle.setText(deal.getTitle());
        dealViewHolder.txtPrice.setText(deal.getPrice());

        String description = null;
        if(deal.getDescription().length() > 30){
            description = deal.getDescription().substring(0, 30) + "...";
        }else {
            description = deal.getDescription();
        }

        dealViewHolder.txtDescription.setText(description);
        dealViewHolder.txtDealLeft.setText(deal.getDealLeft());

        dealViewHolder.setListener(new onItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                Toast.makeText(context, "Ban vua click vao: " + deal.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
