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
    int test;
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
        holder.nilai.setText( nilai.getNamaNilai());
        holder.guru.setText( nilai.getNamaGuru());
        holder.pelajaraan.setText( nilai.getNamaPelajaran());
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(holder.itemView.getContext());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), DetailNilaiActivity.class);
//
//                intent.putExtra("namanilai",  nilai.getNamaNilai());
//                intent.putExtra("namapelajaran",  nilai.getNamaPelajaran());
//                intent.putExtra("guru",  nilai.getNamaGuru());
//                holder.itemView.getContext().startActivity(intent);
//                         }
//        });
        test = nilai.getIdDatanilai();
        ApiList(holder,test);
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
    private void ApiList(@NonNull final NilaiAdapter.ViewHolder holder, final int datanilai)  {
        results = null;
        nilaiAdapter = null;
        results = new ArrayList<>();
        results.clear();
        Call<Idnilai> uploadGambar = mApiInterface.nilai(test,Integer.parseInt(sessionManager.get_ID_SISWA()));
        uploadGambar.enqueue(new Callback<Idnilai>() {
            @Override
            public void onResponse(Call<Idnilai> call, Response<Idnilai> response) {
                assert response.body() != null;

                if (response.code() == 200 ){

                    results.clear();
                    results.addAll(response.body().getData());
                    nilaiAdapter = new iddatanilaiadapter(holder.itemView.getContext(),results);

                    holder.recyclerView.setAdapter(nilaiAdapter);
                    test = 0;
                }

            }

            @Override
            public void onFailure(Call<Idnilai> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
}
