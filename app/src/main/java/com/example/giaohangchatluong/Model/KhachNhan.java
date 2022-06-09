package com.example.giaohangchatluong.Model;

public class KhachNhan {

    String MaKN;
    String TenKN;
    String DiaChi;
    String SDT;
    String GioiTinh;

    public KhachNhan( String tenKN,String SDT , String diaChi) {

        TenKN = tenKN;
        DiaChi = diaChi;
        this.SDT = SDT;
    }

    public String getMaKN() {
        return MaKN;
    }

    public void setMaKN(String maKH) {
        MaKN = maKH;
    }

    public String getTenKN() {
        return TenKN;
    }

    public void setTenKN(String tenKH) {
        TenKN = tenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }
}
