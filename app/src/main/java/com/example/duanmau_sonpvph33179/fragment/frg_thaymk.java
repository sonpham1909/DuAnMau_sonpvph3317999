package com.example.duanmau_sonpvph33179.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.dao.ThuThuDao;
import com.example.duanmau_sonpvph33179.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class frg_thaymk extends Fragment {
    TextInputEditText edtoldpass,edtnewpass,edtrepass;
    Button btnsave,btncc;
    ThuThuDao dao;


    public frg_thaymk() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_thaymk, container, false);
        edtoldpass = view.findViewById(R.id.oldpass);
        edtnewpass = view.findViewById(R.id.newpass);

        edtrepass = view.findViewById(R.id.repass);
        btnsave = view.findViewById(R.id.btnsavett);
        btncc = view.findViewById(R.id.btncctt);
        dao = new ThuThuDao(getActivity());

        btncc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtoldpass.setText("");
                edtnewpass.setText("");
                edtrepass.setText("");

            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("Thongtin", MODE_PRIVATE);
                String user = pref.getString("matt","");
                if (validate()>0){
                    ThuThu tt = dao.getID(user);
                    tt.setMatkhau(edtnewpass.getText().toString());
                    dao.update(tt);
                    if(dao.update(tt)>0){
                        Toast.makeText(getContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                        edtoldpass.setText("");
                        edtnewpass.setText("");
                        edtrepass.setText("");
                    }else{
                        Toast.makeText(getContext(), "Không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });




        // Inflate the layout for this fragment
        return view;
    }


    public int validate(){

        int check = 1;
        if(edtoldpass.getText().length() == 0||edtnewpass.getText().length() == 0||edtrepass.getText().length() == 0){

            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }else{

            SharedPreferences pref = getActivity().getSharedPreferences("Thongtin", MODE_PRIVATE);
            String passold = pref.getString("matkhau","");
            String pass = edtnewpass.getText().toString();
            String repas = edtrepass.getText().toString();
            if(!passold.equals(edtoldpass.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(!pass.equals(repas)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp ", Toast.LENGTH_SHORT).show();
                check = -1;
            }



        }



        return check;
    }

}