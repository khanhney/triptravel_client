package com.example.apple.triptravel.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.apple.triptravel.R;

public class FragmentBottomSheet extends BottomSheetDialogFragment {

    private ImageButton btnClick1, btnClick2, btnClick3, btnClick4;
    private TextView txtChange1, txtChange2;

    private TextView txtDone_Flights, txtCancel_Flights;


    private static int number1 = 1,  number2 = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.dialog_bottom_sheet, container, false);
        txtDone_Flights     = view.findViewById(R.id.txtDone_Flights);
        txtCancel_Flights   = view.findViewById(R.id.txtCancel_Flights);

        txtCancel_Flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        txtDone_Flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new AirPlanFindCreateViewFragment(), "trip_detail_find_create_fragment")
                        .addToBackStack(null)
                        .commit();
                dismiss();
            }
        });

        btnClick1 = view.findViewById(R.id.btnClick1);
        btnClick2 = view.findViewById(R.id.btnClick2);
        btnClick3 = view.findViewById(R.id.btnClick3);
        btnClick4 = view.findViewById(R.id.btnCLick4);
        txtChange1 = view.findViewById(R.id.txtChange1);
        txtChange2 = view.findViewById(R.id.txtChange3);

        btnClick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1--;
                txtChange1.setText(number1 + "");
            }
        });

        btnClick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1++;
                txtChange1.setText(number1 + "");
            }
        });

        btnClick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number2--;
                txtChange2.setText(number2 + "");
            }
        });

        btnClick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number2++;
                txtChange2.setText(number2 + "");
            }
        });


        return view;
    }
}
