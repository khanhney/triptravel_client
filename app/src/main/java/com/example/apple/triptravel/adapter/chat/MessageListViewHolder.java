package com.example.apple.triptravel.adapter.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;

public class MessageListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public RelativeLayout lnMessage;
    public TextView txtMessage;
    private onItemClickListener listener;

    public MessageListViewHolder(View itemView) {
        super(itemView);

        txtMessage  = itemView.findViewById(R.id.message_body);
        lnMessage   = itemView.findViewById(R.id.lnMessage);
        itemView.setOnClickListener(this);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onCLick(view, getAdapterPosition(), false);
    }
}
