package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.PelajaranActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.classmodel.Idnilai;
import com.izrael.nakulaedu.model.Iddatanilai;
import com.izrael.nakulaedu.model.MapelResult;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class iddatanilaiadapter extends RecyclerView.Adapter<iddatanilaiadapter.ViewHolder> {
    List<Iddatanilai> list;
    Context       context;
    public iddatanilaiadapter(Context contextt, List<Iddatanilai> list) {
        this.context = contextt;
        this.list = list;
    }

    @NonNull
    @Override
    public iddatanilaiadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.idnilaiitem,parent,false);
        return new iddatanilaiadapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final iddatanilaiadapter.ViewHolder holder, int position) {
        final Iddatanilai MapelResult = list.get(position);
        holder.mapel.setText(MapelResult.getNilai());
        Log.d(TAG, "onBindViewHolder: " + MapelResult.getNilai().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mapel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mapel = itemView.findViewById(R.id.nilaidetail);
        }
    }
}
