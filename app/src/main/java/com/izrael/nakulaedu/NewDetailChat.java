package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.izrael.nakulaedu.adapter.ChatStatus;
import com.izrael.nakulaedu.adapter.IdChatStatus;
import com.izrael.nakulaedu.classmodel.DetailChat;
import com.izrael.nakulaedu.model.DetailChatStatus;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewDetailChat extends AppCompatActivity {

    SessionManager sessionManager;
    private List<DetailChatStatus> results;
    ApiInterface mApiInterface;
    IdChatStatus iddatanilaiadapter;
    private RecyclerView recyclerView;
    ImageView photodeskripsi;
    TextView namaguru;
    EditText editkirim;
    ImageButton btn_send;
    ImageButton btn_file;
    private static File mediaStorageDir;
    static File mediaFile;
    TextView deskripsi;
    String send;
    String kode;
    String id_guru;
    String getChatdeskripsi;
    String getNamaPelajaran;
    String getGambar;
    Uri fileUri;
    public final int REQUEST_CAMERA = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail_chat);
        Intent i = getIntent();
        kode = i.getStringExtra("id_status");

        getChatdeskripsi = i.getStringExtra("getChatdeskripsi");
        id_guru = i.getStringExtra("id_guru");
        getNamaPelajaran = i.getStringExtra("getNamaPelajaran");
        getGambar = i.getStringExtra("getGambar");
        photodeskripsi = findViewById(R.id.photodeskripsi);
        btn_send = findViewById(R.id.btn_send);
        btn_file = findViewById(R.id.btn_file);
        editkirim = findViewById(R.id.edit_send);
        deskripsi = findViewById(R.id.deskripsi);
        namaguru = findViewById(R.id.namaguru);
        namaguru.setText(getNamaPelajaran);
        deskripsi.setText(getChatdeskripsi);
        Glide.with(this).load("https://admin.nakula.co.id/assets/chat/" +  getGambar ).into(photodeskripsi);
        recyclerView = findViewById(R.id.commentar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(NewDetailChat.this));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(NewDetailChat.this);
        ApiList();
        btn_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    fileUri = FileProvider.getUriForFile(NewDetailChat.this, BuildConfig.APPLICATION_ID + ".fileprovider", getOutputMediaFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatkirim();
            }
        });
    }

    private void chatkirim() {
        send = editkirim.getText().toString();
        editkirim.setText("");
        Call<ResponseBody> uploadGambar = mApiInterface.chatkirim(sessionManager.get_ID_SISWA(),sessionManager.get_KODEKELAS(),id_guru,send,kode);
        uploadGambar.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200){

                    ApiList();
                }else {
                    Toast.makeText(NewDetailChat.this,"Chat Tidak Terkirim",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private static File getOutputMediaFile() throws IOException {

        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "nakula");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                return mediaStorageDir;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "nakula" + timeStamp + ".jpg");

        if (!mediaFile.exists()) mediaFile.createNewFile();

        return mediaFile;
    }
    private void ApiList( )  {
        results = null;
        results = new ArrayList<>();
        results.clear();
        Call<DetailChat> uploadGambar = mApiInterface.chat_status(Integer.parseInt(kode));
        uploadGambar.enqueue(new Callback<DetailChat>() {
            @Override
            public void onResponse(Call<DetailChat> call, Response<DetailChat> response) {
                assert response.body() != null;

                if (response.code() == 200 ){

                    results.clear();
                    results.addAll(response.body().getData());
                    iddatanilaiadapter = new IdChatStatus(NewDetailChat.this,results,"belakang");

                  recyclerView.setAdapter(iddatanilaiadapter);
                }else {

                }

            }

            @Override
            public void onFailure(Call<DetailChat> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    String cek = String.valueOf(fileUri);
                    Intent intent = new Intent(NewDetailChat.this, UploadActivity.class);
                    intent.putExtra("photo", cek);
                    intent.putExtra("cek", "chat");
                    intent.putExtra("id_siswa",  sessionManager.get_ID_SISWA());
                    intent.putExtra("id_kelas", sessionManager.get_KODEKELAS());
                    intent.putExtra("id_guru", id_guru);
                    intent.putExtra("send", send);
                    intent.putExtra("kode", kode);
                 startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];

                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean
                                showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        this, permission);

                    }
                }
            }
        }
    }
}