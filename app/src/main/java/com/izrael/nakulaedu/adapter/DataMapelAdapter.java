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

import com.izrael.nakulaedu.DetailMapelActivity;
import com.izrael.nakulaedu.IdQuizActivity;
import com.izrael.nakulaedu.PelajaranActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.DataDetail;
import com.izrael.nakulaedu.model.DataQuizUjian;

import java.util.List;

public class DataMapelAdapter extends RecyclerView.Adapter<DataMapelAdapter.ViewHolder> {
    List<DataDetail> list;
    Context context;
    int                 list1 ;

    public DataMapelAdapter(Context contextt, List<DataDetail> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public DataMapelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerviewmapeldata,parent,false);
        return new DataMapelAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataMapelAdapter.ViewHolder holder, int position) {
        final DataDetail quizUjian = list.get(position);
        holder.type.setText("Pdf");
        holder.nama.setText(quizUjian.getNamaMateri());
        holder.masuk.setText(quizUjian.getJamMulai());
        holder.keluar.setText(quizUjian.getJamSelesai());
        holder.hari.setText(quizUjian.getHari());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cek = String.valueOf(quizUjian.getFile());
                Intent intent = new Intent(holder.itemView.getContext(), PelajaranActivity.class);
                intent.putExtra("file",cek);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView masuk,keluar,hari,type,nama;
        LinearLayout background;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            masuk = itemView.findViewById(R.id.masuk);
            keluar = itemView.findViewById(R.id.keluar);
            hari = itemView.findViewById(R.id.hari);
            type = itemView.findViewById(R.id.type);
        }

    }
}
