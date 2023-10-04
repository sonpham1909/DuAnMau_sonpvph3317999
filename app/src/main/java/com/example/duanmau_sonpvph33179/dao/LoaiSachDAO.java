package com.example.duanmau_sonpvph33179.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_sonpvph33179.database.dbhelper;
import com.example.duanmau_sonpvph33179.model.LoaiSach;


import java.util.ArrayList;

public class LoaiSachDAO {

    dbhelper dbhelper;
    public LoaiSachDAO(Context context){
        dbhelper = new dbhelper(context);

    }
    //Lấy toàn bộ đầu sách có ở trong thư viên
    public ArrayList<LoaiSach> getDSDauSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select*from loaisach",null);
            if(cursor.getCount()!=0){
                cursor.moveToFirst();
                do{
                    list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
                }while(cursor.moveToNext());

            }
        }catch (Exception ex){
            Log.i(TAG,"Lỗi",ex);
        }

        return list;

    }
    public boolean insert(LoaiSach ls){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

       values.put("tenloai",ls.getTenloai());
        long row = db.insert("loaisach",null,values);
        return (row>0);




    }
    public  boolean update (LoaiSach ls){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("maloai",ls.getMaloai());
        values.put("tenloai",ls.getTenloai());
        long row = db.update("loaisach",values,"maloai=?",new String[]{String.valueOf(ls.getMaloai())});

        return (row>0);
    }
    public boolean delete (int maloai){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long row = db.delete("loaisach","maloai=?",new String[]{String.valueOf(maloai)});

        return (row>0);



    }




}
