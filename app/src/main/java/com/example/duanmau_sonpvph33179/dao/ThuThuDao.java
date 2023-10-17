package com.example.duanmau_sonpvph33179.dao;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_sonpvph33179.database.dbhelper;
import com.example.duanmau_sonpvph33179.model.Sach;
import com.example.duanmau_sonpvph33179.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDao {
    dbhelper dbhelper;
    private SQLiteDatabase db;

    public ThuThuDao(Context context) {
        dbhelper = new dbhelper(context);
    }


    public boolean checkDN(String matt, String matkhau) {

        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from thuthu where matt=? and matkhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0) {
            return true;

        } else {
            return false;
        }

    }


    public long update(ThuThu tt){

        ContentValues values = new ContentValues();
        db= dbhelper.getWritableDatabase();

        values.put("hoten",tt.getHoten());
        values.put("matkhau",tt.getMatkhau());
        return db.update("thuthu",values,"matt=?",new String[]{tt.getMatt()});
    }

    @SuppressLint("Range")
    public List<ThuThu> getDAta(String sql, String...SelectAGr){
        List<ThuThu> list = new ArrayList<ThuThu>();
        db= dbhelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, SelectAGr);
        while(c.moveToNext()){
            ThuThu tt = new ThuThu();
            tt.setMatt(c.getString(c.getColumnIndex("matt")));
            tt.setHoten(c.getString(c.getColumnIndex("hoten")));
            tt.setMatkhau(c.getString(c.getColumnIndex("matkhau")));
            list.add(tt);

        }

        return list;

    }

    public List<ThuThu> getAll(){

        String sql = "Select*from thuthu";
        return getDAta(sql);
    }

    public ThuThu getID(String id){
        String sql = "Select*from thuthu where matt=?";
        List<ThuThu> list = getDAta(sql,id);
        return list.get(0);
    }





}
