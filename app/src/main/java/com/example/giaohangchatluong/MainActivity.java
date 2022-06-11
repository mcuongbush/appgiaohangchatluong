package com.example.giaohangchatluong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private String MaKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        NavController navController= Navigation.findNavController(this,R.id.NavHostFragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }

    public String getMaKH()
    {
        return getIntent().getStringExtra("MaKH");
    }
    public String getTenTK(){
        return getIntent().getStringExtra("TenTK");
    }
}