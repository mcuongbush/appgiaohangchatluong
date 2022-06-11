package com.example.giaohangchatluong.Model;

import java.util.Date;

public class PhieuYeuCau {
    String SoPYC;
    Date NgayLap;
    String MaKH;
    String MaKN;
    String MaLVC;
    long ThanhToan;
    float KhoiLuong;

    public String getSoPYC() {
        return SoPYC;
    }

    public void setSoPYC(String soPYC) {
        SoPYC = soPYC;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date ngayLap) {
        NgayLap = ngayLap;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getMaKN() {
        return MaKN;
    }

    public void setMaKN(String maKN) {
        MaKN = maKN;
    }

    public String getMaLVC() {
        return MaLVC;
    }

    public void setMaLVC(String maLVC) {
        MaLVC = maLVC;
    }

    public long getThanhToan() {
        return ThanhToan;
    }

    public void setThanhToan(long thanhToan) {
        ThanhToan = thanhToan;
    }

    public float getKhoiLuong() {
        return KhoiLuong;
    }

    public void setKhoiLuong(float khoiLuong) {
        KhoiLuong = khoiLuong;
    }

    public PhieuYeuCau(Date NgayLap, String MaKH, float khoiluong, String MaKN, String MaLVC, long Thanhtoan)
    {
        this.NgayLap=NgayLap;
        this.MaKH=MaKH;
        this.KhoiLuong=khoiluong;
        this.MaKN=MaKN;
        this.MaLVC=MaLVC;
        this.ThanhToan=Thanhtoan;
    }
}
