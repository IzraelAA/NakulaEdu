package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.classmodel.Result;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

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
//        Toast.makeText(context,result.getNamamatapelajaran(),Toast.LENGTH_LONG).show();
        Log.d(TAG, "onBindViewHolder: "+result.getNamamatapelajaran());
        if (result.getNamamatapelajaran().equals("Jaringan Komputer")){
            holder.mapel.setImageResource(R.drawable.agamahindu);
        }else if (result.getNamamatapelajaran().equals("Web Design")) {
            holder.mapel.setImageResource(R.drawable.agamakristen);
        }else {
            holder.mapel.setImageResource(R.drawable.bahasainggris);
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
