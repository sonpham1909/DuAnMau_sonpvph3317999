package com.example.duanmau_sonpvph33179.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.adapter.thanhvienadapter;
import com.example.duanmau_sonpvph33179.dao.ThanhVienDAO;
import com.example.duanmau_sonpvph33179.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frg_qltv extends Fragment {


    RecyclerView rcv;
    FloatingActionButton flt;
    ThanhVienDAO dao;
    ThanhVien tv;
    thanhvienadapter adapter;
    private ArrayList<ThanhVien> list = new ArrayList<>();


    public frg_qltv() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_frg_qltv, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvtv);
        flt = view.findViewById(R.id.fltbtntv);

        dao = new ThanhVienDAO(getActivity());

        list= dao.getDSTV();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(manager);
        adapter = new thanhvienadapter(getActivity(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();
            }
        });





        return view;
    }

    public void them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.them_thanhvien,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();


        EditText edttenThanhVien = view.findViewById(R.id.edttentv);
        EditText editns = view.findViewById(R.id.edtnam);

        Button btnsm = view.findViewById(R.id.btnsavetv);
        btnsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edttenThanhVien.getText().toString();
                String nam = editns.getText().toString();

                tv = new ThanhVien(ten,nam);

                if (dao.insert(tv)){

                    list.clear();
                    list.addAll(dao.getDSTV());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Đã thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });




    }
}