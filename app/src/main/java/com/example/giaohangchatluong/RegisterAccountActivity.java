package com.example.giaohangchatluong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.giaohangchatluong.Model.KhachHang;
import com.example.giaohangchatluong.Model.TaiKhoanKH;
import com.example.giaohangchatluong.api.APIService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAccountActivity extends AppCompatActivity {


    Context context;
    EditText txtUserName;
    EditText txtCusAddress;
    EditText txtCusName;
    EditText txtCusNumberPhone;
    RadioButton rdbNam;
    RadioButton rdbNu;
    EditText txtreUserPassword;
    EditText txtUserPassword;
    //TaiKhoanKH taiKhoanKH;
    KhachHang maKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        context = this;


        txtUserName = findViewById(R.id.txtUsername_R);
        txtCusAddress = findViewById(R.id.txtCusAddress);
        txtCusName = findViewById(R.id.txtCusName);
        txtCusNumberPhone = findViewById(R.id.txtCusNumberPhone);
        rdbNam = findViewById(R.id.rBtnNam);
        rdbNu = findViewById(R.id.rBtnNu);
        txtreUserPassword = findViewById(R.id.txtreUserPassword);
        txtUserPassword =  findViewById(R.id.txtUserPassword);

        Button btnConRegister = findViewById(R.id.btn_Confirm_Register);
        btnConRegister.setOnClickListener(v -> Register());

        txtUserPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    if(txtUserPassword.getText().toString().trim().length()<8)
                    {
                        String msg = "Mật khẩu phải dài hơn 8 ký tự";
                        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        txtUserPassword.setError(null);
                    }
                }
            }
        });


    }

    private void Register() {

        if(check()){
            if(!txtUserPassword.getText().toString().trim().equals(txtreUserPassword.getText().toString().trim()))
            {
                Toast.makeText(context,"Mật khẩu không khớp, vui lòng nhập lại",Toast.LENGTH_LONG).show();
            }
            else
            {
                APIService.API_SERVICE.getListKhachHang().enqueue(new Callback<List<KhachHang>>() {
                    @Override
                    public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                        List<KhachHang> lstKH = response.body();
                        boolean flag = true;
                        for (KhachHang kh : lstKH) {
                            if (kh.getSDT().contains(txtCusNumberPhone.getText().toString()))
                            {
                                flag =false;
                                Toast.makeText(context,"Số điện thoại đã có người sử dụng, vui lòng nhập lại",Toast.LENGTH_LONG).show();
                                break;
                            }
                        }
                        if( flag)
                        {
                            String GioiTinh;
                            if(rdbNam.isChecked()) GioiTinh="FALSE";
                            else GioiTinh="TRUE";
                            KhachHang khachHang = new KhachHang(txtCusName.getText().toString(),txtCusNumberPhone.getText().toString(),GioiTinh,txtCusAddress.getText().toString());
                            APIService.API_SERVICE.addKhachHang(khachHang).enqueue(new Callback<List<KhachHang>>() {
                                @Override
                                public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                                    String MaKH = response.body().get(0).getMaKH();
                                    TaiKhoanKH taiKhoanKH = new TaiKhoanKH(txtUserName.getText().toString().trim(), txtUserPassword.getText().toString().trim(), MaKH);
                                    APIService.API_SERVICE.addTaiKhoanKH(taiKhoanKH).enqueue(new Callback<List<TaiKhoanKH>>() {
                                        @Override
                                        public void onResponse(Call<List<TaiKhoanKH>> call, Response<List<TaiKhoanKH>> response) {
                                            Toast.makeText(context, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                                            LoginActivity.getInstance().getListTK();
                                            finishAffinity();
                                            startActivity(new Intent(context,LoginActivity.class));
                                        }

                                        @Override
                                        public void onFailure(Call<List<TaiKhoanKH>> call, Throwable t) {

                                        }
                                    });
                                }
                                @Override
                                public void onFailure(Call<List<KhachHang>> call, Throwable t) {

                                }
                            });
//                            APIService.API_SERVICE.getKhachHang(khachHang.getSDT().trim()).enqueue(new Callback<List<KhachHang>>() {
//                                @Override
//                                public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
//                                    KhachHang kh = response.body().get(0);
//                                    if(kh!=null) {
//                                        TaiKhoanKH taiKhoanKH = new TaiKhoanKH(txtUserName.getText().toString().trim(), txtUserPassword.getText().toString().trim(), kh.getMaKH());
//                                        APIService.API_SERVICE.addTaiKhoanKH(taiKhoanKH).enqueue(new Callback<List<TaiKhoanKH>>() {
//                                            @Override
//                                            public void onResponse(Call<List<TaiKhoanKH>> call, Response<List<TaiKhoanKH>> response) {
//                                                Toast.makeText(context, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
//                                                LoginActivity.getInstance().getListTK();
//                                                finishAffinity();
//                                                startActivity(new Intent(context,LoginActivity.class));
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<List<TaiKhoanKH>> call, Throwable t) {
//
//                                            }
//                                        });
//                                    }
//                                }
//                                @Override
//                                public void onFailure(Call<List<KhachHang>> call, Throwable t) {
//
//                                }
//                            });
                        }
                    }
                    @Override
                    public void onFailure(Call<List<KhachHang>> call, Throwable t) {

                    }
                });
            }
        }
        else Toast.makeText(context,"Bạn chưa nhập đầy đủ thông tin, vui lòng nhập lại!",Toast.LENGTH_LONG).show();
    }

    private boolean check() {
        if(txtUserName.getText().toString().isEmpty() || txtCusName.getText().toString().isEmpty() || txtCusAddress.getText().toString().isEmpty()
            ||txtUserPassword.getText().toString().isEmpty() || txtCusNumberPhone.getText().toString().isEmpty()
            || txtreUserPassword.getText().toString().isEmpty())
            return false;
        else return true;
//        return !txtUserName.getText().toString().isEmpty() && !txtCusName.getText().toString().isEmpty() && !txtCusAddress.getText().toString().isEmpty()
//                && !txtUserPassword.getText().toString().isEmpty() && !txtCusNumberPhone.getText().toString().isEmpty()
//                && !txtreUserPassword.getText().toString().isEmpty();
    }


}