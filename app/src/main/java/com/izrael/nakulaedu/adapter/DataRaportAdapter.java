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
import com.izrael.nakulaedu.classmodel.IdRaport;
import com.izrael.nakulaedu.model.IdDataRaport;
import com.izrael.nakulaedu.model.JadwalResult;

import java.util.List;

public class DataRaportAdapter  extends RecyclerView.Adapter<DataRaportAdapter.ViewHolder> {
    List<IdDataRaport> list;
    Context        context;
    int            list1 ;

    public DataRaportAdapter(Context contextt, List<IdDataRaport> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public DataRaportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nilairaportlist,parent,false);
        return new DataRaportAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataRaportAdapter.ViewHolder holder, int position) {
        final IdDataRaport jadwal = list.get(position);
        holder.mapel.setText(jadwal.getNamaPelajaran());
        holder.tipe.setText(jadwal.getTipe());
        holder.nilai.setText(jadwal.getNilai());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nilai,tipe,mapel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nilai = itemView.findViewById(R.id.nilai);
            tipe = itemView.findViewById(R.id.tipe);
            mapel = itemView.findViewById(R.id.mapel);
        }
    }
}

