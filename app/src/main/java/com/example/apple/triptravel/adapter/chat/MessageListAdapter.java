package com.example.apple.triptravel.adapter.chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.triptravel.HomeActivity;
import com.example.apple.triptravel.LoginActivity;
import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.onItemClickListener;
import com.example.apple.triptravel.common.UserCommon;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.interfaces.GetHotelService;
import com.example.apple.triptravel.models.chat.Chat;
import com.example.apple.triptravel.models.login.DataUserLogin;
import com.example.apple.triptravel.models.rating.TrainRating;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListViewHolder> {

    Context context;
    List<Chat> list;

    GetHotelService service;

    public MessageListAdapter(Context context, List<Chat> list) {
        this.context = context;
        this.list = list;

        service = UserCommon.getHotelService();
    }

    @Override
    public MessageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_bot_sent, parent, false));
    }

    @Override
    public void onBindViewHolder(MessageListViewHolder holder, int position) {
        final Chat chat = list.get(position);
//        final String answer       = list.get(position - 1).getMessage();

        if (chat.getId().equals("0")) {
            holder.lnMessage.setPadding(15, 0, 60, 0);
            holder.lnMessage.setGravity(Gravity.LEFT);
            holder.txtMessage.setBackground(context.getResources().getDrawable(R.drawable.custom_btn_find_flight1));
            holder.txtMessage.setTextColor(context.getResources().getColor(R.color.colorDark));


        } else {
            holder.lnMessage.setPadding(60, 0, 15, 0);
            holder.lnMessage.setGravity(Gravity.RIGHT);
            holder.txtMessage.setBackground(context.getResources().getDrawable(R.drawable.custom_btn_find_flight2));
            holder.txtMessage.setTextColor(context.getResources().getColor(R.color.colorWhite));

        }

        if (position % 2 != 0) {
            holder.setListener(new onItemClickListener() {
                @Override
                public void onCLick(View v, int position, boolean isLongClick) {

                    displayPopupWindow(v, chat.getMessage(), list.get(position - 1).getMessage());
                }
            });
        } else {
            holder.setListener(new onItemClickListener() {
                @Override
                public void onCLick(View v, int position, boolean isLongClick) {
                }
            });
        }
        holder.txtMessage.setText(chat.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void displayPopupWindow(View v, String message, String question) {
        PopupWindow popup = new PopupWindow(context);
        View layout = ((AppCompatActivity) context).getLayoutInflater().inflate(R.layout.item_popup_rating_for_chat_bot, null);

        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(v);

        final Animation anim_zoom_in, anim_zoom_out;
        anim_zoom_in = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
        anim_zoom_out = AnimationUtils.loadAnimation(context, R.anim.zoom_out);


        TextView txtQuestion, txtAnswer;
        txtQuestion                     = layout.findViewById(R.id.txtQuestion);
        txtAnswer                       = layout.findViewById(R.id.txtAnswer);
        final ImageView imgLove         = layout.findViewById(R.id.imgLove);
        final ImageView imgHaha         = layout.findViewById(R.id.imgHaha);
        final ImageView imgWow          = layout.findViewById(R.id.imgWow);
        final ImageView imgSad          = layout.findViewById(R.id.imgSad);
        final ImageView imgAngry        = layout.findViewById(R.id.imgAngry);

        LinearLayout lnIconLove         = layout.findViewById(R.id.lnIconLove);
        LinearLayout lnIconHaha         = layout.findViewById(R.id.lnIconHaha);
        LinearLayout lnIconWow          = layout.findViewById(R.id.lnIconWow);
        LinearLayout lnIconSad          = layout.findViewById(R.id.lnIconSad);
        LinearLayout lnIconAngry        = layout.findViewById(R.id.lnIconAngry);

        SharedPreferences sharedPreferences = context.getSharedPreferences("INFO_USER_AND_TOKEN",
                MODE_PRIVATE);

        String token = sharedPreferences.getString("Token", "");

        if (sharedPreferences.contains("Token") && !TextUtils.isEmpty(token)){
            setClickListener(imgLove,   message, question, 5, token, popup);
            setClickListener(imgHaha,   message, question, 4, token, popup);
            setClickListener(imgWow,    message, question, 3, token, popup);
            setClickListener(imgSad,    message, question, 2, token, popup);
            setClickListener(imgAngry,  message, question, 1, token, popup);

        }

        setTouchListener(lnIconLove,    anim_zoom_in, anim_zoom_out);
        setTouchListener(lnIconHaha,    anim_zoom_in, anim_zoom_out);
        setTouchListener(lnIconWow,     anim_zoom_in, anim_zoom_out);
        setTouchListener(lnIconSad,     anim_zoom_in, anim_zoom_out);
        setTouchListener(lnIconAngry,   anim_zoom_in, anim_zoom_out);

        txtQuestion.setText(question);
        txtAnswer.setText(message);
    }

    private void setClickListener(final ImageView img, final String message, final String question, final int point, final String token, final PopupWindow popup) {
        final Animation anim = AnimationUtils.loadAnimation(context, R.anim.zoom_in_and_fade_in);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.postTrainRating(question, message, point, token).enqueue(new Callback<TrainRating>() {
                    @Override
                    public void onResponse(Call<TrainRating> call, Response<TrainRating> response) {
                        if (response.isSuccessful()){
                            if (!response.body().getError()){
                                Toast.makeText(context, "Cảm ơn bạn đã đánh giá!", Toast.LENGTH_SHORT).show();
                                popup.dismiss();
                                img.startAnimation(anim);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TrainRating> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void setTouchListener(final LinearLayout ln, final Animation anim_zoom_in, final Animation anim_zoom_out) {
        ln.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        ln.startAnimation(anim_zoom_in);
                        break;
                    case MotionEvent.ACTION_UP:
                        ln.startAnimation(anim_zoom_out);
                        break;
                }
                return true;
            }
        });
    }
}