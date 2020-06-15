package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.JadwalPelajaraan;
import com.izrael.nakulaedu.classmodel.Jadwal;
import com.izrael.nakulaedu.model.GetJadwal;
import com.izrael.nakulaedu.model.GetTahun;
import com.izrael.nakulaedu.model.JadwalResult;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalPelajaran extends AppCompatActivity {
    private List<JadwalResult> results;
    private JadwalPelajaraan nilaiAdapter;
    String[]       name;
    String[]       guru;
    String[]       nilai;
    ApiInterface   mApiInterface;
    RecyclerView   recyclerView;
    SessionManager sessionManager;
    ShimmerFrameLayout shimmerFrameLayout;
    String stahun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_pelajaran);
        recyclerView = findViewById(R.id.reclerviewjadwal);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(JadwalPelajaran.this));
        results = new ArrayList<>();
        sessionManager = new SessionManager(JadwalPelajaran.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ApiJadwal();
//        ListAdd();
    }

    private void ApiJadwal(){
        Call<GetJadwal> uploadGambar = mApiInterface.jadwal(Integer.parseInt(sessionManager.get_KODEKELAS()));
        uploadGambar.enqueue(new Callback<GetJadwal>() {
            @Override
            public void onResponse(Call<GetJadwal> call, Response<GetJadwal> response) {
                assert response.body() != null;

                results.addAll(response.body().getData());

                nilaiAdapter = new JadwalPelajaraan(JadwalPelajaran.this,results);

                shimmerFrameLayout.setVisibility(View.GONE);
                shimmerFrameLayout.stopShimmerAnimation();
                recyclerView.setAdapter(nilaiAdapter);

                Log.e("TAG", "onkkoResponse: "+response.body().getData());
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