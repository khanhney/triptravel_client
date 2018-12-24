package com.example.apple.triptravel.fragments;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.triptravel.R;
import com.example.apple.triptravel.adapter.flights.DealAdapter;
import com.example.apple.triptravel.adapter.flights.ListPopularDestinationsAdapter;
import com.example.apple.triptravel.adapter.hotel.DealHotelAdapter;
import com.example.apple.triptravel.adapter.hotel.ListPopularDestinationsHotelAdapter;
import com.example.apple.triptravel.adapter.search.ListSearchAdapter;
import com.example.apple.triptravel.common.UserCommon;
import com.example.apple.triptravel.interfaces.GetHotelService;
import com.example.apple.triptravel.models.Deal;
import com.example.apple.triptravel.models.Flights;
import com.example.apple.triptravel.models.hotel.DataHotel;
import com.example.apple.triptravel.models.hotel.Hotel;
import com.example.apple.triptravel.models.search.DataSearch;
import com.example.apple.triptravel.models.search.SearchHotel;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HotelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView txtHotel;

    //    component popular of flight fragment
    private RecyclerView rlNearYou;
    private RecyclerView.LayoutManager layoutManager;
    private ListPopularDestinationsHotelAdapter adapter;
    private List<DataHotel> list;

    //component deal of flights fragment
    private RecyclerView rlDeal;
    private RecyclerView.LayoutManager layoutManagerDeal;
    private DealHotelAdapter adapterDeal;
    private List<DataHotel> listDeal;

    //conponent city breaks of flight fragment
    private RecyclerView rlBestHotel;
    private RecyclerView.LayoutManager layoutManagerBestHotel;
    private ListPopularDestinationsAdapter adapterBestHotel ;
    private List<Flights> listBestHotel;

    private AutoCompleteTextView editSearchHotel;
    private ListSearchAdapter adapterSearch;
    private List<DataSearch> listSearch;

    private GetHotelService service;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotelFragment newInstance(String param1, String param2) {
        HotelFragment fragment = new HotelFragment();
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
        View view = inflater.inflate(R.layout.fragment_hotel, container, false);

        service = UserCommon.getHotelService();

        txtHotel = view.findViewById(R.id.txtHotel);

        rlNearYou = view.findViewById(R.id.rlvListNearYouFragmentHotel);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlNearYou.setLayoutManager(layoutManager);
        rlNearYou.setHasFixedSize(true);

        //popular custom
        list = new ArrayList<>();

        adapter = new ListPopularDestinationsHotelAdapter(getContext(), list);
        rlNearYou.setAdapter(adapter);

        //Deal custom
        rlDeal = view.findViewById(R.id.rlvListDealFragmentHotel);
        layoutManagerDeal = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlDeal.setLayoutManager(layoutManagerDeal);
        rlDeal.setHasFixedSize(true);

        //popular custom
        listDeal = new ArrayList<>();

        adapterDeal = new DealHotelAdapter(getContext(), listDeal);
        rlDeal.setAdapter(adapterDeal);

        //custom city break
        rlBestHotel = view.findViewById(R.id.rlvListBestHotelFragmentHotel);
        layoutManagerBestHotel = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlBestHotel.setLayoutManager(layoutManagerBestHotel);
        rlBestHotel.setHasFixedSize(true);

        //popular custom
        listBestHotel = new ArrayList<>();
        listBestHotel.add(new Flights("InterContinental",        "https://d4qwptktddc5f.cloudfront.net/ac-martin-intercontinental-la-sky-lobby-1117.jpg"));
        listBestHotel.add(new Flights("Renaissance Atlanta",     "https://d4qwptktddc5f.cloudfront.net/rottet-studio-renaissance-atlanta-airport-gateway-lounge-sofas-1017.jpg"));
        listBestHotel.add(new Flights("Hotel Brooklyn ",         "https://d4qwptktddc5f.cloudfront.net/inc-architecture-1-hotel-brooklyn-lobby-plant-wall-0917.jpg"));
        listBestHotel.add(new Flights("Museum Hotel",            "https://d4qwptktddc5f.cloudfront.net/10-deborah-berke-21c-museum-hotel-nashville-restaurant-entrance-0817.jpg"));
        listBestHotel.add(new Flights("The East",                "https://d4qwptktddc5f.cloudfront.net/clodagh-design-international-east-miami-hotel-concrete-walls-0717.jpg"));

        adapterBestHotel = new ListPopularDestinationsAdapter(getContext(), listBestHotel);
        rlBestHotel.setAdapter(adapterBestHotel);


        addControls(view);
        addEvents(view);
        initLoadingApi();



        return  view;
    }

    private void addEvents(View view) {
        editSearchHotel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                try{
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            service.getSearchHotel(s.toString()).enqueue(new Callback<SearchHotel>() {
                                @Override
                                public void onResponse(@NonNull Call<SearchHotel> call, @NonNull Response<SearchHotel> response) {
                                    listSearch.clear();
                                    if (response.isSuccessful()){
                                        if (!response.body().getError()){
                                            listSearch.addAll(response.body().getData());
//                                            adapterSearch.notifyDataSetChanged();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<SearchHotel> call, Throwable t) {
                                    Log.e("___", t.getMessage());
                                }
                            });
                        }
                    }, 300);
                }catch (Exception e){
                    Log.e("___ERROR", e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                s.clear();
            }
        });

//        editSearchHotel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                DataSearch dataSearch = listSearch.get(i);
//
//                DataHotel dataHotel = new DataHotel(
//                        dataSearch.getStatus(),
//                        dataSearch.getId(),
//                        dataSearch.getTitle(),
//                        dataSearch.getDescription(),
//                        dataSearch.getPrice(),
//                        dataSearch.getV(),
//                        dataSearch.getImage(),
//                        dataSearch.getReview(),
//                        dataSearch.getUpdateAt());
//
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("DATA_HOTEL", dataHotel);
//                HotelDetailFragment hotelDetailFragment = new HotelDetailFragment();
//                hotelDetailFragment.setArguments(bundle);
//
//                ((AppCompatActivity) getActivity()).getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, hotelDetailFragment, "hotel_detail_fragment")
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
    }

    private void addControls(View view) {
        editSearchHotel = view.findViewById(R.id.editSearchHotel);
        editSearchHotel.setHasTransientState(true);
        listSearch = new ArrayList<>();
        adapterSearch = new ListSearchAdapter(getActivity(), listSearch);
        editSearchHotel.setAdapter(adapterSearch);
    }

    private void initLoadingApi() {
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getContext()).build();
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        service.getListHotel().enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(@NonNull Call<Hotel> call, @NonNull Response<Hotel> response) {
                if (response.isSuccessful()){
                    if (response.body().getError()){
                        Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }else {
                        for (int i = 0; i < response.body().getData().size(); i++){
                            if (i % 2 != 0){
                                list.add(response.body().getData().get(i));
                            }else {
                                listDeal.add(response.body().getData().get(i));
                            }
                        }

                        adapter.notifyDataSetChanged();
                        adapterDeal.notifyDataSetChanged();
                    }
                }

                dialog.dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<Hotel> call, @NonNull Throwable t) {
                dialog.dismiss();
            }
        });


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
