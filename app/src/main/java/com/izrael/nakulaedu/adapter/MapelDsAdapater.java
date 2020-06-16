package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.MapelResult;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MapelDsAdapater extends RecyclerView.Adapter<MapelDsAdapater.ViewHolder> {
    List<MapelResult> list;
    Context           context;

    public MapelDsAdapater(Context contextt, List<MapelResult> list) {
        this.context = contextt;
        this.list = list;
    }

    @NonNull
    @Override
    public MapelDsAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.reclerviewmapel, parent, false);
        return new MapelDsAdapater.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MapelDsAdapater.ViewHolder holder, int position) {
        final MapelResult MapelResult = list.get(position);
        Log.d(TAG, "onBindViewHolder: " + MapelResult.getNamaPelajaran());
        holder.guru.setText(MapelResult.getNamaPelajaran());
        holder.nama.setText(MapelResult.getNamaGuru());
        holder.deskripsi.setText(MapelResult.getDeskripsi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Sedag dalam proses", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() < 3) {
            return list.size();
        } else {
            return 3;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView foto;
        TextView guru, nama,deskripsi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.gurufoto);
            guru = itemView.findViewById(R.id.namaguru);
            nama = itemView.findViewById(R.id.mapel);
            deskripsi = itemView.findViewById(R.id.deskripsi);
        }
    }
}
