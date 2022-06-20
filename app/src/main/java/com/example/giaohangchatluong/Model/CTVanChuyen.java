package com.example.giaohangchatluong.Model;

import java.text.DateFormat;
import java.util.Date;

public class CTVanChuyen {
    String TenNK;
    Date NgayNhapKho;
    Date NgayXuatKho;

    public String getTenNK() {
        return TenNK;
    }

    public void setTenNK(String tenNK) {
        TenNK = tenNK;
    }

    public String getNgayNhapKho() {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(NgayNhapKho);
    }

    public void setNgayNhapKho(Date ngayNhapKho) {
        NgayNhapKho = ngayNhapKho;
    }

    public String getNgayXuatKho() {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(NgayXuatKho);
    }

    public void setNgayXuatKho(Date ngayXuatKho) {
        NgayXuatKho = ngayXuatKho;
    }
}
