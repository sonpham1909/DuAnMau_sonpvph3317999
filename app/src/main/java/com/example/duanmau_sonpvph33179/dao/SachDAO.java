package com.example.duanmau_sonpvph33179.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_sonpvph33179.database.dbhelper;
import com.example.duanmau_sonpvph33179.model.Sach;

import java.util.ArrayList;

public class SachDAO {

    dbhelper dbhelper;
    public SachDAO(Context context){
        dbhelper = new dbhelper(context);

    }
    //Lấy toàn bộ đầu sách có ở trong thư viên
    public ArrayList<Sach>  getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
       try {
           Cursor cursor = sqLiteDatabase.rawQuery("Select*from sach",null);
           if(cursor.getCount()!=0){
               cursor.moveToFirst();
               do{
                   list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getFloat(2),cursor.getInt(3)));
               }while(cursor.moveToNext());

           }
       }catch (Exception ex){
           Log.i(TAG,"Lỗi",ex);
       }

        return list;

    }


    public boolean insert(Sach s){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tensach",s.getTensach());
        values.put("giathue",s.getGiathue());
        values.put("maloai",s.getMaloai());
        long row = db.insert("sach",null,values);
        return (row>0);




    }
    public  boolean update (Sach s){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tensach",s.getTensach());
        values.put("giathue",s.getGiathue());
        values.put("maloai",s.getMaloai());
        long row = db.update("sach",values,"masach=?",new String[]{String.valueOf(s.getMasach())});

        return (row>0);
    }
    public boolean delete (int masach){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long row = db.delete("sach","masach=?",new String[]{String.valueOf(masach)});

        return (row>0);



    }



}
