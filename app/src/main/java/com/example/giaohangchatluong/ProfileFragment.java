package com.example.giaohangchatluong;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.giaohangchatluong.Model.KhachHang;
import com.example.giaohangchatluong.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ProfileFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ProfileFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public ProfileFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProfileFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ProfileFragment newInstance(String param1, String param2) {
//        ProfileFragment fragment = new ProfileFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    static List<KhachHang> lstKH;
    String MaKH;
    String TenTK;
    View fragmentView;
    EditText Txt_TenKH;
    EditText Txt_UserName;
    EditText Txt_UserGender;
    EditText Txt_UserPhone;
    EditText aTxt_UserAddress;
    Button btnDangXuat;
    Button btnSuaThongTin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        fragmentView= inflater.inflate(R.layout.fragment_profile, container, false);

        loadControll();

        MainActivity mainActivity = (MainActivity) getActivity();
        MaKH = mainActivity.getMaKH();
        TenTK = mainActivity.getTenTK();



        getData();
        return fragmentView;
    }

    void loadControll()
    {
        Txt_TenKH=  fragmentView.findViewById(R.id.aTxt_TenKH);
        Txt_UserGender= fragmentView.findViewById(R.id.aTxt_UserGender);
        Txt_UserName=fragmentView.findViewById(R.id.aTxt_UserName);
        Txt_UserPhone= fragmentView.findViewById(R.id.aTxt_UserPhone);
        aTxt_UserAddress=fragmentView.findViewById(R.id.aTxt_UserAddress);
        btnDangXuat=fragmentView.findViewById(R.id.btnDangXuat);
        btnSuaThongTin =fragmentView.findViewById(R.id.btnSuaThongTin);

        btnDangXuat.setOnClickListener(v -> {
            AlertDialog.Builder Builder = new AlertDialog.Builder(fragmentView.getContext()) ;
            Builder.setMessage("Bạn muốn đăng xuất?").setCancelable(true)
                    .setPositiveButton("Xác nhận", (dialogInterface, i) -> {
                        Intent intent = new Intent(fragmentView.getContext(),LoginActivity.class);
                        SharedPreferences sp = requireActivity().getSharedPreferences("Login", MODE_PRIVATE);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor Ed = sp.edit();
                        Ed.putString("sttLog", "true");
                        Ed.apply();
                        requireActivity().finish();
                        requireActivity().startActivity(intent);
                    });
            Builder.setNeutralButton("Hủy", (dialog, which) -> {

            });

            AlertDialog alert = Builder.create();
            alert.setTitle("Xác nhận!");
            alert.show();
        });

    }
    void setEnable(boolean b)
    {
        Txt_TenKH.setEnabled(b);
        Txt_UserName.setEnabled(b);
        Txt_UserGender.setEnabled(b);
        Txt_UserPhone.setEnabled(b);
        aTxt_UserAddress.setEnabled(b);
    }

    void getData()
    {
        setEnable(false);
        APIService.API_SERVICE.getTtKhachHang(MaKH).enqueue(new Callback<List<KhachHang>>() {
            @Override
            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                KhachHang kh = response.body().get(0);
                String gt;
                Txt_TenKH.setText(kh.getTenKH());
                if(kh.getGioiTinh().contains("True")) gt="Nữ";
                else gt="Nam";
                Txt_UserGender.setText(gt);
                Txt_UserPhone.setText(kh.getSDT());
                Txt_UserName.setText(TenTK);
                aTxt_UserAddress.setText(kh.getDiaChi());


            }
            @Override
            public void onFailure(Call<List<KhachHang>> call, Throwable t) {

            }
        });
    }

}