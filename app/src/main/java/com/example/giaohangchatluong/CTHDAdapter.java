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
        CTHDAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_cthd_layout, null);
            holder = new CTHDAdapter.ViewHolder();
            holder.time = (TextView) convertView.findViewById(R.id.txtDateTimeCTHD);
            holder.mess = (TextView) convertView.findViewById(R.id.txtStatusCTHD);
            convertView.setTag(holder);
        } else {
            holder = (CTHDAdapter.ViewHolder) convertView.getTag();
        }
        TheoDoi theoDoi = this.lstItem.get(position);

        holder.time.setText(theoDoi.getTime());
        if(!theoDoi.isSukien()){
            holder.mess.setText("Đơn hàng đã đến kho "+ theoDoi.getTenNK());
        }
        else holder.mess.setText("Đơn hàng đã xuất kho " +theoDoi.getTenNK());

        //holder.status.setImageResource(imgID);
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent (context, BillInfoActivity.class);
//                context.startActivity(intent);
//                //Toast.makeText(context, "HD: "+ hd.getSoHD(), Toast.LENGTH_SHORT).show();
//            }
//        });
        return convertView;
    }
    static class ViewHolder{
        TextView time;
        TextView mess;

    }


}
