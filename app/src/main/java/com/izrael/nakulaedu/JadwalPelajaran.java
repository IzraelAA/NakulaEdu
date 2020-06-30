package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.JadwalHari;
import com.izrael.nakulaedu.model.GetJadwal;
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
    private JadwalHari nilaiAdapter;
    String     name;
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
        Intent i = getIntent();
        name = i.getStringExtra("hari");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(JadwalPelajaran.this));
        results = new ArrayList<>();
        sessionManager = new SessionManager(JadwalPelajaran.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ApiJadwal();
    }

    private void ApiJadwal(){
        Call<GetJadwal> uploadGambar = mApiInterface.jadwalhari(Integer.parseInt(sessionManager.get_KODEKELAS()),name);
        uploadGambar.enqueue(new Callback<GetJadwal>() {
            @Override
            public void onResponse(Call<GetJadwal> call, Response<GetJadwal> response) {
                assert response.body() != null;

                results.addAll(response.body().getData());

                nilaiAdapter = new JadwalHari(JadwalPelajaran.this,results);

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