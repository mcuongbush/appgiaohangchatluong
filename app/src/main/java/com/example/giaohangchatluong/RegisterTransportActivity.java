package com.example.giaohangchatluong;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giaohangchatluong.Model.KhachHang;
import com.example.giaohangchatluong.Model.KhachNhan;
import com.example.giaohangchatluong.Model.LoaiVanChuyen;
import com.example.giaohangchatluong.Model.PhieuYeuCau;
import com.example.giaohangchatluong.api.APIService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterTransportActivity extends AppCompatActivity {

    RegisterTransportActivity context;
    TextView txtThanhToan;
    EditText txtWeight;
    EditText txtReceiverName;
    EditText txtNumberPhoneReceiver;
    EditText txtAddressReceiver;
    AutoCompleteTextView txtLoaiVC;
    CheckBox cBxCOD;

    ArrayAdapter<LoaiVanChuyen> adapterLVC;
    String DiaChiKH;
    String MaKH;
    String MaLVC;
    String MaKN;
    boolean flagTxtAddress;
    boolean flagTxtWeight;
    boolean flagLvc;
    long GiaLVC;
    long Total;
    int km;

    boolean availableKN = false;

    AlertDialog.Builder builder;
    Button btn_Confirm_Transport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_transport);
        context=this;

        MaKH = getIntent().getStringExtra("MaKH");
        getData();
        loadControll();

    }


    void AddPhieuYeuCau()
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
            PhieuYeuCau pyc =new PhieuYeuCau(LocalDate.now().format(formatter),MaKH,Float.parseFloat(txtWeight.getText().toString()),MaKN,MaLVC,Total,String.valueOf(cBxCOD.isChecked()));
            APIService.API_SERVICE.addPhieuYeuCau(pyc).enqueue(new Callback<List<PhieuYeuCau>>() {
                @Override
                public void onResponse(Call<List<PhieuYeuCau>> call, Response<List<PhieuYeuCau>> response) {
                    AlertDialog.Builder Builder = new AlertDialog.Builder(context) ;
                    Builder.setMessage("Đăng ký phiếu yêu cầu thành công!").setCancelable(true)
                            .setPositiveButton("Đóng", (dialogInterface, i) -> finish());
                    AlertDialog alert = Builder.create();
                    alert.setTitle("Thông báo!");
                    alert.show();
                }

                @Override
                public void onFailure(Call<List<PhieuYeuCau>> call, Throwable t) {

                }
            });
        }
    }
    Boolean check()
    {
        return !txtNumberPhoneReceiver.getText().toString().isEmpty() && Total!=0 && !txtAddressReceiver.getText().toString().isEmpty() && !txtReceiverName.getText().toString().isEmpty() &&
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
                        calTotal();
                        APIService.API_SERVICE.getKhoangCach(DiaChiKH,kn.getDiaChi()).enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                km = response.body();
                                flagTxtAddress=true;
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                Log.e("Loi: ", t.toString());
                            }
                        });
                        availableKN = true;
                    }
                    else {
                        availableKN=false;
                        flagTxtAddress=false;
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

        flagTxtWeight = flagTxtAddress = flagLvc = false;

        txtAddressReceiver.setOnFocusChangeListener((v,focus) ->{
            if(!focus){
                if(!txtAddressReceiver.getText().toString().isEmpty())
                {
                    APIService.API_SERVICE.getKhoangCach(DiaChiKH,txtAddressReceiver.getText().toString()).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            km=response.body();
                            calTotal();
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Log.e("Loi: ", t.toString());
                        }
                    });
                }
                else km=0;
            }
        });

        txtWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 calTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtNumberPhoneReceiver.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) iSValidKN();
        });

        btn_Confirm_Transport.setOnClickListener(v -> {
            AlertDialog.Builder Builder = new AlertDialog.Builder(context) ;
            Builder.setMessage("Bạn xác nhận đăng ký gửi hàng?").setCancelable(true)
                    .setPositiveButton("Xác nhận", (dialogInterface, i) -> AddPhieuYeuCau());
            Builder.setNeutralButton("Hủy", (dialog, which) -> {

            });

            AlertDialog alert = Builder.create();
            alert.setTitle("Xác nhận!");
            alert.show();
        });

        APIService.API_SERVICE.getLoaiVC().enqueue(new Callback<List<LoaiVanChuyen>>() {
            @Override
            public void onResponse(Call<List<LoaiVanChuyen>> call, Response<List<LoaiVanChuyen>> response) {
                adapterLVC = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, response.body());
                txtLoaiVC = findViewById(R.id.txtLoaiVC);
                txtLoaiVC.setAdapter(adapterLVC);
                txtLoaiVC.setOnItemClickListener((parent, view, position, id) -> {
                    GiaLVC=adapterLVC.getItem(position).getGia();
                    calTotal();
                    MaLVC=adapterLVC.getItem(position).getMaLVC();
                });
            }
            @Override
            public void onFailure(Call<List<LoaiVanChuyen>> call, Throwable t) {

            }
        });
    }

    void getData(){
        APIService.API_SERVICE.getTtKhachHang(MaKH).enqueue(new Callback<List<KhachHang>>() {
            @Override
            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                KhachHang kh = response.body().get(0);
                DiaChiKH=kh.getDiaChi();
            }
            @Override
            public void onFailure(Call<List<KhachHang>> call, Throwable t) {

            }
        });
    }


    void calTotal(){
        Total = 0;
        if (GiaLVC != 0 && km !=0 && !txtWeight.getText().toString().isEmpty() && !txtAddressReceiver.getText().toString().isEmpty()) {
            double weight = Double.parseDouble(txtWeight.getText().toString());
            double roundWeight =  Math.round(weight*10 /10);
            if (km <= 100) {
                if(weight <=1.6 )
                {
                    Total = GiaLVC;
                }
                else
                {
                    Total =  GiaLVC + ((GiaLVC/100)* 5 ) * (long) (roundWeight-1)*10/10 ;

                }
            }
            else
            {
                if(weight<=1.6) {
                    if ((km - 100) / 100 < 1) {
                        Total = GiaLVC + ((GiaLVC / 100) * 25);
                    } else {
                        int t = (km - 100) / 100;
                        Total = GiaLVC + ((GiaLVC / 100) * 25) * t;
                    }
                }
                //if(weight<=1.6) Total = GiaLVC + ((GiaLVC/100) *25) * (km-100);
                else{
                    int t = (km - 100) / 100;
                        Total = GiaLVC + (((GiaLVC/100)*5 ) * (long) ((roundWeight-1)*1)) + ((GiaLVC/100) *25) * t;
                }
            }
            txtThanhToan.setText(String.valueOf(Total));
        }
        else txtThanhToan.setText(String.valueOf(0));
    }
}