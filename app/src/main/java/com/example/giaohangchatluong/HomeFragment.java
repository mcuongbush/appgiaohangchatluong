package com.example.giaohangchatluong;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.giaohangchatluong.Model.HoaDon;
import com.example.giaohangchatluong.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link HomeFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class HomeFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    double a;
    double b;
    View fragmentView ;
    String MaKH;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView= inflater.inflate(R.layout.fragment_home, container, false);

        a = 20.4;
        b = 2.6;

        int t = (int) (a*10) %10;
        double c = (double) Math.round((a*10+10)/10);
        MainActivity mainActivity = (MainActivity) getActivity();
        MaKH = mainActivity.getMaKH();
        final ListView lst_Item = fragmentView.findViewById(R.id.lst_Item);
        APIService.API_SERVICE.getHoaDon(MaKH).enqueue(new Callback<List<HoaDon>>() {
            @Override
            public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
                List<HoaDon> ls = response.body();
                if( response.body().size() >0) lst_Item.setAdapter(new CustomListAdapter(fragmentView.getContext(), response.body()));
            }
            @Override
            public void onFailure(Call<List<HoaDon>> call, Throwable t) {

            }
        });

        final ImageButton btn_RegisterTransport = fragmentView.findViewById(R.id.btn_Transport_Register);
        btn_RegisterTransport.setOnClickListener(v -> {
            Intent intent = new Intent(fragmentView.getContext(),RegisterTransportActivity.class);
            intent.putExtra("MaKH",MaKH);
            startActivity(intent);
        });

        return fragmentView;
    }


}