package com.example.giaohangchatluong.Model;

import java.time.LocalDate;
import java.util.Date;

public class PhieuGuiHang {
    public PhieuGuiHang(String ngayLap, String COD, String maKH, String maLVC, String maKN) {
//        SoPGH = soPGH;
        NgayGui = ngayLap;
        this.COD = COD;
        MaKH = maKH;
        MaLVC = maLVC;
        MaKN = maKN;
    }
    public PhieuGuiHang(){}
    public String SoPGH;
    public String NgayGui;

    public String getSoPGH() {
        return SoPGH;
    }

    public void setSoPGH(String soPGH) {
        SoPGH = soPGH;
    }

    public String getNgayGui() {
        return NgayGui;
    }

    public void setNgayGui(String ngayLap) {
        NgayGui = ngayLap;
    }

    public String isCOD() {
        return COD;
    }

    public void setCOD(String COD) {
        this.COD = COD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getMaLVC() {
        return MaLVC;
    }

    public void setMaLVC(String maLVC) {
        MaLVC = maLVC;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getMaKN() {
        return MaKN;
    }

    public void setMaKN(String maKN) {
        MaKN = maKN;
    }


    public String COD;
    public String MaKH;
    public String MaLVC;
    public String MaNV;
    public String MaKN;
}
