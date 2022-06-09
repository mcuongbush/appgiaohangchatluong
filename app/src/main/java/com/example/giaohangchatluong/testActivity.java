package com.example.giaohangchatluong;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giaohangchatluong.Model.KhachHang;
import com.example.giaohangchatluong.api.APIService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class testActivity extends AppCompatActivity {

    TextView txtV_GioiTinh;
    TextView txtV_TenKH;
    TextView txtV_SDT;
    TextView txtV_DiaChi;
    Button btn_Clik;
    KhachHang kh ;
    List<KhachHang> maKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        txtV_GioiTinh = findViewById(R.id.txtV_GioiTinh);
        txtV_TenKH = findViewById(R.id.txtV_TenKH);
        txtV_SDT = findViewById(R.id.txtV_SDT);
        txtV_DiaChi = findViewById(R.id.txtV_DiaChi);
        btn_Clik = findViewById(R.id.btn_click);

        btn_Clik.setOnClickListener(v -> addKhachHang());
    }

//    Toast.makeText(testActivity.this,"Call finish",Toast.LENGTH_SHORT).show();
    void Call ()
    {



//        APIService.API_SERVICE.getTaiKhoanKH().enqueue(new Callback<List<KhachHang>>() {
//            @Override
//            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
//                Toast.makeText(testActivity.this,"Call finish",Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<KhachHang>> call, Throwable t) {
//                Toast.makeText(testActivity.this,"Call fail",Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    void addKhachHang () {
    }
}