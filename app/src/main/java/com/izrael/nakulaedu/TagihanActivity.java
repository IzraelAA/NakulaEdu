package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.JadwalHari;
import com.izrael.nakulaedu.adapter.PembayaranAdapter;
import com.izrael.nakulaedu.classmodel.DataPembayaran;
import com.izrael.nakulaedu.model.GetJadwal;
import com.izrael.nakulaedu.model.JadwalResult;
import com.izrael.nakulaedu.model.Pembayaran;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagihanActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Pembayaran> results;
    private PembayaranAdapter nilaiAdapter;
    String     name;
    ApiInterface   mApiInterface;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagihan);
        recyclerView = findViewById(R.id.tagihan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(TagihanActivity.this));
        results = new ArrayList<>();
        sessionManager = new SessionManager(TagihanActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ApiJadwal();
    }
    private void ApiJadwal(){
        Call<DataPembayaran> uploadGambar = mApiInterface.pembayaran( sessionManager.get_ID_SISWA());
        uploadGambar.enqueue(new Callback<DataPembayaran>() {
            @Override
            public void onResponse(Call<DataPembayaran> call, Response<DataPembayaran> response) {
                assert response.body() != null;

                results.addAll(response.body().getData());

                nilaiAdapter = new PembayaranAdapter(TagihanActivity.this,results);

                recyclerView.setAdapter(nilaiAdapter);

                Log.e("TAG", "onkkoResponse: "+response.body().getData());
                Log.d("", "onResponse: "+response.body());
            }
            @Override
            public void onFailure(Call<DataPembayaran> call, Throwable t) {

                Log.e("TAG", "kkonResponse: "+t);
                Log.d("", "onResponse: "+t);
            }
        });

    }

}
