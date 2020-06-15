package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.IdQuizActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.DataQuizUjian;
import com.izrael.nakulaedu.model.Quiz;

import java.util.List;

public class IdDataQuizAdapter extends RecyclerView.Adapter<IdDataQuizAdapter.ViewHolder> {
    List<Quiz> list;
    Context    context;
    int        list1 ;

    public IdDataQuizAdapter(Context contextt, List<Quiz> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public IdDataQuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.iddataquiz,parent,false);
        return new IdDataQuizAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final IdDataQuizAdapter.ViewHolder holder, int position) {
        final Quiz quizUjian = list.get(position);
        holder.namaquiz.setText(quizUjian.getQuiz());
        holder.nomer.setText(quizUjian.getNomer().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView guru,nomer,namaquiz;
        RadioButton  a,b,c,d,e;
        LinearLayout background;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaquiz = itemView.findViewById(R.id.namaquiz);
            nomer = itemView.findViewById(R.id.nomer2);
        }

    }
}
