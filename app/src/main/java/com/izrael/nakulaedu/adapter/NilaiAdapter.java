package com.izrael.nakulaedu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.DetailNilaiActivity;
import com.izrael.nakulaedu.NilaiActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.classmodel.Idnilai;
import com.izrael.nakulaedu.classmodel.UjianResult;
import com.izrael.nakulaedu.fragment.User;
import com.izrael.nakulaedu.model.Iddatanilai;
import com.izrael.nakulaedu.model.MapelResult;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.model.Quiz;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;
import android.widget.*;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NilaiAdapter extends RecyclerView.Adapter<NilaiAdapter.ViewHolder> {
    List<Nilai>    list;
    Context        context;
    SessionManager sessionManager;
    private iddatanilaiadapter nilaiAdapter;
    private List<Iddatanilai> results;
    ApiInterface mApiInterface;
    public NilaiAdapter(Context contextt, List<Nilai> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public NilaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nilaiitem,parent,false);
        return new NilaiAdapter.ViewHolder(v);
    }
 
    @Override
    public void onBindViewHolder(@NonNull final NilaiAdapter.ViewHolder holder, int position) {
        final Nilai nilai = list.get(position);
        results = new ArrayList<>();
        holder.nilai.setText( nilai.getNamaNilai());
        holder.guru.setText( nilai.getNamaGuru());
        holder.pelajaraan.setText( nilai.getNamaPelajaran());
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(holder.itemView.getContext());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), DetailNilaiActivity.class);
//                intent.putExtra("idnilai",  nilai.getIdDatanilai());
//                intent.putExtra("namanilai",  nilai.getNamaNilai());
//                intent.putExtra("namapelajaran",  nilai.getNamaPelajaran());
//                intent.putExtra("guru",  nilai.getNamaGuru());
//                holder.itemView.getContext().startActivity(intent);
//                         }
//        });
        ApiList(holder,nilai.getIdDatanilai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView guru,nilai,pelajaraan;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guru = itemView.findViewById(R.id.guru);
            recyclerView = itemView.findViewById(R.id.iddatanilai);
            nilai = itemView.findViewById(R.id.nilai1);
            pelajaraan = itemView.findViewById(R.id.pelajaran);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
    private void ApiList(@NonNull final NilaiAdapter.ViewHolder holder,int datanilai)  {

        Call<Idnilai> uploadGambar = mApiInterface.nilai(datanilai,Integer.parseInt(sessionManager.get_ID_SISWA()));
        uploadGambar.enqueue(new Callback<Idnilai>() {
            @Override
            public void onResponse(Call<Idnilai> call, Response<Idnilai> response) {
                assert response.body() != null;

                results.addAll(response.body().getData());

                Log.d("2", "onResponse: " + results.get(0).getNilai());
                nilaiAdapter = new iddatanilaiadapter(holder.itemView.getContext(),results);

                Log.d("2", "onResponse: " +nilaiAdapter);
                holder.recyclerView.setAdapter(nilaiAdapter);

            }

            @Override
            public void onFailure(Call<Idnilai> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
}
