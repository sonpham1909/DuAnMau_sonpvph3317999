package com.example.duanmau_sonpvph33179;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Manhinhcho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhcho);
        ImageView ivlogo = findViewById(R.id.ivilogo);
        Glide.with(this).load(R.mipmap.bg).into(ivlogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Manhinhcho.this,DangNhap.class);
                startActivity(intent);
            }
        },3000);
    }
}