package com.example.giaohangchatluong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {

    ListView lst_ctvanchuyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        loadControl();
    }

    void loadControl(){
        lst_ctvanchuyen=findViewById(R.id.lst_ctvanchuyen);
    }


}

