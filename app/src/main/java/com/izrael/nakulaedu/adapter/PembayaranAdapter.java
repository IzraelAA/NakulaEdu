package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.DetailRaportActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.Pembayaran;
import com.izrael.nakulaedu.model.Pembayaran;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PembayaranAdapter extends RecyclerView.Adapter<PembayaranAdapter.ViewHolder> {
    List<Pembayaran> list;
    Context context;
    SessionManager sessionManager;
    ApiInterface mApiInterface;
    public PembayaranAdapter(Context contextt, List<Pembayaran> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public PembayaranAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tagihan,parent,false);
        return new PembayaranAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PembayaranAdapter.ViewHolder holder, int position) {
        final Pembayaran nilai = list.get(position);
        holder.spp.setText( "Spp");
        holder.tanggal.setText( "Tanggal "+nilai.getTanggalPembayaran());
        holder.bulan.setText( "Bulan "+nilai.getBulanPembayaran());
        holder.jumlah.setText( "Rp."+nilai.getJumlahPembayaran()+",-");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView spp,tanggal,jumlah,bulan;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spp = itemView.findViewById(R.id.spp);
            tanggal = itemView.findViewById(R.id.tanggal);
            jumlah = itemView.findViewById(R.id.jumlah);
            bulan = itemView.findViewById(R.id.bulan);
        }
    }
}
