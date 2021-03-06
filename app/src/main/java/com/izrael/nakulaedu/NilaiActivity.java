package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.MapelAdapter;
import com.izrael.nakulaedu.adapter.NilaiAdapter;
import com.izrael.nakulaedu.classmodel.DataNilai;
import com.izrael.nakulaedu.classmodel.MapelClass;
import com.izrael.nakulaedu.classmodel.Result;
import com.izrael.nakulaedu.classmodel.UjianResult;
import com.izrael.nakulaedu.fragment.Chatlist;
import com.izrael.nakulaedu.fragment.UserAdapter;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.model.Quiz;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NilaiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NilaiAdapter nilaiAdapter;
    String[] name;
    String[] guru;
    private List<Nilai> results;
    ApiInterface   mApiInterface;
    SessionManager sessionManager;
    ShimmerFrameLayout shimmerFrameLayout;
    String[] nilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        recyclerView = findViewById(R.id.nilai);
        recyclerView.setHasFixedSize(true);
        shimmerFrameLayout = findViewById(R.id.shimmernilai);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
        recyclerView.setLayoutManager(new LinearLayoutManager(NilaiActivity.this));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(NilaiActivity.this);
        results = new ArrayList<>();
        ApiList();
    }

    private void ApiList() {

        Call<DataNilai> uploadGambar = mApiInterface.quiz(Integer.parseInt(sessionManager.get_KODEKELAS()));
        uploadGambar.enqueue(new Callback<DataNilai>() {
            @Override
            public void onResponse(Call<DataNilai> call, Response<DataNilai> response) {
                assert response.body() != null;
        if (response.code() == 200){

            Log.d("2", "onResponse: " + response.body());
            results.addAll(response.body().getData());

            shimmerFrameLayout.setVisibility(View.GONE);
            shimmerFrameLayout.stopShimmerAnimation();
            nilaiAdapter = new NilaiAdapter(NilaiActivity.this,results);

            recyclerView.setAdapter(nilaiAdapter);
        }

            }

            @Override
            public void onFailure(Call<DataNilai> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }


}
