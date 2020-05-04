package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.classmodel.Result;

import java.util.List;

public class MapelAdapter extends RecyclerView.Adapter<MapelAdapter.ViewHolder> {
    List<Result> list;
    Context context;
    int list1 ;

    public MapelAdapter(Context contextt, List<Result> list) {
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
    public void onBindViewHolder(@NonNull MapelAdapter.ViewHolder holder, int position) {
        Result result = list.get(position);
        if (result.getNamamatapelajaran().equals("Teknik komputer Jaringan")){
            holder.mapel.setImageResource(R.drawable.agamahindu);
        }if (result.getNamamatapelajaran().equals("Web Desaign")) {
            holder.mapel.setImageResource(R.drawable.agamahindu);
        }
        else {
            holder.mapel.setImageResource(R.drawable.agamakristen);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  mapel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mapel = itemView.findViewById(R.id.mapelgambar);
        }
    }
}
