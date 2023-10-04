package com.example.duanmau_sonpvph33179.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.dao.ThanhVienDAO;
import com.example.duanmau_sonpvph33179.model.ThanhVien;

import java.util.ArrayList;

public class thanhvienadapter extends RecyclerView.Adapter<thanhvienadapter.Viewholder> {

    private final Context context;
    private final ArrayList<ThanhVien> list;
    ThanhVienDAO dao;

    public thanhvienadapter(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
        dao = new ThanhVienDAO(context);
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien,null);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.txtmatv.setText(String.valueOf(list.get(position).getMatv()));
        holder.txttentv.setText(list.get(position).getHoTen());
        holder.txtnamsinh.setText(list.get(position).getNamSinh());
        ThanhVien tv= list.get(position);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView txtmatv,txttentv,txtnamsinh;
        ImageButton imgedit,imgdel;
        public Viewholder(@NonNull View itemView) {
            super(itemView);




            txtmatv = itemView.findViewById(R.id.txtmatv);
            txttentv = itemView.findViewById(R.id.txttentv);
            txtnamsinh = itemView.findViewById(R.id.txtnamsinh);

            imgedit = itemView.findViewById(R.id.btnsuatv);
            imgdel = itemView.findViewById(R.id.btnsuatv);

        }
    }
}
