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
import com.example.duanmau_sonpvph33179.adapter.loaisachadapter;
import com.example.duanmau_sonpvph33179.dao.LoaiSachDAO;
import com.example.duanmau_sonpvph33179.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frg_qlls extends Fragment {
    RecyclerView rcvls;
    FloatingActionButton fltbtnls;
    LoaiSachDAO dao;
    loaisachadapter adapter;
    LoaiSach ls;
    ArrayList<LoaiSach> list = new ArrayList<>();


    public frg_qlls() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_frg_qlls, container, false);
        // Inflate the layout for this fragment
        rcvls = view.findViewById(R.id.rcvloai);
        fltbtnls = view.findViewById(R.id.fltbtnloai);
        dao = new LoaiSachDAO(getContext());
        list = dao.getDSDauSach();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcvls.setLayoutManager(manager);
        adapter= new loaisachadapter(getContext(),list);
        rcvls.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fltbtnls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();
            }
        });



        return view;




    }



    public void them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View vew = getLayoutInflater().inflate(R.layout.them_loaisach,null);
        builder.setView(vew);
        Dialog dialog = builder.create();
        dialog.show();


        EditText edtten = vew.findViewById(R.id.edttena);
        Button btnadd = vew.findViewById(R.id.btnsavels);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtten.getText().toString();
             ls = new LoaiSach(ten);
             if(dao.insert(ls)){


                 list.clear();
                 list.addAll(dao.getDSDauSach());
                 adapter.notifyDataSetChanged();
                 Toast.makeText(getActivity(), "Đã thêm", Toast.LENGTH_SHORT).show();
                 dialog.dismiss();
             }
            }
        });


    }
}