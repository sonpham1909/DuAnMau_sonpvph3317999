package com.example.duanmau_sonpvph33179.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.adapter.TopAdapter;
import com.example.duanmau_sonpvph33179.dao.ThongkeDao;
import com.example.duanmau_sonpvph33179.model.Sach;

import java.util.ArrayList;
import java.util.List;


public class frg_thongke extends Fragment {

    public frg_thongke() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_frg_thongke2, container, false);
        RecyclerView rcv = view.findViewById(R.id.rcvtk);
        ThongkeDao dao = new ThongkeDao(getContext());
        ArrayList<Sach> list =dao.getTop10();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);
        TopAdapter adapter = new TopAdapter(getContext(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // Inflate the layout for this fragment
        return view;
    }
}