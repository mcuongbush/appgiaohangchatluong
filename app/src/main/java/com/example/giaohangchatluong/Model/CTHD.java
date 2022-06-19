package com.example.giaohangchatluong.Model;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class CTHD {

    String SoHD;
    String SoPGH;
    String TenKH;
    String TenKN;
    String NoiDen;
    String NoiGui;
    Date NgayGui;
    long TongTien;
    String TrangThai;

    public CTHD( String soPGH, Date ngayGui,String tenKH,String noiGui,  String noiDen,String tenKN,   long tongTien, String trangThai) {

        SoPGH = soPGH;
        TenKH = TenKH;
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

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String maKH) {
        TenKH = maKH;
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

    public String getNgayGui() {

        return DateFormat.getDateInstance(DateFormat.SHORT).format(NgayGui);
    }

    public void setNgayGui(Date ngayGui) {
        NgayGui = ngayGui;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long tongTien) {
        TongTien = tongTien;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
