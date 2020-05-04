package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.izrael.nakulaedu.adapter.JadwalPelajaraan;
import com.izrael.nakulaedu.adapter.NilaiAdapter;
import com.izrael.nakulaedu.classmodel.Jadwal;
import com.izrael.nakulaedu.classmodel.Nilai;
import com.izrael.nakulaedu.model.GetJadwal;
import com.izrael.nakulaedu.model.GetTahun;
import com.izrael.nakulaedu.model.Result;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.*;

public class JadwalPelajaran extends AppCompatActivity {
    private List<Jadwal> list;
    private List<Result> results;
    private JadwalPelajaraan nilaiAdapter;
    String[]       name;
    String[]       guru;
    String[]       nilai;
    ApiInterface   mApiInterface;
    RecyclerView   recyclerView;
    SessionManager sessionManager;
    String stahun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_pelajaran);
        recyclerView = findViewById(R.id.reclerviewjadwal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(JadwalPelajaran.this));
        results = new ArrayList<>();
        sessionManager = new SessionManager(JadwalPelajaran.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
//        AddList();
//        ListAdd();
        ApiTahun();
    }

    private void ApiTahun(){
        Call<GetTahun> tahun = mApiInterface.api_tahun(sessionManager.get_TAHUN());
        tahun.enqueue(new Callback<GetTahun>() {
            @Override
            public void onResponse(Call<GetTahun> call, Response<GetTahun> response) {

                stahun = response.body().getResult();
                ApiJadwal();
            }

            @Override
            public void onFailure(Call<GetTahun> call, Throwable t) {

            }
        });
    }
    private void ApiJadwal(){
        Call<GetJadwal> uploadGambar = mApiInterface.jadwal(sessionManager.get_NISN(),sessionManager.get_KODEKELAS(),stahun);
        uploadGambar.enqueue(new Callback<GetJadwal>() {
            @Override
            public void onResponse(Call<GetJadwal> call, Response<GetJadwal> response) {
                assert response.body() != null;

                results.addAll(response.body().getResult());

                nilaiAdapter = new JadwalPelajaraan(JadwalPelajaran.this,results);

                recyclerView.setAdapter(nilaiAdapter);

                Log.e("TAG", "onkkoResponse: "+response.body().getResult());
                Log.d("", "onResponse: "+response.body());
            }
            @Override
            public void onFailure(Call<GetJadwal> call, Throwable t) {

                Log.e("TAG", "kkonResponse: "+t);
                Log.d("", "onResponse: "+t);
            }
        });

    }

}