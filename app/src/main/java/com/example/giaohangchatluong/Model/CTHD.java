package com.example.giaohangchatluong.Model;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class CTHD {

    String SoHD;
    String SoPGH;
    String TenKH;
    String TenLVC;
    String TenKN;
    String NoiDen;
    String NoiGui;
    Date NgayGui;
    long TongTien;
    String TrangThai;

    public CTHD( String soPGH, Date ngayGui,String tenlvc,String tenKH,String noiGui,  String noiDen,String tenKN,   long tongTien, String trangThai) {

        SoPGH = soPGH;
        TenKH = tenKH;
        TenLVC=tenlvc;
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

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM/dd/yyyy");
        //formatter.parse(NgayGui.toString());
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(NgayGui);
    }

    public void setNgayGui(Date ngayGui) {
        NgayGui = ngayGui;
    }

    public String getTenLVC() {
        return TenLVC;
    }

    public void setTenLVC(String tenLVC) {
        TenLVC = tenLVC;
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
