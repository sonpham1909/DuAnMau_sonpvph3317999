package com.example.duanmau_sonpvph33179.model;

public class PhieuMuon {
    private int mapm,matv;
    private String matt;
    private int masach;
    private String ngay;
    private int trasach;
    private float tienthue;

    public PhieuMuon() {
    }

    public PhieuMuon(int mapm, int matv, String matt, int masach, String ngay, int trasach, float tienthue) {
        this.mapm = mapm;
        this.matv = matv;
        this.matt = matt;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public float getTienthue() {
        return tienthue;
    }

    public void setTienthue(float tienthue) {
        this.tienthue = tienthue;
    }

    public PhieuMuon(int matv, String matt, int masach, String ngay, int trasach, float tienthue) {
        this.matv = matv;
        this.matt = matt;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
    }
}
