package com.example.duanmau_sonpvph33179.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.adapter.sachadapter;
import com.example.duanmau_sonpvph33179.dao.SachDAO;
import com.example.duanmau_sonpvph33179.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class frg_sach extends Fragment {
    RecyclerView rcv;
    SachDAO dao;
    Sach sach;
    FloatingActionButton flt;
    sachadapter adapter;

    private SearchView sv;
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
        sv = view.findViewById(R.id.sv);

        sv.clearFocus();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        dao = new SachDAO(getActivity());

        list = dao.getDSDauSach();



        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(manager);

        adapter= new sachadapter(getActivity(),list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();





        return view;
    }
    ArrayList listnull = new ArrayList();

    private void filterList(String Text) {
        ArrayList<Sach> lists = new ArrayList<>();
        for(Sach s: list){
            if(s.getTensach().toLowerCase().contains(Text.toLowerCase())){
                lists.add(s);
            }
        }
        if(lists.isEmpty()){


            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.setFilteredList(lists);

        }
    }


}