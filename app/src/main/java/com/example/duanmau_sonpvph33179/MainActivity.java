package com.example.duanmau_sonpvph33179;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.duanmau_sonpvph33179.dao.SachDAO;
import com.example.duanmau_sonpvph33179.fragment.frg_qlls;
import com.example.duanmau_sonpvph33179.fragment.frg_qlpm;
import com.example.duanmau_sonpvph33179.fragment.frg_qltv;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.Framelayout);
        NavigationView navigationView = findViewById(R.id.navigationview);
        drawerLayout=findViewById(R.id.drawerlayot);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               Fragment fragment;
                fragment = new frg_qlpm();
                replace(fragment);
                if(item.getItemId()==R.id.qlyphieumuon){
                    fragment = new frg_qlpm();
                    replace(fragment);
                }else if(item.getItemId()==R.id.qlyloaisach){
                    fragment = new frg_qlls();
                    replace(fragment);
                }else if(item.getItemId()==R.id.qlythanhvien){
                    fragment = new frg_qltv();
                    replace(fragment);

                }

                return false;
            }
        });


    }
    public void replace(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.Framelayout,fragment).commit();
        drawerLayout.close();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}