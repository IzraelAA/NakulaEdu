package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.Pembayaran;
import com.izrael.nakulaedu.model.notifikasi;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotifikasiAdpater  extends RecyclerView.Adapter<NotifikasiAdpater.ViewHolder> {
    List<notifikasi> list;
    Context context;
    SessionManager sessionManager;
    ApiInterface mApiInterface;
    public NotifikasiAdpater(Context contextt, List<notifikasi> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public NotifikasiAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notifikasi,parent,false);
        return new NotifikasiAdpater.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotifikasiAdpater.ViewHolder holder, int position) {
        final notifikasi nilai = list.get(position);
        holder.deskripsi.setText( "Dari "+nilai.getTanggalMulai()+" Sampai "+nilai.getTanggalSelesai());
        holder.tanggal.setText(  nilai.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView deskripsi,tanggal,jumlah,bulan;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            tanggal = itemView.findViewById(R.id.tanggal);
        }
    }
}
