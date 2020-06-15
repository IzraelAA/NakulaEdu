package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.NilaiAdapter;
import com.izrael.nakulaedu.adapter.iddatanilaiadapter;
import com.izrael.nakulaedu.classmodel.Idnilai;
import com.izrael.nakulaedu.model.DefaultResponse;
import com.izrael.nakulaedu.model.Iddatanilai;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNilaiActivity extends AppCompatActivity {
    Integer iddatanilai;
    private iddatanilaiadapter nilaiAdapter;
    private List<Iddatanilai>  results;
    String namapelajaran, nilaipelajaran, namaguru;
    TextView nilai, npelajaran, guru;
    ShimmerFrameLayout shimmerFrameLayout;
    ApiInterface   mApiInterface;
    SessionManager session;
    RecyclerView   recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nilai);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
        recyclerView = findViewById(R.id.detailrecyler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailNilaiActivity.this));
        Intent intent = getIntent();
        iddatanilai = intent.getIntExtra("idnilai", 0);
        nilaipelajaran = intent.getStringExtra("namanilai");
        namapelajaran = intent.getStringExtra("namapelajaran");
        namaguru = intent.getStringExtra("guru");
        guru = findViewById(R.id.namaguru);
        nilai = findViewById(R.id.namanilai);
        session = new SessionManager(DetailNilaiActivity.this);
        npelajaran = findViewById(R.id.namapelajaran);
        guru.setText(namaguru);
        nilai.setText(nilaipelajaran);
        npelajaran.setText(namapelajaran);
        results = new ArrayList<>();
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        send_login();
    }

    private void send_login() {

        Call<Idnilai> uploadGambar = mApiInterface.nilai(iddatanilai, Integer.parseInt(session.get_ID_SISWA()));
        uploadGambar.enqueue(new Callback<Idnilai>() {
            @Override
            public void onResponse(Call<Idnilai> call, Response<Idnilai> response) {
                assert response.body() != null;

                Log.d("2", "onResponse: " + response.body());
                results.addAll(response.body().getData());

                nilaiAdapter = new iddatanilaiadapter(DetailNilaiActivity.this, results);
shimmerFrameLayout.setVisibility(View.GONE);
shimmerFrameLayout.stopShimmerAnimation();
                recyclerView.setAdapter(nilaiAdapter);

            }

            @Override
            public void onFailure(Call<Idnilai> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
}
