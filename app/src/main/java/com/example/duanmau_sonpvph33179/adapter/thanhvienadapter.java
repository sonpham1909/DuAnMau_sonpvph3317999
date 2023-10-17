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
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(tv);
            }
        });
        holder.imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      if (dao.delete(tv.getMatv())){
                          list.clear();
                          list.addAll(dao.getDSTV());
                          notifyDataSetChanged();
                          Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                      }
                    }
                });
                builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog diaglog = builder.create();
                diaglog.show();
            }
        });



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
            imgdel = itemView.findViewById(R.id.btndeltv);

        }
    }

    public void update(ThanhVien tv){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.ud_tv,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtten = view.findViewById(R.id.edttentvu);
        EditText edtns = view.findViewById(R.id.edtnamu);

        Button btnsm = view.findViewById(R.id.btnsavetvu);


        edtten.setText(tv.getHoTen());
        edtns.setText(tv.getNamSinh());
        btnsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv.setHoTen(edtten.getText().toString());
                tv.setNamSinh(edtns.getText().toString());
                if(dao.update(tv)){
                    list.clear();
                    list.addAll(dao.getDSTV());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã sửa", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }

            }
        });



    }
}
