package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.izrael.nakulaedu.adapter.DataQuizAdapter;
import com.izrael.nakulaedu.adapter.VideoAdapter;
import com.izrael.nakulaedu.classmodel.DataQuiz;
import com.izrael.nakulaedu.model.DataQuizUjian;
import com.izrael.nakulaedu.model.VideioData;
import com.izrael.nakulaedu.model.video;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiInterface mApiInterface;
    SessionManager session;
    private List<VideioData> results;
    private VideoAdapter nilaiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        recyclerView = findViewById(R.id.reclerviewvideo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(VideoActivity.this,2));
        results = new ArrayList<>();
        session = new SessionManager(VideoActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ApiHari();
    }
    private void ApiHari() {
        Call<video> uploadGambar = mApiInterface.video(Integer.parseInt(session.get_KODEKELAS()));
        uploadGambar.enqueue(new Callback<video>() {
            @Override
            public void onResponse(Call<video> call, Response<video> response) {
                assert response.body() != null;
                results.addAll(response.body().getData());

                nilaiAdapter = new VideoAdapter(VideoActivity.this,results);

                recyclerView.setAdapter(nilaiAdapter);

            }

            @Override
            public void onFailure(Call<video> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });

    }
}