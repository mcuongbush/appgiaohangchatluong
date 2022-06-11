package com.example.giaohangchatluong.Model;

public class TaiKhoanKH {

    private String TenTK;
    private String MatKhau;

    public TaiKhoanKH(String tenTK, String matKhau, String maKH) {
        TenTK = tenTK;
        MatKhau = matKhau;
        MaKH = maKH;
    }

    private String MaKH;



    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }
}
