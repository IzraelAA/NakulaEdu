package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.classmodel.Jadwal;
import com.izrael.nakulaedu.classmodel.Nilai;
import com.izrael.nakulaedu.model.Result;

import android.widget.*;

import java.util.List;

public class JadwalPelajaraan extends RecyclerView.Adapter<JadwalPelajaraan.ViewHolder> {
    List<Result> list;
    Context      context;
    int list1 ;

    public JadwalPelajaraan(Context contextt, List<Result> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public JadwalPelajaraan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.jadwalpelajaran,parent,false);
        return new JadwalPelajaraan.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalPelajaraan.ViewHolder holder, int position) {
        final Result jadwal = list.get(position);
        int angka = +1;
        if (jadwal.getHari().equals("Senin")){
            holder.background.setBackgroundResource(R.drawable.seninbeckground);
        }
        if (jadwal.getHari().equals("Selasa")){
            holder.background.setBackgroundResource(R.drawable.selasabackground);
        }
        if (jadwal.getHari().equals("Rabu")){
            holder.background.setBackgroundResource(R.drawable.rabubackground);
        }
        if (jadwal.getHari().equals("Kamis")){
            holder.background.setBackgroundResource(R.drawable.kamisbeckground);
        }
        if (jadwal.getHari().equals("Jumat")){
            holder.background.setBackgroundResource(R.drawable.jumatbackground);
        }
        if (jadwal.getHari().equals("Sabtu")){
            holder.background.setBackgroundResource(R.drawable.sabtubackground);
        }
        holder.hari.setText(jadwal.getHari());
        holder.mapel.setText("Mata pelajaran : "+jadwal.getNamamatapelajaran());
        holder.mulai.setText("Jam mulai         : "+jadwal.getJamSelesai() );
        holder.keluar.setText("Jam Selesai      : "+jadwal.getJamMulai() );
        if (jadwal.getNamaGuru().equals("")){
            holder.guru.setText("Guru                   : Nama guru tidak tercantum");
        }else {

            holder.guru.setText("Guru                   : "+jadwal.getNamaGuru());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hari,mulai,keluar,guru,mapel;
        LinearLayout background;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hari = itemView.findViewById(R.id.hari);
            mulai = itemView.findViewById(R.id.mulai);
            keluar = itemView.findViewById(R.id.selesai);
            guru = itemView.findViewById(R.id.guru);
            mapel = itemView.findViewById(R.id.mapel);
            background = itemView.findViewById(R.id.beckground);
        }
    }
}
