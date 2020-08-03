package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.IdQuizActivity;
import com.izrael.nakulaedu.PgActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.DataQuizUjian;

import java.util.List;

public class DataQuizAdapter extends RecyclerView.Adapter<DataQuizAdapter.ViewHolder> {
    List<DataQuizUjian> list;
    Context             context;
    int                 list1 ;
    String check;

    public DataQuizAdapter(Context contextt, List<DataQuizUjian> list,String check) {
        this.context = contextt;
        this.list = list;
        this.check = check;

    }

    @NonNull
    @Override
    public DataQuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (check.equals("home")){
            View v = LayoutInflater.from(context).inflate(R.layout.dataquizhome,parent,false);
            return new DataQuizAdapter.ViewHolder(v);
        }else {
            View v = LayoutInflater.from(context).inflate(R.layout.dataquiz,parent,false);
            return new DataQuizAdapter.ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final DataQuizAdapter.ViewHolder holder, int position) {
        final DataQuizUjian quizUjian = list.get(position);
        holder.guru.setText(quizUjian.getNamaGuru());
        holder.mapel.setText(quizUjian.getNamaPelajaran());
        holder.tugasharian.setText(quizUjian.getNamaQuiz());
        if (quizUjian.getType().equals("Essai")){

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
        }else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(holder.itemView.getContext(), PgActivity.class);
                    intent.putExtra("IdDataquiz",quizUjian.getIdDataquiz());
                    intent.putExtra("nomer",1);
                    intent.putExtra("jumlah",quizUjian.getJumlah());
                    intent.putExtra("pelajaran",quizUjian.getNamaPelajaran());
                    intent.putExtra("namaquiz",quizUjian.getNamaQuiz());
                    intent.putExtra("gamabar",quizUjian.getPicture());
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
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
