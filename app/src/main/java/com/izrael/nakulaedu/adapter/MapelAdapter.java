package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.PelajaranActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.MapelResult;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MapelAdapter extends RecyclerView.Adapter<MapelAdapter.ViewHolder> {
    List<MapelResult> list;
    Context context;

    public MapelAdapter(Context contextt, List<MapelResult> list) {
        this.context = contextt;
        this.list = list;
    }

    @NonNull
    @Override
    public MapelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.reclerviewmapel,parent,false);
        return new MapelAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MapelAdapter.ViewHolder holder, int position) {
        final MapelResult MapelResult = list.get(position);
        Log.d(TAG, "onBindViewHolder: "+MapelResult.getNamaPelajaran());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Sedag dalam proses", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  mapel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
