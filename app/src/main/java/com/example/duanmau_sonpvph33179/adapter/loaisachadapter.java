package com.example.duanmau_sonpvph33179.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.dao.LoaiSachDAO;
import com.example.duanmau_sonpvph33179.model.LoaiSach;

import java.util.ArrayList;

public class loaisachadapter extends RecyclerView.Adapter<loaisachadapter.Viewholder> {
    private final Context context;
    private final ArrayList<LoaiSach> list;
    LoaiSachDAO dao;

    public loaisachadapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
        dao = new LoaiSachDAO(context);
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach,null);



        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txtmaloai.setText(String.valueOf(list.get(position).getMaloai()));
        holder.txttenloai.setText(list.get(position).getTenloai());
        LoaiSach ls = list.get(position);
        holder.imgeditls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(ls);
            }
        });
        holder.immgdells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có muốn xóa?");
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dao.delete(ls.getMaloai())){
                            list.clear();
                            list.addAll(dao.getDSDauSach());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView txtmaloai,txttenloai;
        ImageButton immgdells,imgeditls;

        public Viewholder(@NonNull View itemView) {
            super(itemView);


            txtmaloai = itemView.findViewById(R.id.txtmaloai);
            txttenloai = itemView.findViewById(R.id.txttenloai);

            immgdells = itemView.findViewById(R.id.btndells);
            imgeditls = itemView.findViewById(R.id.btnsuals);


        }
    }
    public void update(LoaiSach ls){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_loaisach,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtten = view.findViewById(R.id.edtten);
        Button btnsave = view.findViewById(R.id.btnsavelsu);


        edtten.setText(ls.getTenloai());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ls.setTenloai(edtten.getText().toString());
                if(dao.update(ls)){
                    list.clear();
                    list.addAll(dao.getDSDauSach());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Đã sưả thành cônng", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    }

