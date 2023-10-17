package com.example.duanmau_sonpvph33179;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_sonpvph33179.dao.ThuThuDao;

public class DangNhap extends AppCompatActivity {
    CheckBox chkremember;
    EditText edtuser;
    EditText edtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtuser = findViewById(R.id.edtusername);
         edtpass = findViewById(R.id.edtpassword);
        Button btnlogin = findViewById(R.id.btnlogin);
        chkremember = findViewById(R.id.remember);
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
                    editor.putString("matkhau",pass);
                    editor.putBoolean("save",chkremember.isChecked());

                    editor.commit();

                    startActivity(new Intent(DangNhap.this,MainActivity.class));

                }else{
                    Toast.makeText(DangNhap.this, "User name hoặc mk không đúng", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("Thongtin",MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt","");
        String pass = sharedPreferences.getString("matkhau","");
        boolean save = sharedPreferences.getBoolean("save",false);
        if (save==true){
            edtuser.setText(matt);
            edtpass.setText(pass);
            chkremember.setChecked(true);
        }
    }
}