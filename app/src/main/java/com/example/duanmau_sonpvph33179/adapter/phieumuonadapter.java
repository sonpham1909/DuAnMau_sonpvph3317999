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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_sonpvph33179.R;
import com.example.duanmau_sonpvph33179.dao.PhieuMuonDao;
import com.example.duanmau_sonpvph33179.model.PhieuMuon;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class phieumuonadapter extends RecyclerView.Adapter<phieumuonadapter.Viewholder> {

    private final Context context;
    private final ArrayList<PhieuMuon> list;
    PhieuMuonDao dao;

    public phieumuonadapter(Context context, ArrayList<PhieuMuon> list) {
        this.context = context;
        this.list = list;
        dao = new PhieuMuonDao(context);
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_qlpm,null);
        return new Viewholder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txtmapm.setText(String.valueOf(list.get(position).getMapm()));
        holder.txtmatv.setText(String.valueOf(list.get(position).getMatv()));
        holder.txtmatt.setText(list.get(position).getMatt());
        holder.txtmasach.setText(String.valueOf(list.get(position).getMasach()));
        holder.txtngay.setText(list.get(position).getNgay());

        if(list.get(position).getTrasach()==0){
            holder.txttrasach.setText("Chưa trả");

        }else{

            holder.txttrasach.setText("Đã trả");

        }
        holder.txttienthue.setText(String.valueOf(list.get(position).getTienthue()));
        PhieuMuon pm = list.get(position);
        holder.immgdelpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có muốn xóa?");
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dao.delete(pm.getMapm())){
                            list.clear();
                            list.addAll(dao.getDSPM());
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
        holder.imgeditpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(pm);

            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView txtmapm,txtmatv,txtmatt,txtmasach,txtngay,txttrasach,txttienthue;
        ImageButton immgdelpm,imgeditpm;

        public Viewholder(@NonNull View itemView) {
            super(itemView);


            txtmapm = itemView.findViewById(R.id.txtmapm);
            txtmatv = itemView.findViewById(R.id.txtmatv);
            txtmatt = itemView.findViewById(R.id.txtmatt);
            txtmasach = itemView.findViewById(R.id.txtmassach);
            txtngay = itemView.findViewById(R.id.txtngay);
            txttrasach = itemView.findViewById(R.id.txttrasach);
            txttienthue = itemView.findViewById(R.id.txttienthue);
            immgdelpm = itemView.findViewById(R.id.btndel);
            imgeditpm = itemView.findViewById(R.id.btnsuapm);


        }
    }
    public  void update(PhieuMuon pm){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.ud_pm,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtmatv = view.findViewById(R.id.edtmatv);
        EditText edtmatt = view.findViewById(R.id.edtmatt);
        EditText edtmasach = view.findViewById(R.id.edtmasach);
        EditText edtngay = view.findViewById(R.id.edtngay);
        RadioGroup rdg1 = view.findViewById(R.id.RDG1);
        RadioButton rdodatra = view.findViewById(R.id.datra);
        RadioButton rdochuatra = view.findViewById(R.id.chuatra);
        EditText edttienthue = view.findViewById(R.id.edttienthue);
        Button btnsavepm = view.findViewById(R.id.btnudpm);
        Button btnccpm = view.findViewById(R.id.btnccudpm);


        edtmatv.setText(String.valueOf(pm.getMatv()));
        edtmatt.setText(pm.getMatt());
        edtmasach.setText((String.valueOf(pm.getMasach())));
        edtngay.setText(pm.getNgay());
        if(pm.getTrasach()==1){
            rdodatra.setChecked(true);
        }else{
            rdochuatra.setChecked(true);
        }
        edttienthue.setText(String.valueOf(pm.getTienthue()));

        btnccpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnsavepm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pm.setMatv(Integer.parseInt(edtmatv.getText().toString()));
                pm.setMatt(edtmatt.getText().toString());
                pm.setMasach(Integer.parseInt(edtmasach.getText().toString()));
                pm.setNgay(edtngay.getText().toString());
                if(rdodatra.isChecked()){
                    pm.setTrasach(1);
                }else{
                    pm.setTrasach(0);
                }
                pm.setTienthue(Float.parseFloat(edttienthue.getText().toString()));
                if (dao.update(pm)){
                    list.clear();
                    list.addAll(dao.getDSPM());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã sửa", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {

                    Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();
                }



            }
        });









    }
}
