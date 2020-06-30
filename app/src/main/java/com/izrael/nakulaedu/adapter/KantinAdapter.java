package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.JadwalResult;
import com.izrael.nakulaedu.model.kantin;

import java.util.List;

public class KantinAdapter extends RecyclerView.Adapter<KantinAdapter.ViewHolder> {
    List<kantin> list;
    Context context;
    int list1;

    public KantinAdapter(Context contextt, List<kantin> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.kantin, parent, false);
        return new KantinAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KantinAdapter.ViewHolder holder, int position) {
        final kantin jadwal = list.get(position);

        holder.nama.setText(jadwal.getNamaBarang());
        holder.harga.setText("harga : " + jadwal.getHarga());
        holder.deskripsi.setText("Deskripsi : " + jadwal.getDeskripsi());
        Glide.with(holder.itemView.getContext()).load("https://testing.nakula.co.id/assets/foto/" + jadwal.getFile()).into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, harga, deskripsi;
        ImageView foto;
        LinearLayout background;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            harga = itemView.findViewById(R.id.harga);
            nama = itemView.findViewById(R.id.namabarang);
            foto = itemView.findViewById(R.id.foto);
        }
    }
}
