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

public class DetailNilaiAdapter  extends RecyclerView.Adapter<DetailNilaiAdapter.ViewHolder> {
    List<JadwalResult> list;
    Context            context;
    int                list1 ;

    public DetailNilaiAdapter(Context contextt, List<JadwalResult> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public DetailNilaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.jadwalpelajaran,parent,false);
        return new DetailNilaiAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailNilaiAdapter.ViewHolder holder, int position) {
        final JadwalResult jadwal = list.get(position);

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
        }
    }
}
