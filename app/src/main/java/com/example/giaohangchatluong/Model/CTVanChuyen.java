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

    public Date getNgayNhapKho() {
        //return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(NgayNhapKho);
        return NgayNhapKho;
    }

    public void setNgayNhapKho(Date ngayNhapKho) {
        NgayNhapKho = ngayNhapKho;
    }

    public Date getNgayXuatKho() {
        return NgayNhapKho;
        //return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(NgayXuatKho);
    }

    public void setNgayXuatKho(Date ngayXuatKho) {
        NgayXuatKho = ngayXuatKho;
    }
}
