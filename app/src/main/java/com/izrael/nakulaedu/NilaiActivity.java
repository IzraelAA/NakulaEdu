package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.izrael.nakulaedu.adapter.MapelAdapter;
import com.izrael.nakulaedu.adapter.NilaiAdapter;
import com.izrael.nakulaedu.classmodel.MapelClass;
import com.izrael.nakulaedu.classmodel.Nilai;
import com.izrael.nakulaedu.classmodel.QuizUjian;
import com.izrael.nakulaedu.classmodel.Result;
import com.izrael.nakulaedu.classmodel.UjianResult;
import com.izrael.nakulaedu.fragment.Chatlist;
import com.izrael.nakulaedu.fragment.UserAdapter;
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
    private List<Nilai>  list;
    private NilaiAdapter nilaiAdapter;
    String[] name;
    String[] guru;
    private List<UjianResult> results;
    ApiInterface   mApiInterface;
    SessionManager sessionManager;
    String[] nilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        recyclerView = findViewById(R.id.nilai);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(NilaiActivity.this));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(NilaiActivity.this);
        results = new ArrayList<>();
        ApiList();
    }

    private void ApiList() {

        Call<QuizUjian> uploadGambar = mApiInterface.quiz(sessionManager.get_NISN() );
        uploadGambar.enqueue(new Callback<QuizUjian>() {
            @Override
            public void onResponse(Call<QuizUjian> call, Response<QuizUjian> response) {
                assert response.body() != null;

                results.addAll(response.body().getResult());

                nilaiAdapter = new NilaiAdapter(NilaiActivity.this,results);

                recyclerView.setAdapter(nilaiAdapter);

                Log.d("", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<QuizUjian> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
//
//    private void AddList() {
//    name = getResources().getStringArray(R.array.data_nama);
//    guru = getResources().getStringArray(R.array.data_guru);
//    nilai = getResources().getStringArray(R.array.data_nilai);
//    }
//
//    private void ListAdd() {
//        for (int i = 0; i < name.length; i++) {
//            Nilai nilai2 = new Nilai();
//            nilai2.setNama(name[i]);
//            nilai2.setNilai(nilai[i]);
//            nilai2.setPengajar(guru[i]);
//            list.add(nilai2);
//        }
//        nilaiAdapter = new NilaiAdapter(NilaiActivity.this,list);
//
//        recyclerView.setAdapter(nilaiAdapter);
//    }



}
