package com.example.giaohangchatluong.Model;

public class LoaiHH {
    private String MaLHH;
    private String TenLHH;

    public LoaiHH(String MaLHH, String TenLHH)
    {
        this.MaLHH=MaLHH;
        this.TenLHH=TenLHH;
    }


    @Override
    public String toString() {
        return this.TenLHH;
    }

    public String getMaLHH() {
        return MaLHH;
    }


    public String getItem(){return MaLHH;}
    public void setMaLHH(String maLHH) {
        MaLHH = maLHH;
    }

    public String getTenLoaiHH() {
        return TenLHH;
    }

    public void setTenLoaiHH(String tenLoaiHH) {
        TenLHH = tenLoaiHH;
    }
}
