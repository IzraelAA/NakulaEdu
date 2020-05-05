package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.agrawalsuneet.dotsloader.loaders.ZeeLoader;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.firebase.database.DatabaseReference;
import com.izrael.nakulaedu.adapter.JadwalPelajaraan;
import com.izrael.nakulaedu.model.Bahan;
import com.izrael.nakulaedu.model.GetJadwal;
import com.izrael.nakulaedu.model.JadwalResult;
import com.izrael.nakulaedu.model.Result;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelajaranActivity extends AppCompatActivity implements DownloadFile.Listener {
    String url = "http://siakad.nakula.co.id/files/";
    String kode, api;
    private List<Result> results;
    private JadwalPelajaraan nilaiAdapter;
    SessionManager session;
    RemotePDFViewPager remotePDFViewPager;
    DatabaseReference reference;
    es.voghdev.pdfviewpager.library.PDFViewPager pdfViewPager;
    ApiInterface mApiInterface;
    LinearLayout root;
    EditText etPdfUrl;
    Button btnDownload;
    PDFPagerAdapter adapter;
    final Context ctx = this;
    final DownloadFile.Listener listener = this;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelajaran);
        Intent i = getIntent();
        root = findViewById(R.id.container);
        kode = i.getStringExtra("kodejadwal");
        results = new ArrayList<>();
        session = new SessionManager(PelajaranActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ApiJadwal();
        pdfViewPager = findViewById(R.id.pdfViewPager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (adapter != null) {
            adapter.close();
        }
    }

    private void ApiJadwal() {
        Call<Bahan> uploadGambar = mApiInterface.bahan(session.get_NISN(), kode);
        uploadGambar.enqueue(new Callback<Bahan>() {
            @Override
            public void onResponse(Call<Bahan> call, Response<Bahan> response) {
                assert response.body() != null;

                results.addAll(response.body().getResult());

                api = results.get(0).getFileUpload();

                remotePDFViewPager = new RemotePDFViewPager(ctx, url + api, listener);
                remotePDFViewPager.setId(R.id.pdfViewPager);
                Log.e("TAG", "onkkoResponse: " + api);
                Log.d("", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<Bahan> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });

    }


    public void updateLayout() {
        root.removeAllViewsInLayout();
        root.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);
        updateLayout();
    }


    @Override
    public void onFailure(Exception e) {
        Log.e("TAG", "onFailure: " + e);
        // kalo gagal download
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        // buat bikin progressbar misalnya
        Log.e("TAG", "onFailure: " + progress);
    }
}
