package com.example.apple.triptravel.adapter.flights;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;
import com.example.apple.triptravel.fragments.AirPlanFindCheckOut;
import com.example.apple.triptravel.models.AirTicket;

import java.util.List;

public class ListDateTimeOfFlightAdapter extends RecyclerView.Adapter<ListDateTimeOfFlightViewHoder>{

    Context context;
    List<AirTicket> list;

    public ListDateTimeOfFlightAdapter(Context context, List<AirTicket> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListDateTimeOfFlightViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ListDateTimeOfFlightViewHoder(LayoutInflater.from(context).inflate(R.layout.item_list_dat_time, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListDateTimeOfFlightViewHoder listDateTimeOfFlightViewHoder, int i) {
        final AirTicket airTicket = list.get(i);
        listDateTimeOfFlightViewHoder.txtPlaceFrom_Flight.setText(airTicket.getPlaceFrom());
        listDateTimeOfFlightViewHoder.txtPlaceTo_Flight.setText(airTicket.getPlaceTo());
        listDateTimeOfFlightViewHoder.txtDateTimeFrom_Flight.setText(airTicket.getDateTimeFrom());
        listDateTimeOfFlightViewHoder.txtDateTimeTo_Flight.setText(airTicket.getDateTimeTo());
        listDateTimeOfFlightViewHoder.txtBrand_Flight.setText(airTicket.getBrand());
        listDateTimeOfFlightViewHoder.txtDateTime_Flight.setText(airTicket.getDateTimeLastAgo());
        listDateTimeOfFlightViewHoder.txtPrice_Flight.setText(airTicket.getPrice());

        listDateTimeOfFlightViewHoder.setListener(new onItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                Toast.makeText(context, "Ban vua click vao:" + airTicket.getBrand(), Toast.LENGTH_SHORT).show();
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AirPlanFindCheckOut()).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
