package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.izrael.nakulaedu.adapter.NotifikasiAdpater;
import com.izrael.nakulaedu.adapter.PembayaranAdapter;
import com.izrael.nakulaedu.classmodel.DataNotifikasi;
import com.izrael.nakulaedu.classmodel.DataPembayaran;
import com.izrael.nakulaedu.model.Pembayaran;
import com.izrael.nakulaedu.model.notifikasi;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifikasiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<notifikasi> results;
    private NotifikasiAdpater nilaiAdapter;
    String     name;
    ApiInterface   mApiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);
        recyclerView = findViewById(R.id.notifikasi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(NotifikasiActivity.this));
        results = new ArrayList<>();
        sessionManager = new SessionManager(NotifikasiActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ApiJadwal();
    }

    private void ApiJadwal(){
        Call<DataNotifikasi> uploadGambar = mApiInterface.notifikasi( sessionManager.get_KODEKELAS());
        uploadGambar.enqueue(new Callback<DataNotifikasi>() {
            @Override
            public void onResponse(Call<DataNotifikasi> call, Response<DataNotifikasi> response) {
                assert response.body() != null;

                results.addAll(response.body().getData());

                nilaiAdapter = new NotifikasiAdpater(NotifikasiActivity.this,results);

                recyclerView.setAdapter(nilaiAdapter);
            }
            @Override
            public void onFailure(Call<DataNotifikasi> call, Throwable t) {

                Log.e("TAG", "kkonResponse: "+t);
                Log.d("", "onResponse: "+t);
            }
        });

    }

}
