package com.example.giaohangchatluong;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giaohangchatluong.Model.CTHD;
import com.example.giaohangchatluong.Model.CTVanChuyen;
import com.example.giaohangchatluong.Model.HoaDon;
import com.example.giaohangchatluong.Model.TheoDoi;

import java.util.List;

public class CTHDAdapter extends BaseAdapter {

    private List<TheoDoi> lstItem;
    private LayoutInflater inflater;
    private Context context;


    public CTHDAdapter (Context aContext, List<TheoDoi> listData){
        this.context=aContext;
        this.lstItem=listData;
        inflater= LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return lstItem.size();
    }

    @Override
    public Object getItem(int position) {
        return lstItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_cthd_layout, null);
            holder = new ViewHolder();
            holder.time = (TextView) convertView.findViewById(R.id.txtDateTimeCTHD);
            holder.mess = (TextView) convertView.findViewById(R.id.txtStatusCTHD);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TheoDoi theoDoi = this.lstItem.get(position);

        try{
            holder.time.setText(theoDoi.getTime());
        }catch (NullPointerException e){
            holder.time.setText("null");
        }

        if(!theoDoi.isSukien()){
            holder.mess.setText("Đơn hàng đã đến kho "+ theoDoi.getTenNK());
        }
        else holder.mess.setText("Đơn hàng đã xuất kho " +theoDoi.getTenNK());

        convertView.setEnabled(false);
        return convertView;
    }
    static class ViewHolder{
        TextView time;
        TextView mess;

    }


}
