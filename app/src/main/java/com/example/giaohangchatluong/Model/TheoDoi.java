package com.example.giaohangchatluong.Model;

import java.text.DateFormat;
import java.util.Date;

public class TheoDoi {

    public String getTime() {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(Time);
    }

    public void setTime(Date time) {
        Time = time;
    }

    public boolean isSukien() {
        return sukien;
    }

    public void setSukien(boolean sukien) {
        this.sukien = sukien;
    }

    public TheoDoi(Date time, boolean sukien, String tenNK) {
        Time = time;
        this.sukien = sukien;
        TenNK = tenNK;
    }
    public TheoDoi (){

    }

    private Date Time;
    private boolean sukien;

    public String getTenNK() {
        return TenNK;
    }

    public void setTenNK(String tenNK) {
        TenNK = tenNK;
    }

    private String TenNK;
}
