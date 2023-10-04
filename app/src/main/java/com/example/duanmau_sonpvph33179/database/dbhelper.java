package com.example.duanmau_sonpvph33179.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbhelper extends SQLiteOpenHelper {

    public dbhelper(@Nullable Context context) {
        super(context, "DANGKYMONHOC", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbThuThu = "Create table thuthu(matt text primary key, hoten text, matkhau text)";
        sqLiteDatabase.execSQL(dbThuThu);
        String dbThanhvien = "Create table thanhvien(matv integer primary key autoincrement,hoten text, namsinh text)";
        sqLiteDatabase.execSQL(dbThanhvien);
        String dbLoaiSach = "Create table loaisach(maloai integer primary key autoincrement,tenloai text)";
        sqLiteDatabase.execSQL(dbLoaiSach);
        String dbSach = "Create table sach(masach integer primary key autoincrement, tensach text, giathue float,maloai integer references loaisach(maloai))";
        sqLiteDatabase.execSQL(dbSach);
        String dbPhieuMuon = "Create table phieumuon(mapm integer primary key autoincrement,matv integer references thanhvien(matv),matt text references thuthu(matt),masach integer references sach(masach),ngay text, trasach integer, tienthue float )";
        sqLiteDatabase.execSQL(dbPhieuMuon);

        //data mẫu

        sqLiteDatabase.execSQL("insert into loaisach values(1,'Sách thiếu nhi'),(2,'Sách tình cảm'),(3,'Sách giáo khoa')");
        sqLiteDatabase.execSQL("insert into sach values(1,'Hãy đợi đấy',2500,1),(2,'Thằng cuội',1000,1),(3,'Lập trình Android',2000,3)");
        sqLiteDatabase.execSQL("insert into thuthu values('thuthu01','Nguyễn Văn An','abc123'),('thuthu02','Phạm Văn B','abc124')");
        sqLiteDatabase.execSQL("insert into thanhvien values(1,'Phạm Thị Linh','2004'),(2,'Nguyễn Văn An','2003')");
        sqLiteDatabase.execSQL("insert into phieumuon values(1,1,'thuthu01',1,'2023/09/29',1,30000.0)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i!= i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS thuthu");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS thanhvien");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS loaisach");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS sach");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS phieumuon");
            onCreate(sqLiteDatabase);



        }

    }
}
