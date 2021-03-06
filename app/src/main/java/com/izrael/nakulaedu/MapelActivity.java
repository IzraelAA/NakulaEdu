package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.MapelAdapter;
import com.izrael.nakulaedu.classmodel.MapelClass;
import com.izrael.nakulaedu.model.GetTahun;
import com.izrael.nakulaedu.classmodel.Result;
import com.izrael.nakulaedu.model.MapelResult;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapelActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<MapelResult> results;
    private MapelAdapter nilaiAdapter;
    ApiInterface mApiInterface;
    SessionManager sessionManager;
    ShimmerFrameLayout shimmerFrameLayout;
    String stahun;

    ImageView kosong;
    TextView kosong2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);
        results = new ArrayList<>();
        kosong = findViewById(R.id.kosong);
        kosong2 = findViewById(R.id.kosong2);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
        recyclerView = findViewById(R.id.recylerviewmapel);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MapelActivity.this));
        sessionManager = new SessionManager(MapelActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ApiJadwal();
    }

    private void ApiJadwal() {
        Call<MapelClass> uploadGambar = mApiInterface.mapel(Integer.parseInt(sessionManager.get_KODEKELAS()));
        uploadGambar.enqueue(new Callback<MapelClass>() {
            @Override
            public void onResponse(Call<MapelClass> call, Response<MapelClass> response) {
                assert response.body() != null;
                if (response.code() == 200) {

                    results.addAll(response.body().getData());

                    shimmerFrameLayout.setVisibility(View.GONE);
                    shimmerFrameLayout.stopShimmerAnimation();
                    nilaiAdapter = new MapelAdapter(MapelActivity.this, results);
                    if (results.size() > 0) {

                        kosong.setVisibility(View.GONE);
                        kosong2.setVisibility(View.GONE);
                    }
                    recyclerView.setAdapter(nilaiAdapter);

                } else {

                    shimmerFrameLayout.setVisibility(View.GONE);
                    shimmerFrameLayout.stopShimmerAnimation();
                }

            }

            @Override
            public void onFailure(Call<MapelClass> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });

    }
}
