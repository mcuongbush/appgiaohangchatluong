package com.example.giaohangchatluong;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giaohangchatluong.Model.TaiKhoanKH;
import com.example.giaohangchatluong.api.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    private EditText txtUsername;
    private EditText txtPassword;
    Button btn_login;
    private static List<TaiKhoanKH> lstTaiKhoan;
    TextView txtForgetPass;
    public boolean flag = false;

    String Username;
    String Password;

    public static LoginActivity instant ;
    static String MaKH;
    Boolean logout=false;

    ImageButton btn_search;

    ActivityResultLauncher<Intent> startLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadControll();


        try {
            SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);
            if(sp1.getString("sttLog",null).contains("true"))
            {
                Username = "";
                Password = "";
            }
            else
            {
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor Ed = sp1.edit();
                Ed.putString("sttLog", "false");
                Ed.apply();
                Username = sp1.getString("Unm", null);
                Password = sp1.getString("Psw", null);
                login(Username,Password);
            }
        }catch (Exception e){
            Log.e("Loi",e.toString());
        }


        instant = this;

        if(!isNetworkAvailable())
        {
            builder = new AlertDialog.Builder(this);
            builder.setMessage("Không thể kết nối mạng, vui lòng thử lại!").setCancelable(false)
                    .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finishAffinity();
                            System.exit(0);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Lỗi kết nối mạng");
            alert.show();
        }
    }


    public static LoginActivity getInstance(){
        if (instant==null) instant = new LoginActivity();
        return instant;
    }

    void loadControll(){
        txtUsername=findViewById(R.id.txtUsername);
        txtPassword=findViewById(R.id.txtPassword);
        txtForgetPass=findViewById(R.id.txtForgetPass);
        btn_search=findViewById(R.id.btn_search);
        btn_login = findViewById(R.id.btn_Login);
        //Username = txtUsername.getText().toString().trim();
        //Password = txtPassword.getText().toString().trim();
        btn_login.setOnClickListener(v -> login(txtUsername.getText().toString().trim(),txtPassword.getText().toString().trim()));

        btn_search.setOnClickListener(v->{
            startActivity(new Intent(instant,SearchActivity.class));
        });

        txtForgetPass.setOnClickListener(v-> {
            Intent intent =new Intent(instant,ForgetPasswordActivity.class);
            startLauncher.launch(intent);
        });
    }

    public static void setInstant(LoginActivity instant){
        LoginActivity.instant=instant;
    }

//    void getListTK()
//    {
//
//        APIService.API_SERVICE.getTaiKhoanKH().enqueue(new Callback<List<TaiKhoanKH>>() {
//            @Override
//            public void onResponse(Call<List<TaiKhoanKH>> call, Response<List<TaiKhoanKH>> response) {
//                lstTaiKhoan = response.body();
//                for ( TaiKhoanKH e: lstTaiKhoan) {
//
//                    if ((e.getTenTK().equals(strUsername) )&& e.getMatKhau().equals(strPassword)) {
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        MaKH = e.getMaKH();
//                        intent.putExtra("MaKH", e.getMaKH());
//                        intent.putExtra("TenTK", strUsername);
//                        finishAffinity();
//                        startLauncher.launch(intent);
//                        String msg = "Chào mừng bạn quay trở lại!";
//                        Toast.makeText(instant, msg, Toast.LENGTH_LONG).show();
//                        flag = true;
//                        break;
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<List<TaiKhoanKH>> call, Throwable t) {
//
//            }
//        });
//
//    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void OpenRegisterPage(View view) {
        Intent intent = new Intent(LoginActivity.this,RegisterAccountActivity.class);
        startLauncher.launch(intent);

    }

    public void login(String strUsername, String strPassword ) {


        APIService.API_SERVICE.getTaiKhoanKH(strUsername,strPassword).enqueue(new Callback<List<TaiKhoanKH>>() {
            @Override
            public void onResponse(Call<List<TaiKhoanKH>> call, Response<List<TaiKhoanKH>> response) {
                if(response.body()!=null){
                    TaiKhoanKH e = response.body().get(0);
                    if (e.getTenTK().equals(strUsername) && e.getMatKhau().equals(strPassword)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        MaKH = e.getMaKH();
                        intent.putExtra("MaKH", e.getMaKH());
                        intent.putExtra("TenTK", strUsername);
                        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                        SharedPreferences.Editor Ed = sp.edit();
                        Ed.putString("sttLog", "false");
                        Ed.putString("Unm", strUsername);
                        Ed.putString("Psw", strPassword);
                        Ed.apply();
                        finishAffinity();
                        startLauncher.launch(intent);
                        String msg = "Chào mừng bạn quay trở lại!";
                        Toast.makeText(instant, msg, Toast.LENGTH_LONG).show();
                        flag = true;
                    }

                }
                if(!flag) {
                    String msg = "Sai tài khoản hoặc mật khẩu, vui lòng thử lại!";
                    Toast.makeText(instant, msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<TaiKhoanKH>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Call API fail",Toast.LENGTH_SHORT).show();
                Log.e("Call API",t.toString());
            }
        });

//        for ( TaiKhoanKH e: lstTaiKhoan) {
//
//            if (e.getTenTK().equals(strUsername) && e.getMatKhau().equals(strPassword)) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                MaKH = e.getMaKH();
//                intent.putExtra("MaKH", e.getMaKH());
//                intent.putExtra("TenTK", strUsername);
//                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
//                SharedPreferences.Editor Ed = sp.edit();
//                Ed.putString("sttLog", "false");
//                Ed.putString("Unm", strUsername);
//                Ed.putString("Psw", strPassword);
//                Ed.apply();
//                finishAffinity();
//                startLauncher.launch(intent);
//                String msg = "Chào mừng bạn quay trở lại!";
//                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
//                flag = true;
//                break;
//            }
//        }

    }
}