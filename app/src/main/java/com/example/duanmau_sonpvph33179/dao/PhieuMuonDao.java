package com.example.duanmau_sonpvph33179.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_sonpvph33179.database.dbhelper;
import com.example.duanmau_sonpvph33179.model.PhieuMuon;
import com.example.duanmau_sonpvph33179.model.Sach;

import java.util.ArrayList;

public class PhieuMuonDao {

    dbhelper dbhelper;
    public  PhieuMuonDao(Context context){
        dbhelper = new dbhelper(context);
    }

    //Lấy toàn bộ đầu sách có ở trong thư viên
    public ArrayList<PhieuMuon> getDSPM(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select*from phieumuon",null);
            if(cursor.getCount()!=0){
                cursor.moveToFirst();
                do{
                    list.add(new PhieuMuon(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getInt(5),cursor.getFloat(6)));
                }while(cursor.moveToNext());

            }
        }catch (Exception ex){
            Log.i(TAG,"Lỗi",ex);
        }

        return list;

    }


    public boolean insert(PhieuMuon pm){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("matv",pm.getMatv());
        values.put("matt",pm.getMatt());
        values.put("masach",pm.getMasach());
        values.put("ngay",pm.getNgay());
        values.put("trasach",pm.getTrasach());
        values.put("tienthue",pm.getTienthue());
        long row = db.insert("phieumuon",null,values);
        return (row>0);




    }
    public  boolean update (PhieuMuon pm){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("matv",pm.getMatv());
        values.put("matt",pm.getMatt());
        values.put("masach",pm.getMasach());
        values.put("ngay",pm.getNgay());
        values.put("trasach",pm.getTrasach());
        values.put("tienthue",pm.getTienthue());
        long row = db.update("phieumuon",values,"mapm=?",new String[]{String.valueOf(pm.getMapm())});

        return (row>0);
    }
    public boolean delete (int mapm){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long row = db.delete("phieumuon","mapm=?",new String[]{String.valueOf(mapm)});

        return (row>0);



    }


}
