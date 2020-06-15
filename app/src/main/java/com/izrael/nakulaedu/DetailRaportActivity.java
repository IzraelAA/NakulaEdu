package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.DataRaportAdapter;
import com.izrael.nakulaedu.adapter.RaportAdapter;
import com.izrael.nakulaedu.classmodel.IdRaport;
import com.izrael.nakulaedu.classmodel.Raport;
import com.izrael.nakulaedu.model.DataRaport;
import com.izrael.nakulaedu.model.IdDataRaport;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRaportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<IdDataRaport> results;
    private DataRaportAdapter      nilaiAdapter;
    String[]           name;
    String[]           guru;
    String[]           nilai;
    ApiInterface       mApiInterface;
    SessionManager     sessionManager;
    ShimmerFrameLayout shimmerFrameLayout;
    String             stahun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_raport);
        recyclerView = findViewById(R.id.reclerviewnilairaport);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailRaportActivity.this));
        results = new ArrayList<>();
        sessionManager = new SessionManager(DetailRaportActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        getdata();
    }

    private void getdata() {
        Call<IdRaport> uploadGambar = mApiInterface.detailraport(1);
        uploadGambar.enqueue(new Callback<IdRaport>() {
            @Override
            public void onResponse(Call<IdRaport> call, Response<IdRaport> response) {
                assert response.body() != null;

                results.addAll(response.body().getData());

                nilaiAdapter = new DataRaportAdapter(DetailRaportActivity.this,results);

                recyclerView.setAdapter(nilaiAdapter);

                Log.e("TAG", "onkkoResponse: "+response.body());
                Log.d("22", "onResponse: "+response.body());
            }
            @Override
            public void onFailure(Call<IdRaport> call, Throwable t) {

                Log.e("TAG", "kkonResponse: "+t);
                Log.d("", "onResponse: "+t);
            }
        });

    }
}
