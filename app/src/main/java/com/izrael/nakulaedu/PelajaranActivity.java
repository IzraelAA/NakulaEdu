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
    String url = "https://testing.nakula.co.id/assets/materi/";
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
    ProgressBar pg;
    Button btnDownload;
    PDFPagerAdapter adapter;
    final Context ctx = this;
    final DownloadFile.Listener listener = this;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelajaran);
        root = findViewById(R.id.container);
        Intent i = getIntent();
        pg = findViewById(R.id.pg);
        pg.setVisibility(View.VISIBLE);
        kode = i.getStringExtra("file");
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

                remotePDFViewPager = new RemotePDFViewPager(ctx, url + kode, listener);
                remotePDFViewPager.setId(R.id.pdfViewPager);

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
        pg.setVisibility(View.GONE);
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
