package com.example.giaohangchatluong.Model;

public class KhachHang {
    String MaKH;
    String TenKH;
    String DiaChi;
    String SDT;
    String GioiTinh;


    public KhachHang( String tenKH,String SDT , String gioiTinh, String diaChi) {

        TenKH = tenKH;
        DiaChi = diaChi;
        this.SDT = SDT;
        GioiTinh = gioiTinh;
    }

    public KhachHang( String MaKH,String tenKH,String SDT , String gioiTinh, String diaChi) {
        this.MaKH=MaKH;
        TenKH = tenKH;
        DiaChi = diaChi;
        this.SDT = SDT;
        GioiTinh = gioiTinh;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
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

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }
}
