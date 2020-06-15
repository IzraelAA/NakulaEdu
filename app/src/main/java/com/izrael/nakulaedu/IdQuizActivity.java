package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.izrael.nakulaedu.adapter.DataQuizAdapter;
import com.izrael.nakulaedu.adapter.IdDataQuizAdapter;
import com.izrael.nakulaedu.classmodel.DataQuiz;
import com.izrael.nakulaedu.classmodel.QuizUjian;
import com.izrael.nakulaedu.model.DataQuizUjian;
import com.izrael.nakulaedu.model.Quiz;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;
import android.widget.*;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdQuizActivity extends AppCompatActivity {
    int IdDataquiz;
    private List<Quiz> results;
    ApiInterface   mApiInterface;
    String nama,guru,pelajaran,gambar;
    TextView tvnamamapel,tvguru,tvpelajaran;
    ImageView sekolah;
    CircleImageView gambarguru;
    SessionManager sessionManager;
    ShimmerFrameLayout shimmerFrameLayout;
    private RecyclerView      recyclerView;
    private IdDataQuizAdapter nilaiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_quiz);
        recyclerView = findViewById(R.id.reclerviewidquiz);
        recyclerView.setHasFixedSize(true);
        tvguru = findViewById(R.id.guru1);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
        tvnamamapel = findViewById(R.id.namamapel1);
        tvpelajaran = findViewById(R.id.pelajaran2);
        gambarguru = findViewById(R.id.gambarguru);
        sekolah = findViewById(R.id.sekolah);
        recyclerView.setLayoutManager(new LinearLayoutManager(IdQuizActivity.this));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(IdQuizActivity.this);
        results = new ArrayList<>();
        Intent intent = getIntent();
        IdDataquiz = intent.getIntExtra("IdDataquiz", 0);
        nama = intent.getStringExtra("namaquiz");
        pelajaran = intent.getStringExtra("pelajaran");
        guru = intent.getStringExtra("guru");
        gambar = intent.getStringExtra("gamabar");
        tvguru.setText(guru);
        tvpelajaran.setText(pelajaran.toString());
        tvnamamapel.setText(nama);
        String toast;

        ApiList();
    }

    private void ApiList() {
        Call<QuizUjian> uploadGambar = mApiInterface.soalquiz(IdDataquiz);
        uploadGambar.enqueue(new Callback<QuizUjian>() {
            @Override
            public void onResponse(Call<QuizUjian> call, Response<QuizUjian> response) {
                assert response.body() != null;

                Log.d("2", "onResponse: " + response.body());
                results.addAll(response.body().getData());

                shimmerFrameLayout.setVisibility(View.GONE);
                shimmerFrameLayout.stopShimmerAnimation();
                nilaiAdapter = new IdDataQuizAdapter(IdQuizActivity.this, results);

                recyclerView.setAdapter(nilaiAdapter);

            }

            @Override
            public void onFailure(Call<QuizUjian> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
}