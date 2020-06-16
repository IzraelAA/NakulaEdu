package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.IdQuizActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.DataQuizUjian;

import java.util.List;

public class DataQuizAdapter extends RecyclerView.Adapter<DataQuizAdapter.ViewHolder> {
    List<DataQuizUjian> list;
    Context             context;
    int                 list1 ;

    public DataQuizAdapter(Context contextt, List<DataQuizUjian> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public DataQuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dataquiz,parent,false);
        return new DataQuizAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataQuizAdapter.ViewHolder holder, int position) {
        final DataQuizUjian quizUjian = list.get(position);
        holder.guru.setText(quizUjian.getNamaGuru());
        holder.mapel.setText(quizUjian.getNamaPelajaran());
        holder.tugasharian.setText(quizUjian.getNamaQuiz());
        holder.tugasharian.setText(quizUjian.getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), IdQuizActivity.class);
                intent.putExtra("IdDataquiz",quizUjian.getIdDataquiz());
                intent.putExtra("guru",quizUjian.getNamaGuru());
                intent.putExtra("pelajaran",quizUjian.getNamaPelajaran());
                intent.putExtra("namaquiz",quizUjian.getNamaQuiz());
                intent.putExtra("gamabar",quizUjian.getPicture());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tugasharian,guru,mapel;
        LinearLayout background;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guru = itemView.findViewById(R.id.guruquiz);
            tugasharian = itemView.findViewById(R.id.tugasharian);
            mapel = itemView.findViewById(R.id.mapel);
        }

    }
}
