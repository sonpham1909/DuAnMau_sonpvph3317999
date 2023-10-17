package com.example.duanmau_sonpvph33179.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_sonpvph33179.database.dbhelper;
import com.example.duanmau_sonpvph33179.model.Sach;

import java.util.ArrayList;

public class ThongkeDao {

    dbhelper dbhelper;
    public ThongkeDao(Context context){
        dbhelper = new dbhelper(context);
    }

    public ArrayList<Sach> getTop10(){

        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT pm.masach,sc.tensach,COUNT(pm.masach)\n" +
                "FROM phieumuon pm,sach sc\n" +
                "WHERE pm.masach = sc.masach\n" +
                "GROUP BY pm.masach,sc.tensach\n" +
                "ORDER BY COUNT(pm.masach) DESC\n" +
                "LIMIT 10",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }

        return list;
    }
}
