package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.DetailRaportActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.classmodel.Idnilai;
import com.izrael.nakulaedu.model.DataRaport;
import com.izrael.nakulaedu.model.Iddatanilai;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RaportAdapter  extends RecyclerView.Adapter<RaportAdapter.ViewHolder> {
    List<DataRaport> list;
    Context          context;
    SessionManager   sessionManager;
    ApiInterface mApiInterface;
    public RaportAdapter(Context contextt, List<DataRaport> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public RaportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listraport,parent,false);
        return new RaportAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RaportAdapter.ViewHolder holder, int position) {
        final DataRaport nilai = list.get(position);
        holder.nama.setText( nilai.getNamaSiswa());
        holder.sekolah.setText( nilai.getNamaKelas());
        holder.kelas.setText( nilai.getNamaSekolah());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailRaportActivity.class);
                intent.putExtra("hari",nilai.getIdDatarapot().toString());
                holder.itemView.getContext().startActivity(intent);
            }
        });
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView        nama,sekolah,kelas;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gamabar);
            nama = itemView.findViewById(R.id.nama);
            sekolah = itemView.findViewById(R.id.sekolah);
            kelas = itemView.findViewById(R.id.kelas);
        }
    }
}
