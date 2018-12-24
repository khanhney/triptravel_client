package com.example.apple.triptravel.adapter.search;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.fragments.HotelDetailFragment;
import com.example.apple.triptravel.models.hotel.DataHotel;
import com.example.apple.triptravel.models.search.DataSearch;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ListSearchAdapter extends ArrayAdapter<DataSearch> {
    private Context context;
    private List<DataSearch> list;

    public ListSearchAdapter(Context context, List<DataSearch> list) {
        super(context, 0);
        this.context = context;
        this.list = list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
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
        txtTitle.setText(deal.getTitle());
        txtPrice.setText(currencyFormat(deal.getPrice()) + " VNĐ");

        String description;
        if(deal.getDescription().length() > 30){
            description = deal.getDescription().substring(0, 40) + "...";
        }else {
            description = deal.getDescription();
        }

        txtDescription.setText(description);
        txtDealLeft.setText(Utils.convertDateToString(deal.getUpdateAt()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHotel dataHotel = new DataHotel(
                        deal.getStatus(),
                        deal.getId(),
                        deal.getTitle(),
                        deal.getDescription(),
                        deal.getPrice(),
                        deal.getV(),
                        deal.getImage(),
                        deal.getReview(),
                        deal.getUpdateAt());

                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA_HOTEL", dataHotel);
                HotelDetailFragment hotelDetailFragment = new HotelDetailFragment();
                hotelDetailFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, hotelDetailFragment, "hotel_detail_fragment")
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return dataFilter;
    }

    private Filter dataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            results.values = list;
            results.count  = list.size();

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
            Log.d("___", ((DataSearch) resultValue).getTitle());
            return ((DataSearch) resultValue).getTitle();
        }
    };

    private static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(Double.parseDouble(amount));
    }
}
