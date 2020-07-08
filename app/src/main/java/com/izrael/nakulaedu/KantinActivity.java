package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.izrael.nakulaedu.adapter.KantinAdapter;
import com.izrael.nakulaedu.adapter.NotifikasiAdpater;
import com.izrael.nakulaedu.classmodel.DataKantin;
import com.izrael.nakulaedu.classmodel.DataNotifikasi;
import com.izrael.nakulaedu.model.kantin;
import com.izrael.nakulaedu.model.notifikasi;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KantinActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<kantin> results;
    private KantinAdapter nilaiAdapter;
    String     name;
    ApiInterface   mApiInterface;
    SessionManager sessionManager;
    ImageView kosong;
    TextView kosong2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kantin);
        recyclerView = findViewById(R.id.kantin);
        kosong = findViewById(R.id.kosong);
        kosong2 = findViewById(R.id.kosong2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(KantinActivity.this));
        results = new ArrayList<>();
        sessionManager = new SessionManager(KantinActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ApiJadwal();
    }

    private void ApiJadwal(){
        Call<DataKantin> uploadGambar = mApiInterface.kantin( sessionManager.get_IDSEKOLAH());
        uploadGambar.enqueue(new Callback<DataKantin>() {
            @Override
            public void onResponse(Call<DataKantin> call, Response<DataKantin> response) {
                assert response.body() != null;
                if (response.code() == 200){
                results.addAll(response.body().getData());
                if (results.size()>0){

                    kosong.setVisibility(View.GONE);
                    kosong2.setVisibility(View.GONE);
                }
                nilaiAdapter = new KantinAdapter(KantinActivity.this,results);

                recyclerView.setAdapter(nilaiAdapter);
                }
                else {

                }
            }
            @Override
            public void onFailure(Call<DataKantin> call, Throwable t) {

                Log.e("TAG", "kkonResponse: "+t);
                Log.d("", "onResponse: "+t);
            }
        });

    }

}
