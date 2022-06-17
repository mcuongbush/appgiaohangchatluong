package com.example.giaohangchatluong.Model;

import java.util.Date;

public class CTHD {

    String SoHD;
    String SoPGH;
    String MaKH;
    String TenKN;
    String NoiDen;
    String NoiGui;
    Date NgayGui;
    String TongTien;
    String TrangThai;

    public CTHD(String soHD, String soPGH, String maKH, String tenKN, String noiDen, String noiGui, Date ngayGui, String tongTien, String trangThai) {
        SoHD = soHD;
        SoPGH = soPGH;
        MaKH = maKH;
        TenKN = tenKN;
        NoiDen = noiDen;
        NoiGui = noiGui;
        NgayGui = ngayGui;
        TongTien = tongTien;
        TrangThai = trangThai;
    }


    public void setSoHD(String soHD) {
        SoHD = soHD;
    }

    public String getSoPGH() {
        return SoPGH;
    }

    public void setSoPGH(String soPGH) {
        SoPGH = soPGH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTenKN() {
        return TenKN;
    }

    public void setTenKN(String tenKN) {
        TenKN = tenKN;
    }

    public String getNoiDen() {
        return NoiDen;
    }

    public void setNoiDen(String noiDen) {
        NoiDen = noiDen;
    }

    public String getNoiGui() {
        return NoiGui;
    }

    public void setNoiGui(String noiGui) {
        NoiGui = noiGui;
    }

    public Date getNgayGui() {
        return NgayGui;
    }

    public void setNgayGui(Date ngayGui) {
        NgayGui = ngayGui;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
