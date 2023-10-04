package com.example.duanmau_sonpvph33179.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_sonpvph33179.database.dbhelper;

public class ThuThuDao {
    dbhelper dbhelper;
    public  ThuThuDao(Context context){
        dbhelper = new dbhelper(context);
    }
    //đăng nhập
    public boolean checkDN(String matt,String matkhau){

        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from thuthu where matt=? and matkhau = ?",new String[]{matt,matkhau});
        if(cursor.getCount()!=0){
            return true;

        }else{
            return false;
        }

    }
}
