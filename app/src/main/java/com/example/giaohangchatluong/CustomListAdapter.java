package com.example.giaohangchatluong;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giaohangchatluong.Model.HoaDon;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<HoaDon> lstItem;
    private LayoutInflater inflater;
    private Context context;

    public CustomListAdapter(Context aContext, List<HoaDon> listData)
    {
        this.context=aContext;
        this.lstItem=listData;
        inflater= LayoutInflater.from(aContext);
    }
    @Override
    public int getCount(){
        return lstItem.size();
    }

    @Override
    public Object getItem(int position){
        return lstItem.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.status = (ImageView) convertView.findViewById(R.id.ic_Status);
            holder.txt_MaHD = (TextView) convertView.findViewById(R.id.txt_SoPGH);
            holder.txt_NgayLap = (TextView) convertView.findViewById(R.id.txt_NgayLap);
            holder.txt_TongTien = (TextView) convertView.findViewById(R.id.txt_TongTien);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HoaDon hd = this.lstItem.get(position);
        holder.txt_MaHD.setText(hd.getSoHD());
        holder.txt_NgayLap.setText(hd.getNgayLapHD());
        holder.txt_TongTien.setText(String.valueOf(hd.getTongTien()));
        String img;
        if(hd.isTrangThai()) img="ic_giaoxong";
        else img="ic_danggiao";
        int imgID = this.getMipmapResIdByName(img);
        holder.status.setImageResource(imgID);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (context,BillInfoMainActivity.class);
                intent.putExtra("SoHD",hd.getSoHD());
                context.startActivity(intent);
                //Toast.makeText(context, "HD: "+ hd.getSoHD(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;

    }
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        //Log.i("GiaoHangChatLuong", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder{
        ImageView status;
        TextView txt_MaHD;
        TextView txt_NgayLap;
        TextView txt_TongTien;

    }
}
