package com.example.giaohangchatluong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.giaohangchatluong.Model.CTHD;
import com.example.giaohangchatluong.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillInfoMainActivity extends AppCompatActivity {

    String SoHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_info_main);
        SoHD =getIntent().getStringExtra("SoHD");

        APIService.API_SERVICE.getCTHD(SoHD).enqueue(new Callback<List<CTHD>>() {
            @Override
            public void onResponse(Call<List<CTHD>> call, Response<List<CTHD>> response) {

            }

            @Override
            public void onFailure(Call<List<CTHD>> call, Throwable t) {

            }
        });
    }
}