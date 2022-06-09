package com.example.giaohangchatluong.Model;

public class LoaiVanChuyen {
    String MaLVC;
    String TenLVC;
    Long Gia;

    public LoaiVanChuyen (String malvc, String Tenlvc, long gia){
        this.MaLVC=malvc;
        this.TenLVC=Tenlvc;
        this.Gia=gia;
    }

    @Override
    public String toString() {
        return TenLVC;
    }



    public String getMaLVC() {
        return MaLVC;
    }

    public void setMaLVC(String maLVC) {
        MaLVC = maLVC;
    }

    public String getTenLVC() {
        return TenLVC;
    }

    public void setTenLVC(String tenLVC) {
        TenLVC = tenLVC;
    }

    public Long getGia() {
        return Gia;
    }

    public void setGia(Long gia) {
        Gia = gia;
    }
}
