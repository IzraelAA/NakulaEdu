package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.DataQuizAdapter;
import com.izrael.nakulaedu.adapter.NilaiAdapter;
import com.izrael.nakulaedu.classmodel.DataNilai;
import com.izrael.nakulaedu.classmodel.DataQuiz;
import com.izrael.nakulaedu.model.DataQuizUjian;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataQuizAdapter nilaiAdapter;
    String[] name;
    String[] guru;
    private List<DataQuizUjian> results;
    ApiInterface   mApiInterface;
    SessionManager sessionManager;
    ShimmerFrameLayout shimmerFrameLayout;
    String[]       nilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        recyclerView = findViewById(R.id.reclerviewdataquiz);
        recyclerView.setHasFixedSize(true);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
        recyclerView.setLayoutManager(new LinearLayoutManager(QuizActivity.this));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(QuizActivity.this);
        results = new ArrayList<>();
        ApiList();
    }

    private void ApiList() {
        Call<DataQuiz> uploadGambar = mApiInterface.dataquiz(Integer.parseInt(sessionManager.get_KODEKELAS()));
        uploadGambar.enqueue(new Callback<DataQuiz>() {
            @Override
            public void onResponse(Call<DataQuiz> call, Response<DataQuiz> response) {
                assert response.body() != null;

                Log.d("2", "onResponse: " + response.body());
                results.addAll(response.body().getData());

                shimmerFrameLayout.setVisibility(View.GONE);
                shimmerFrameLayout.stopShimmerAnimation();
                nilaiAdapter = new DataQuizAdapter(QuizActivity.this,results);

                recyclerView.setAdapter(nilaiAdapter);

            }

            @Override
            public void onFailure(Call<DataQuiz> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
}
