package com.example.giaohangchatluong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.giaohangchatluong.Model.CTHD;
import com.example.giaohangchatluong.api.APIService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillInfoActivity extends AppCompatActivity {

    String SoHD;

    TextView txtSoPGH_CT;
    TextView txtNgayGui_CT;
    TextView txtTenKN_CT;
    TextView txtNoiDen_CT;
    TextView txtNoiGui_CT;
    TextView txtTenKH_CT;
    TextView txtTongTien_CT;
    TextView txtTrangThai_CT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_info);
        SoHD =getIntent().getStringExtra("SoHD");

        loadControl();
        loadData();
    }

    void loadControl(){
        txtSoPGH_CT = findViewById(R.id.txtSoPGH_CT);
        txtNgayGui_CT = findViewById(R.id.txtNgayGui_CT);
        txtTenKN_CT = findViewById(R.id.txtTenKN_CT);
        txtNoiDen_CT = findViewById(R.id.txtNoiDen_CT);
        txtNoiGui_CT=findViewById(R.id.txtNoiGui_CT);
        txtTenKH_CT=findViewById(R.id.txtTenKH_CT);
        txtTongTien_CT=findViewById(R.id.txtTongTien_CT);
        txtTrangThai_CT=findViewById(R.id.txtTrangThai_CT);
    }

    void loadData(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM/dd/yyyy");
        APIService.API_SERVICE.getCTHD(SoHD).enqueue(new Callback<List<CTHD>>() {
            @Override
            public void onResponse(Call<List<CTHD>> call, Response<List<CTHD>> response) {
                if(!response.body().isEmpty()){
                    for (CTHD e:  response.body()) {
                        txtSoPGH_CT.setText(e.getSoPGH());
                        txtNgayGui_CT.setText(e.getNgayGui());
                        txtNoiDen_CT.setText(e.getNoiDen());
                        txtNoiGui_CT.setText(e.getNoiGui());
                        txtTenKH_CT.setText(e.getTenKH());
                        txtTenKN_CT.setText(e.getTenKN());
                        txtTongTien_CT.setText(String.valueOf(e.getTongTien()));
                        if(e.getTrangThai().equals("false")) txtTrangThai_CT.setText("Chưa hoàn tất");
                        else txtTrangThai_CT.setText("Hoàn tất");
                    }
                }

            }

            @Override
            public void onFailure(Call<List<CTHD>> call, Throwable t) {

            }
        });
    }
}