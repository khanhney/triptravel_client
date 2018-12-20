package com.example.apple.triptravel.adapter.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.apple.triptravel.R;

public class MessageListReceivedViewHolder extends RecyclerView.ViewHolder {
    public TextView txtReceived;

    public MessageListReceivedViewHolder(@NonNull View itemView) {
        super(itemView);

        txtReceived = itemView.findViewById(R.id.message_body);
    }
}
