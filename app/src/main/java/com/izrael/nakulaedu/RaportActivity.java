package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.JadwalPelajaraan;
import com.izrael.nakulaedu.adapter.RaportAdapter;
import com.izrael.nakulaedu.classmodel.Raport;
import com.izrael.nakulaedu.model.DataRaport;
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

public class RaportActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<DataRaport> results;
    private RaportAdapter nilaiAdapter;
    ApiInterface mApiInterface;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport);
        recyclerView = findViewById(R.id.reclerviewraport);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RaportActivity.this));
        results = new ArrayList<>();
        sessionManager = new SessionManager(RaportActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        getdata();
    }

    private void getdata() {
        Call<Raport> uploadGambar = mApiInterface.raport(Integer.parseInt(sessionManager.get_ID_SISWA()));
        uploadGambar.enqueue(new Callback<Raport>() {
            @Override
            public void onResponse(Call<Raport> call, Response<Raport> response) {
                assert response.body() != null;
                if (response.code() == 200) {
                    results.addAll(response.body().getData());

                    nilaiAdapter = new RaportAdapter(RaportActivity.this, results);

                    recyclerView.setAdapter(nilaiAdapter);
                }
            }

            @Override
            public void onFailure(Call<Raport> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });

    }
}
