package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
 
import com.izrael.nakulaedu.adapter.MapelAdapter;
import com.izrael.nakulaedu.classmodel.MapelClass;
import com.izrael.nakulaedu.model.GetTahun;
import com.izrael.nakulaedu.classmodel.Result;
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
    private List<Result> results;
    private MapelAdapter nilaiAdapter;
    ApiInterface   mApiInterface;
    SessionManager sessionManager;
    String stahun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);
        results = new ArrayList<>();
        recyclerView = findViewById(R.id.recylerviewmapel);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MapelActivity.this));
        sessionManager = new SessionManager(MapelActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
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
    private void ApiJadwal() {
        Call<MapelClass> uploadGambar = mApiInterface.mapel(sessionManager.get_NISN(), sessionManager.get_KODEKELAS(), "2",stahun);
        uploadGambar.enqueue(new Callback<MapelClass>() {
            @Override
            public void onResponse(Call<MapelClass> call, Response<MapelClass> response) {
                assert response.body() != null;

                results.addAll(response.body().getResult());

                nilaiAdapter = new MapelAdapter(MapelActivity.this, results);

                recyclerView.setAdapter(nilaiAdapter);

                Log.e("TAG", "onkkoResponse: " + response.body().getResult());
                Log.d("", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<MapelClass> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });

    }
}
