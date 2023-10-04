package com.example.duanmau_sonpvph33179.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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





        return view;
    }
}