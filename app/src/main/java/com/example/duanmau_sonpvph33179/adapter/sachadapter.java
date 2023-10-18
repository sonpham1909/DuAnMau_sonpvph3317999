package com.example.duanmau_sonpvph33179.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.dao.SachDAO;
import com.example.duanmau_sonpvph33179.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class sachadapter extends RecyclerView.Adapter<sachadapter.Viewholder> {

    private Context context;
    private  ArrayList<Sach> list;



    SachDAO dao;

    public sachadapter(Context context, ArrayList<Sach> list ){
        this.context = context;
        this.list = list;
        dao=new SachDAO(context);

    }
    public void setFilteredList(ArrayList<Sach> lists){
        this.list = lists;
        notifyDataSetChanged();
    }


//    public void setFilteredListNull(ArrayList listnull){
//        this.listnull =listnull
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach,null);


        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
////        holder.txtmasach.setText(String.valueOf(list.get(position).getMasach()));
//        holder.txtten.setText(list.get(position).getTensach());
//        holder.txtgia.setText(String.valueOf(list.get(position).getGiathue()));
//        holder.txtmaloai.setText(String.valueOf(list.get(position).getMaloai()));
//        Sach sach = list.get(position);
        holder.txtmasach.setText(String.valueOf(list.get(position).getMasach()));
        holder.txtten.setText(list.get(position).getTensach());
        holder.txtgia.setText(String.valueOf(list.get(position).getGiathue()));
        holder.txtmaloai.setText(String.valueOf(list.get(position).getMaloai()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView txtmasach,txtten,txtgia,txtmaloai;
        ImageButton imgedit,imgdel;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtmasach = itemView.findViewById(R.id.txtmasach1);

            txtten = itemView.findViewById(R.id.txttensach);
            txtgia = itemView.findViewById(R.id.txtgiathue);
            txtmaloai = itemView.findViewById(R.id.txtmaloais);

            imgdel =itemView.findViewById(R.id.btndelsach);
            imgedit =itemView.findViewById(R.id.btnsuasach);



        }
    }



}
