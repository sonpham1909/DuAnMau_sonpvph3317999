package com.example.duanmau_sonpvph33179.model;

public class Sach {
    private int masach;
    private String tensach;
    private float giathue;
    private int maloai;
    private int soluong;

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Sach(int masach, String tensach, int soluong) {
        this.masach = masach;
        this.tensach = tensach;
        this.soluong = soluong;
    }

    public Sach() {
    }

    public Sach(int masach, String tensach, float giathue, int maloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
    }

    public int getMasach() {
        return masach;
    }

    public Sach(String tensach, float giathue, int maloai) {
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public float getGiathue() {
        return giathue;
    }

    public void setGiathue(float giathue) {
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
