package com.example.duanmau_sonpvph33179.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.adapter.phieumuonadapter;
import com.example.duanmau_sonpvph33179.dao.PhieuMuonDao;
import com.example.duanmau_sonpvph33179.dao.SachDAO;
import com.example.duanmau_sonpvph33179.dao.ThanhVienDAO;
import com.example.duanmau_sonpvph33179.model.PhieuMuon;
import com.example.duanmau_sonpvph33179.model.Sach;
import com.example.duanmau_sonpvph33179.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class frg_qlpm extends Fragment {
    PhieuMuonDao dao;
    FloatingActionButton fltadd;
    phieumuonadapter adapter;
    PhieuMuon pm;
    RecyclerView rcView;
    private ArrayList<PhieuMuon> listpm = new ArrayList<PhieuMuon>();

    public frg_qlpm() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_frg_qlpm, container, false);
        // Inflate the layout for this fragment
        RecyclerView rcView = view.findViewById(R.id.rcv);
        FloatingActionButton fltadd = view.findViewById(R.id.fltbtn);

        dao= new PhieuMuonDao(getActivity());
       listpm = dao.getDSPM();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rcView.setLayoutManager(manager);

        adapter = new phieumuonadapter(getActivity(),listpm);
        rcView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });



        return view;
    }
    public  void showdialog(){
        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_pm,null);

        Spinner spnThanhvien = view.findViewById(R.id.spnThanhvien);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        RadioGroup rdg1a = view.findViewById(R.id.RDG1a);
        RadioButton rdodta = view.findViewById(R.id.datraa);
        RadioButton rdocta = view.findViewById(R.id.chuatraa);
        EditText edtTien = view.findViewById(R.id.edttienthuea);
        getDataTV(spnThanhvien);
        getSach(spnSach);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //lấy mã thành viên
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhvien.getSelectedItem();
                int matv = (int) hsTV.get("matv");
                //lấy mã sách
                HashMap<String, Object> hsS = (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsS.get("masach");

                float tien = Float.parseFloat(edtTien.getText().toString());
                int trasach = 0;
                if (rdocta.isChecked()) {
                    trasach = 1;
                } else {
                    trasach =0;
                }

                themPM(matv,masach,trasach,tien);

            }
        });
        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();




    }
    private void getDataTV(Spinner spnThanhvien){
        ThanhVienDAO dao = new ThanhVienDAO(getActivity());
        ArrayList<ThanhVien> list = dao.getDSTV();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (ThanhVien tv : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("matv",tv.getMatv());
            hs.put("hoten", tv.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getActivity(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1}

        );
        spnThanhvien.setAdapter(simpleAdapter);

    }
    private void getSach(Spinner spnSach){
      SachDAO dao = new SachDAO(getActivity());
        ArrayList<Sach> list = dao.getDSDauSach();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Sach s : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("masach",s.getMasach());
            hs.put("tensach", s.getTensach());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getActivity(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1}

        );
        spnSach.setAdapter(simpleAdapter);

    }
    private void themPM(int matv,int masach,int trasach, float tien){
        //lấy mã thủ thư
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Thongtin", Context.MODE_PRIVATE);
        String matt =sharedPreferences.getString("matt","");
        //lấy ngày hện tại
        Date cDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(cDate);

        PhieuMuon phieuMuon = new PhieuMuon(matv,matt,masach,ngay,trasach,tien);
        boolean kiemtra =dao.insert(phieuMuon);
        if (kiemtra){
            listpm.clear();
            listpm.addAll(dao.getDSPM());
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Không thành công", Toast.LENGTH_SHORT).show();
        }


    }
}