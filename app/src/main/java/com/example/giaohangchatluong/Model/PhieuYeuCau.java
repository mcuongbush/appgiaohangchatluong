package com.example.giaohangchatluong.Model;

import java.util.Date;

public class PhieuYeuCau {
    String SoPYC;
    String NgayLap;
    String MaKH;
    String MaKN;
    String MaLVC;
    String COD;
    String TrangThai;
    long ThanhToan;
    double KhoiLuong;

    public String getSoPYC() {
        return SoPYC;
    }

    public void setSoPYC(String soPYC) {
        SoPYC = soPYC;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String ngayLap) {
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

    public double getKhoiLuong() {
        return KhoiLuong;
    }

    public void setKhoiLuong(double khoiLuong) {
        KhoiLuong = khoiLuong;
    }

    public PhieuYeuCau(String NgayLap, String MaKH, double khoiluong, String MaKN, String MaLVC, long Thanhtoan,String COD)
    {
        this.NgayLap=NgayLap;
        this.MaKH=MaKH;
        this.KhoiLuong=khoiluong;
        this.MaKN=MaKN;
        this.MaLVC=MaLVC;
        this.ThanhToan=Thanhtoan;
        this.COD=COD;
    }
}
