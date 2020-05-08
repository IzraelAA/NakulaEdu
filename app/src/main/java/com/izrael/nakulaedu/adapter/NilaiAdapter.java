package com.izrael.nakulaedu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.classmodel.Nilai;
import com.izrael.nakulaedu.classmodel.QuizUjian;
import com.izrael.nakulaedu.classmodel.UjianResult;
import com.izrael.nakulaedu.fragment.User;

import java.util.List;
import android.widget.*;
import de.hdodenhof.circleimageview.CircleImageView;

public class NilaiAdapter extends RecyclerView.Adapter<NilaiAdapter.ViewHolder> {
    List<UjianResult> list;
    Context           context;


    public NilaiAdapter(Context contextt, List<UjianResult> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public NilaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nilaiitem,parent,false);
        return new NilaiAdapter.ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final NilaiAdapter.ViewHolder holder, int position) {
        final UjianResult nilai = list.get(position);
        holder.imageView.setImageResource(R.drawable.username);
        holder.nilai.setText(       "Nilai      : "+nilai.getNilai());
        holder.guru.setText(        "Guru       : "+nilai.getKeterangan());
        holder.pelajaraan.setText(  "Pelajaraan : "+nilai.getKeterangan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Belum bisa melihat detail" + nilai.getNilai(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView guru,nilai,pelajaraan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photonilai);
            guru = itemView.findViewById(R.id.guru);
            nilai = itemView.findViewById(R.id.nilai1);
            pelajaraan = itemView.findViewById(R.id.pelajaran);
        }
    }
}
