package com.example.apple.triptravel.adapter.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.fragments.HotelDetailFragment;
import com.example.apple.triptravel.models.search.DataSearch;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ListSearchAdapter extends ArrayAdapter<DataSearch> {
    Context context;
    List<DataSearch> list;

    public ListSearchAdapter(Context context, List<DataSearch> list) {
        super(context, 0);
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_card_deal_rl, parent, false);
        }

        ImageView img;
        TextView txtTitle, txtPrice, txtDescription, txtDealLeft;

        img             = view.findViewById(R.id.img_deal_rl);
        txtTitle        = view.findViewById(R.id.txt_deal_title_rl);
        txtPrice        = view.findViewById(R.id.txt_deal_price_rl);
        txtDescription  = view.findViewById(R.id.txt_deal_description_rl);
        txtDealLeft     = view.findViewById(R.id.txt_deal_dealleft_rl);

        final DataSearch deal = list.get(position);

        Picasso.get().load(Utils.BASE_URL +  deal.getImage()).into(img);
        txtTitle.setText(deal.getTitle().substring(0, 10) + "...");
        txtPrice.setText(currencyFormat(deal.getPrice()) + " VNĐ");

        String description = null;
        if(deal.getDescription().length() > 30){
            description = deal.getDescription().substring(0, 40) + "...";
        }else {
            description = deal.getDescription();
        }

        txtDescription.setText(description);
        txtDealLeft.setText(deal.getUpdateAt() + "");


        return view;
    }

    @Override
    public Filter getFilter() {
        return dataFilter;
    }

    private Filter dataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<DataSearch> searchList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){

            }else {
                String filterP = constraint.toString().toLowerCase().toLowerCase();

                for (DataSearch dataSearch : searchList){
                    if (dataSearch.getTitle().toLowerCase().contains(filterP)){
                        searchList.add(dataSearch);
                    }
                }
            }

            results.values = searchList;
            results.count = searchList.size();

            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((DataSearch) resultValue).getTitle();
        }
    };

//
//    @Override
//    public void onBindViewHolder(@NonNull ListSearchlViewHolder dealViewHolder, int i) {
//        final DataSearch deal = list.get(i);
//
//        Picasso.get().load(Utils.BASE_URL +  deal.getImage()).into(dealViewHolder.img);
//        dealViewHolder.txtTitle.setText(deal.getTitle().substring(0, 10) + "...");
//        dealViewHolder.txtPrice.setText(currencyFormat(deal.getPrice()) + " VNĐ");
//
//        String description = null;
//        if(deal.getDescription().length() > 30){
//            description = deal.getDescription().substring(0, 40) + "...";
//        }else {
//            description = deal.getDescription();
//        }
//
//        dealViewHolder.txtDescription.setText(description);
//        dealViewHolder.txtDealLeft.setText(deal.getUpdateAt() + "");
//
//        dealViewHolder.setListener(new onItemClickListener() {
//            @Override
//            public void onCLick(View v, int position, boolean isLongClick) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("DATA_HOTEL", deal);
//                HotelDetailFragment hotelDetailFragment = new HotelDetailFragment();
//                hotelDetailFragment.setArguments(bundle);
//
//                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hotelDetailFragment).commit();
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(Double.parseDouble(amount));
    }
}
