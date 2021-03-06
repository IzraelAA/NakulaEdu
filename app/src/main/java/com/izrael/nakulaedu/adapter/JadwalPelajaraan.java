package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.Jadwal;
import com.izrael.nakulaedu.model.JadwalResult;

import android.widget.*;

import java.util.List;

public class JadwalPelajaraan extends RecyclerView.Adapter<JadwalPelajaraan.ViewHolder> {
    List<JadwalResult> list;
    Context      context;
    int list1 ;

    public JadwalPelajaraan(Context contextt, List<JadwalResult> list) {
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
        final JadwalResult jadwal = list.get(position);
        
        holder.hari.setText(jadwal.getHari());
        holder.mapel.setText("Mata pelajaran : "+jadwal.getNamaPelajaran());
        holder.mulai.setText("Jam mulai         : "+jadwal.getMasuk() );
        holder.keluar.setText("Jam Selesai      : "+jadwal.getKeluar() );

            holder.guru.setText("Guru                   : "+jadwal.getNamaGuru());

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
