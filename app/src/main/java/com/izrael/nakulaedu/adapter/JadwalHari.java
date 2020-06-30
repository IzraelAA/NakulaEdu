package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.JadwalResult;

import java.util.List;

public class JadwalHari extends RecyclerView.Adapter<JadwalHari.ViewHolder> {
    List<JadwalResult> list;
    Context context;
    int list1 ;

    public JadwalHari(Context contextt, List<JadwalResult> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public JadwalHari.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.jadwalhari,parent,false);
        return new JadwalHari.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalHari.ViewHolder holder, int position) {
        final JadwalResult jadwal = list.get(position);

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
            mulai = itemView.findViewById(R.id.mulai);
            keluar = itemView.findViewById(R.id.selesai);
            guru = itemView.findViewById(R.id.guru);
            mapel = itemView.findViewById(R.id.mapel);
        }
    }
}
