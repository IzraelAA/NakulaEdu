package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
        if (jadwal.getHari().equals("Senin")){

        }
        holder.hari.setText(jadwal.getHari());
        holder.hari.setText(jadwal.getJamMulai()+jadwal.getJamSelesai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hari,pelajaran,jam;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hari = itemView.findViewById(R.id.hari);
            recyclerView = itemView.findViewById(R.id.reclerviewjadwal);
        }
    }
}
