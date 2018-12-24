package com.example.apple.triptravel.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.chat.MessageListAdapter;
import com.example.apple.triptravel.common.UserCommon;
import com.example.apple.triptravel.common.Utils;
import com.example.apple.triptravel.interfaces.GetChatService;
import com.example.apple.triptravel.models.chat.Chat;
import com.example.apple.triptravel.models.chat.ChatBot;
import com.fasterxml.jackson.databind.JsonNode;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Member;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<Chat> messageList;

    private EditText editSent;
    private ImageButton btnSent;


    private GetChatService service;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chat, container, false);

        service = UserCommon.getChatService();

//        init component
        editSent = view.findViewById(R.id.editSent);
        btnSent = view.findViewById(R.id.btnSent);


        messageList = new ArrayList<>();

        mMessageRecycler = view.findViewById(R.id.rlvChatbot);
        mMessageAdapter = new MessageListAdapter(getContext(), messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        mMessageRecycler.setAdapter(mMessageAdapter);

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String message = editSent.getText().toString();
                if (!TextUtils.isEmpty(message)){
                    Utils.userCurrent = "1";

                    messageList.add(new Chat("1", message));
                    mMessageAdapter.notifyDataSetChanged();

                    editSent.setText("");

                    service.getListChatBot(message).enqueue(new Callback<ChatBot>() {
                        @Override
                        public void onResponse(Call<ChatBot> call, Response<ChatBot> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(getContext(), "Bot: " + response.body().getAwnser(), Toast.LENGTH_SHORT).show();
                                messageList.add(new Chat("0", response.body().getAwnser()));
                                mMessageAdapter.notifyDataSetChanged();
                            }else Toast.makeText(getActivity(), "Lỗi server", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ChatBot> call, Throwable t) {

                        }
                    });
                }
            }
        });

        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
