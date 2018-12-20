package com.example.apple.triptravel.adapter.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.apple.triptravel.R;

public class MessageListSentViewHolder extends RecyclerView.ViewHolder {

    public TextView txtSent;

    public MessageListSentViewHolder(@NonNull View itemView) {
        super(itemView);

        txtSent = itemView.findViewById(R.id.message_body);
    }
}