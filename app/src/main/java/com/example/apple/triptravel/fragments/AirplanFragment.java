package com.example.apple.triptravel.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.flights.DealAdapter;
import com.example.apple.triptravel.adapter.flights.ListPopularDestinationsAdapter;
import com.example.apple.triptravel.models.Deal;
import com.example.apple.triptravel.models.Flights;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AirplanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AirplanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AirplanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

//    component popular of flight fragment
    private RecyclerView rlPopularDestination;
    private RecyclerView.LayoutManager layoutManager;
    private ListPopularDestinationsAdapter adapter;
    private List<Flights> list;

    //component deal of flights fragment
    private RecyclerView rlDeal;
    private RecyclerView.LayoutManager layoutManagerDeal;
    private DealAdapter adapterDeal;
    private List<Deal> listDeal;

    //conponent city breaks of flight fragment
    private RecyclerView rlCityBreak;
    private RecyclerView.LayoutManager layoutManagerCity;
    private ListPopularDestinationsAdapter adapterCity ;
    private List<Flights> listCity;

    private TextView txtFlights;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AirplanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AirplanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AirplanFragment newInstance(String param1, String param2) {
        AirplanFragment fragment = new AirplanFragment();
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
        View view = inflater.inflate(R.layout.fragment_airplan, container, false);

        txtFlights = view.findViewById(R.id.txtFlights);

        txtFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AirplanFindFragment()).commit();
            }
        });

        rlPopularDestination = view.findViewById(R.id.rlvListPopular);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlPopularDestination.setLayoutManager(layoutManager);
        rlPopularDestination.setHasFixedSize(true);

        //popular custom
        list = new ArrayList<>();
        list.add(new Flights("Dubai United Arab Emirates",   "https://static.businessinsider.com/image/534407baeab8ea15120d5800/image.jpg"));
        list.add(new Flights("Shanghai, China",              "https://static6.businessinsider.com/image/57e54983dd0895435a8b4835-1200/19-shanghai-china.jpg"));
        list.add(new Flights("Vienna, Austria",              "https://static5.businessinsider.com/image/57e54983dd0895435a8b4836-1200/18-vienna-austria.jpg"));
        list.add(new Flights("Osaka, Japan",                 "https://static3.businessinsider.com/image/57e54983dd0895435a8b4837-1200/17-osaka-japan.jpg"));
        list.add(new Flights("Rome, Italy",                  "https://static3.businessinsider.com/image/57e54983dd0895435a8b4838-1200/16-rome-italy.jpg"));

        adapter = new ListPopularDestinationsAdapter(getContext(), list);
        rlPopularDestination.setAdapter(adapter);

        //Deal custom
        rlDeal = view.findViewById(R.id.rlvListDeal);
        layoutManagerDeal = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlDeal.setLayoutManager(layoutManagerDeal);
        rlDeal.setHasFixedSize(true);

        //popular custom
        listDeal = new ArrayList<>();

        listDeal.add(new Deal.Builder()
                .setImgUrl("https://imagesvc.timeincapp.com/v3/mm/image?url=https%3A%2F%2Ftimedotcom.files.wordpress.com%2F2018%2F08%2F180803-cheapest-international-destinations-merida2.jpg&w=1600&q=70")
                .setTitle("Mérida, Mexico")
                .setPrice("$389")
                .setDescription("Among the affordable Mexican vacation spots, Mérida comes out on top for both affordability and richness of experience.")
                .setDealLeft("3 deals left")
                .build());

        listDeal.add(new Deal.Builder()
                .setImgUrl("https://imagesvc.timeincapp.com/v3/mm/image?url=https%3A%2F%2Ftimedotcom.files.wordpress.com%2F2018%2F08%2F180803-cheapest-international-destinations-xian.jpg&w=1600&q=70")
                .setTitle("Xi’an, China")
                .setPrice("$631")
                .setDescription("Xi’an is one of the most affordable cities to visit in China right now. You can find good deals on flights for around $631")
                .setDealLeft("5 deals left")
                .build());

        listDeal.add(new Deal.Builder()
                .setImgUrl("https://imagesvc.timeincapp.com/v3/mm/image?url=https%3A%2F%2Ftimedotcom.files.wordpress.com%2F2018%2F08%2F180803-cheapest-international-destinations-bali1.jpg&w=1600&q=70")
                .setTitle("Bali, Indonesia")
                .setPrice("$856")
                .setDescription("Known for its titanic beaches and hundreds of Hindu shrines and temples, it’s no wonder Bali gets called the Island of the Gods.")
                .setDealLeft("2 deals left")
                .build());

        listDeal.add(new Deal.Builder()
                .setImgUrl("https://imagesvc.timeincapp.com/v3/mm/image?url=https%3A%2F%2Ftimedotcom.files.wordpress.com%2F2018%2F08%2F180803-cheapest-international-destinations-calgary.jpg&w=1600&q=70")
                .setTitle("Calgary, Canada")
                .setPrice("$330")
                .setDescription("Visit Calgary for its cultural history, as well as its proximity to outdoor adventure spots in the Canadian Rocky Mountains.")
                .setDealLeft("10 deals left")
                .build());

        listDeal.add(new Deal.Builder()
                .setImgUrl("https://imagesvc.timeincapp.com/v3/mm/image?url=https%3A%2F%2Ftimedotcom.files.wordpress.com%2F2018%2F08%2F180803-cheapest-international-destinations-tbilisi1.jpg&w=1600&q=70")
                .setTitle("Tbilisi, Georgia")
                .setPrice("$709")
                .setDescription("Travel to Tbilisi, an ancient crossroads between Asia and Europe, for a blend of medieval architecture and modern food and drink.")
                .setDealLeft("6 deals left")
                .build());


        adapterDeal = new DealAdapter(getContext(), listDeal);
        rlDeal.setAdapter(adapterDeal);

        //custom city break
        rlCityBreak = view.findViewById(R.id.rlvListCityBreak);
        layoutManagerCity = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlCityBreak.setLayoutManager(layoutManagerCity);
        rlCityBreak.setHasFixedSize(true);

        //popular custom
        list = new ArrayList<>();
        list.add(new Flights("Salzburg, Austria",               "https://www.myholidayguru.co.uk/wp-content/uploads/2017/10/Stadhouderskade-winter-iStock-457820207-2-707x471.jpg"));
        list.add(new Flights("Tallinn, Estonia",                "https://www.myholidayguru.co.uk/wp-content/uploads/2017/12/shutterstock_411842389-1-smaller-707x382.jpg"));
        list.add(new Flights("Amsterdam, the Netherlands",      "https://www.myholidayguru.co.uk/wp-content/uploads/2017/10/tallinn-shutterstock_156630065-707x471.jpg"));
        list.add(new Flights("Reykjavik, Iceland",              "https://www.myholidayguru.co.uk/wp-content/uploads/2017/10/Amsterdam-sunrise-canal-iStock_31133062_XLARGE-2-1-707x469.jpg"));
        list.add(new Flights("Riga, Latvia",                    "https://www.myholidayguru.co.uk/wp-content/uploads/2017/10/Northern-lights-%C3%BCber-Reykjavik-Island-iStock_000034689888_Large-2-707x469.jpg"));

        adapterCity = new ListPopularDestinationsAdapter(getContext(), list);

        rlCityBreak.setAdapter(adapterCity);

        initDialog();

        return view;
    }

    private void initDialog() {
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getContext()).build();
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1000);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
