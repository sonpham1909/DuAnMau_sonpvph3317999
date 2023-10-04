package com.example.duanmau_sonpvph33179.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_sonpvph33179.database.dbhelper;
import com.example.duanmau_sonpvph33179.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDAO {

    dbhelper dbhelper;
    public ThanhVienDAO(Context context){
        dbhelper = new dbhelper(context);

    }
    public ArrayList<ThanhVien> getDSTV(){
        ArrayList<ThanhVien> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from thanhvien",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public boolean insert(ThanhVien tv){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("hoten",tv.getHoTen());
        values.put("namsinh",tv.getNamSinh());

        long row = db.insert("thanhvien",null,values);
        return (row>0);




    }
    public boolean update(ThanhVien tv){
        SQLiteDatabase db=  dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("hoten",tv.getHoTen());
        values.put("namsinh",tv.getNamSinh());
        long row = db.update("thanhvien",values,"matv=?",new String[]{String.valueOf(tv.getMatv())});
        return (row>0);





    }
    public boolean delete(int matv){
        SQLiteDatabase db=  dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        long row = db.delete("thanhvien","matv=?",new String[]{String.valueOf(matv)});
        return (row>0);



    }
}
