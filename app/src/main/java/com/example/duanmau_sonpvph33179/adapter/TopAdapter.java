package com.example.duanmau_sonpvph33179.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.model.Sach;
import com.example.duanmau_sonpvph33179.model.Top;

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.Viewholder> {

    private Context context;
    private ArrayList<Sach> list;

    public TopAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.top_item,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txtloai.setText("Mã sách: "+String.valueOf(list.get(position).getMasach()));
        holder.txtten.setText("Tên sách: "+list.get(position).getTensach());
        holder.txtsl.setText("Số lượng: "+String.valueOf(list.get(position).getSoluong()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
TextView txtloai,txtten,txtsl;

    public Viewholder(@NonNull View itemView) {
        super(itemView);
        txtloai = itemView.findViewById(R.id.txtmaSach);
        txtten = itemView.findViewById(R.id.txttenSach);
        txtsl = itemView.findViewById(R.id.txtSL);
    }
}


}
