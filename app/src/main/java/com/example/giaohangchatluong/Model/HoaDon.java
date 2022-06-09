package com.example.giaohangchatluong.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

public class HoaDon {

    private String SoHD;
    private long TongTien;
    private Date NgayLapHD;
    boolean TrangThai;

    public void setNgayLapHD(Date ngayLapHD) {
        NgayLapHD = ngayLapHD;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public HoaDon(String SoHD, Date NgaylapHD, long TongTien, Boolean  status)
    {
        this.SoHD=SoHD;
        this.TongTien=TongTien;
        this.NgayLapHD=NgaylapHD;
        this.TrangThai=status;
    }
    public String getSoHD() {
        return SoHD;
    }

    public void setSoHD(String maHD) {
        SoHD = maHD;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long tongTien) {
        TongTien = tongTien;
    }

    public String getNgayLapHD() {

        return DateFormat.getDateInstance(DateFormat.SHORT).format(NgayLapHD);
    }

}
