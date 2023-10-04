package com.example.duanmau_sonpvph33179;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_sonpvph33179.dao.ThuThuDao;

public class DangNhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        EditText edtuser = findViewById(R.id.edtusername);
        EditText edtpass = findViewById(R.id.edtpassword);
        Button btnlogin = findViewById(R.id.btnlogin);
        ThuThuDao dao = new ThuThuDao(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtuser.getText().toString();
                String pass = edtpass.getText().toString();
                if (dao.checkDN(user,pass)){
                    //lưu sharedpreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("Thongtin",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt",user);
                    editor.commit();

                    startActivity(new Intent(DangNhap.this,MainActivity.class));

                }else{
                    Toast.makeText(DangNhap.this, "User name hoặc mk không đúng", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}