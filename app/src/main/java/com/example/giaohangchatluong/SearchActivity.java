package com.example.giaohangchatluong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giaohangchatluong.Model.CTVanChuyen;
import com.example.giaohangchatluong.Model.TheoDoi;
import com.example.giaohangchatluong.api.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    Button btn_Search_ct;
    EditText txt_MaHD;
    Context context;
    List<TheoDoi> lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context = this;

        loadControl();

        final ListView lst_ctvanchuyen = findViewById(R.id.lst_ctvanchuyen);
        btn_Search_ct.setOnClickListener(v -> {
            if(!txt_MaHD.getText().toString().isEmpty()) {
                APIService.API_SERVICE.getCTVanChuyen(txt_MaHD.getText().toString().trim()).enqueue(new Callback<List<CTVanChuyen>>() {
                    @Override
                    public void onResponse(Call<List<CTVanChuyen>> call, Response<List<CTVanChuyen>> response) {
                        if (lst == null) {
                            lst = new ArrayList<>();
                        } else {
                            lst.clear();
                        }
                        if (!response.body().isEmpty()) {
                            for (int i = response.body().size() - 1; i >= 0; i--) {
                                CTVanChuyen e = response.body().get(i);
                                if (e.getNgayXuatKho() != null)
                                    lst.add(new TheoDoi(e.getNgayXuatKho(), true, e.getTenNK()));
                                lst.add(new TheoDoi(e.getNgayNhapKho(), false, e.getTenNK()));
                            }
                            lst_ctvanchuyen.setAdapter(new CTHDAdapter(context, lst));
                        } else {
                            if (lst == null) {
                                lst = new ArrayList<>();
                            } else {
                                lst.clear();
                            }
                            Toast.makeText(context, "Mã hóa đơn không tồn tại", Toast.LENGTH_SHORT).show();
                            lst_ctvanchuyen.setAdapter(new CTHDAdapter(context, lst));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CTVanChuyen>> call, Throwable t) {

                    }
                });
            }
            else
            {
                Toast.makeText(context, "Vui lòng nhập mã hóa đơn", Toast.LENGTH_SHORT).show();
                if(lst!=null)
                {
                    lst.clear();
                    lst_ctvanchuyen.setAdapter(new CTHDAdapter(context, lst));
                }
            }
        });

    }

    void loadControl(){
        txt_MaHD=findViewById(R.id.txt_MaHD);
        btn_Search_ct=findViewById(R.id.btn_search_ct);

    }


}

