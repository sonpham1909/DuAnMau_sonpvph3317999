package com.example.duanmau_sonpvph33179.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.adapter.sachadapter;
import com.example.duanmau_sonpvph33179.dao.SachDAO;
import com.example.duanmau_sonpvph33179.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frg_sach extends Fragment {
    RecyclerView rcv;
    SachDAO dao;
    Sach sach;
    FloatingActionButton flt;
    sachadapter adapter;
    ArrayList<Sach> list = new ArrayList<>();


    public frg_sach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_frg_sach, container, false);
        // Inflate the layout for this fragment

        rcv = view.findViewById(R.id.rcvsach);
        flt = view.findViewById(R.id.fltbtnsach);
        dao = new SachDAO(getActivity());

        list = dao.getDSDauSach();


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(manager);

        adapter= new sachadapter(getActivity(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}