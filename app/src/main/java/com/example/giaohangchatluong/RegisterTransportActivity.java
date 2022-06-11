package com.example.giaohangchatluong;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giaohangchatluong.Model.KhachNhan;
import com.example.giaohangchatluong.Model.LoaiHH;
import com.example.giaohangchatluong.Model.LoaiVanChuyen;
import com.example.giaohangchatluong.Model.PhieuGuiHang;
import com.example.giaohangchatluong.api.APIService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterTransportActivity extends AppCompatActivity {

    public static List<LoaiHH> lstLHH;
    RegisterTransportActivity context;

    List<String> lstDV;



    TextView txtThanhToan;
    EditText txtWeight;
    EditText txtReceiverName;
    EditText txtNumberPhoneReceiver;
    EditText txtAddressReceiver;
    AutoCompleteTextView txtLoaiHH;
    AutoCompleteTextView txtLoaiVC;
    CheckBox cBxCOD;

    ArrayAdapter<LoaiHH> adapterLHH;
    ArrayAdapter<LoaiVanChuyen> adapterLVC;
    String MaKH;
    String MaLVC;
    String MaLHH;
    String MaKN;
    float Total;

    Boolean availableKN = false;

    AlertDialog.Builder builder;
    Button btn_Confirm_Transport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_transport);

        context=this;
        loadControll();
        getData();
    }


    void AddPhieuGuiHang()
    {
        if(check()) {
            if (!availableKN) {
                KhachNhan kn = new KhachNhan(txtReceiverName.getText().toString(),txtNumberPhoneReceiver.getText().toString(),txtAddressReceiver.getText().toString());
                APIService.API_SERVICE.addKhachNhan(kn).enqueue(new Callback<List<KhachNhan>>() {
                    @Override
                    public void onResponse(Call<List<KhachNhan>> call, Response<List<KhachNhan>> response) {
                        MaKN=response.body().get(0).getMaKN();
                    }
                    @Override
                    public void onFailure(Call<List<KhachNhan>> call, Throwable t) {

                    }
                });
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String date = LocalDate.now().format(formatter);
            PhieuGuiHang pgh = new PhieuGuiHang(LocalDate.now().format(formatter), String.valueOf(cBxCOD.isChecked()).toUpperCase(), MaKH,MaLVC, MaKN);
            APIService.API_SERVICE.addPhieuGuiHang(pgh).enqueue(new Callback<List<PhieuGuiHang>>() {
                @Override
                public void onResponse(Call<List<PhieuGuiHang>> call, Response<List<PhieuGuiHang>> response) {
                    AlertDialog.Builder Builder = new AlertDialog.Builder(context) ;
                    Builder.setMessage("Đăng ký phiếu gửi hàng thành công!").setCancelable(true)
                            .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                    AlertDialog alert = Builder.create();
                    alert.setTitle("Thông báo!");
                    alert.show();
                }

                @Override
                public void onFailure(Call<List<PhieuGuiHang>> call, Throwable t) {

                }
            });
        }
    }
    Boolean check()
    {
        return !txtNumberPhoneReceiver.getText().toString().isEmpty() && !txtAddressReceiver.getText().toString().isEmpty() && !txtReceiverName.getText().toString().isEmpty() &&
                 !txtWeight.getText().toString().isEmpty();
    }

    void iSValidKN()
    {
        if(!txtNumberPhoneReceiver.getText().toString().isEmpty()) {
            APIService.API_SERVICE.getKhachNhan(txtNumberPhoneReceiver.getText().toString()).enqueue(new Callback<List<KhachNhan>>() {
                @Override
                public void onResponse(Call<List<KhachNhan>> call, Response<List<KhachNhan>> response) {

                    if (!response.body().isEmpty()) {
                        KhachNhan kn = response.body().get(0);
                        MaKN = kn.getMaKN();
                        txtAddressReceiver.setEnabled(false);
                        txtReceiverName.setEnabled(false);
                        txtReceiverName.setText(kn.getTenKN());
                        txtAddressReceiver.setText(kn.getDiaChi());
                        availableKN = true;
                    }
                    else {
                        availableKN=false;
                        txtAddressReceiver.setEnabled(true);
                        txtReceiverName.setEnabled(true);
                        txtReceiverName.getText().clear();
                        txtAddressReceiver.getText().clear();
                    }
                }
                @Override
                public void onFailure(Call<List<KhachNhan>> call, Throwable t) {

                }
            });
        }
    }


    void loadControll()
    {

        txtWeight = findViewById(R.id.txtWeight);
        txtReceiverName = findViewById(R.id.txtReceiverName);
        txtNumberPhoneReceiver = findViewById(R.id.txtNumberPhoneReceiver);
        txtAddressReceiver = findViewById(R.id.txtAddressReceiver);
        txtLoaiVC = findViewById(R.id.txtLoaiVC);
        cBxCOD = findViewById(R.id.cBxCOD);
        txtThanhToan =findViewById(R.id.txtThanhToan);
        btn_Confirm_Transport=findViewById(R.id.btn_Confirm_Transport);


        txtNumberPhoneReceiver.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                iSValidKN();
            }
        });

        btn_Confirm_Transport.setOnClickListener(v -> {
            AlertDialog.Builder Builder = new AlertDialog.Builder(context) ;
            Builder.setMessage("Bạn xác nhận đăng ký gửi hàng?").setCancelable(true)
                    .setPositiveButton("Xác nhận", (dialogInterface, i) -> AddPhieuGuiHang());
            Builder.setNeutralButton("Hủy", (dialog, which) -> {

            });

            AlertDialog alert = Builder.create();
            alert.setTitle("Xác nhận!");
            alert.show();
        });
    }

    void  getData()
    {
        MaKH =getIntent().getStringExtra("MaKH");
        APIService.API_SERVICE.getLoaiHH().enqueue(new Callback<List<LoaiHH>>() {
            @Override
            public void onResponse(Call<List<LoaiHH>> call, Response<List<LoaiHH>> response) {
                adapterLHH = new ArrayAdapter<LoaiHH>(context, android.R.layout.simple_spinner_item,response.body());
                txtLoaiHH.setAdapter(adapterLHH);
                txtLoaiHH.setOnItemClickListener((parent, view, position, id) -> MaLHH=adapterLHH.getItem(position).getMaLHH());

            }
            @Override
            public void onFailure(Call<List<LoaiHH>> call, Throwable t) {

            }
        });
        APIService.API_SERVICE.getLoaiVC().enqueue(new Callback<List<LoaiVanChuyen>>() {
            @Override
            public void onResponse(Call<List<LoaiVanChuyen>> call, Response<List<LoaiVanChuyen>> response) {
                adapterLVC = new ArrayAdapter<LoaiVanChuyen>(context, android.R.layout.simple_spinner_item,response.body());
                txtLoaiVC = findViewById(R.id.txtLoaiVC);
                txtLoaiVC.setAdapter(adapterLVC);
                txtLoaiVC.setOnItemClickListener((parent, view, position, id) -> MaLVC=adapterLVC.getItem(position).getMaLVC());
            }
            @Override
            public void onFailure(Call<List<LoaiVanChuyen>> call, Throwable t) {

            }
        });


    }
}