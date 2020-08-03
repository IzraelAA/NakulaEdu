package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.izrael.nakulaedu.adapter.DataMapelAdapter;
import com.izrael.nakulaedu.adapter.MapelAdapter;
import com.izrael.nakulaedu.classmodel.DetialMapel;
import com.izrael.nakulaedu.classmodel.MapelClass;
import com.izrael.nakulaedu.model.DataDetail;
import com.izrael.nakulaedu.model.MapelResult;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMapelActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String kode;
    private List<DataDetail> results;
    private DataMapelAdapter nilaiAdapter;
    ApiInterface mApiInterface;
    SessionManager sessionManager;
    ImageView kosong;
    TextView kosong2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mapel);
        recyclerView = findViewById(R.id.materirecylerview);
        recyclerView.setHasFixedSize(true);
        Intent i = getIntent();
        kode = i.getStringExtra("idmapel");
        kosong = findViewById(R.id.kosong);
        kosong2 = findViewById(R.id.kosong2);
        results = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailMapelActivity.this));
        sessionManager = new SessionManager(DetailMapelActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ApiJadwal();
    }

    private void ApiJadwal() {
        Call<DetialMapel> uploadGambar = mApiInterface.materi(kode);
        uploadGambar.enqueue(new Callback<DetialMapel>() {
            @Override
            public void onResponse(Call<DetialMapel> call, Response<DetialMapel> response) {
                assert response.body() != null;
                if (response.code() == 200) {

                    results.addAll(response.body().getData());
                    if (results.size() > 0) {

                        kosong.setVisibility(View.GONE);
                        kosong2.setVisibility(View.GONE);
                    }
                    nilaiAdapter = new DataMapelAdapter(DetailMapelActivity.this, results);

                    recyclerView.setAdapter(nilaiAdapter);

                } else {
                }


            }

            @Override
            public void onFailure(Call<DetialMapel> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });

    }
}
