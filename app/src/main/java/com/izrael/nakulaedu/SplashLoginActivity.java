package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.ZeeLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.izrael.nakulaedu.model.GetAuth;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashLoginActivity extends AppCompatActivity {
String nis,pass;
    SessionManager    session;
    DatabaseReference reference;
    ApiInterface      mApiInterface;
    ZeeLoader         zeeLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);
        Intent intent = getIntent();
        nis = intent.getStringExtra("nis");
        pass = intent.getStringExtra("pass");
        Toast.makeText(this, pass+nis, Toast.LENGTH_SHORT).show();
        zeeLoader = findViewById(R.id.zee);
        session = new SessionManager(SplashLoginActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                send_login();
            }
        },1500);
    }

    private void send_login() {

        Call<GetAuth> getPelanggan = mApiInterface.getPelanggan(nis, pass);
        getPelanggan.enqueue(new Callback<GetAuth>() {
            @Override
            public void onResponse(Call<GetAuth> call, final Response<GetAuth> response) {
                int code = response.body().getCode();
                if (code == 200) {
                    reference = FirebaseDatabase.getInstance().getReference("Users").child(response.body().getPelanggan().getid_siswa());
                    HashMap<String, String> hashmap = new HashMap<>();
                    hashmap.put("id", response.body().getPelanggan().getid_siswa());
                    hashmap.put("username", response.body().getPelanggan().getNama());
                    hashmap.put("imageUrl", "default");
                    hashmap.put("status", "offline");
                    hashmap.put("search", response.body().getPelanggan().getNama().toLowerCase());
                    reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                zeeLoader.setVisibility(View.GONE);
                                Toast.makeText(SplashLoginActivity.this, "Masuk", Toast.LENGTH_SHORT).show();
                                session.set_ID_SISWA(response.body().getPelanggan().getid_siswa());
                                session.set_KODEKELAS(response.body().getPelanggan().getKode_kelas());
                                session.set_NISN(response.body().getPelanggan().getNISN());
                                session.set_nik(response.body().getPelanggan().getNik());
                                session.set_TAHUN(response.body().getPelanggan().getAngkatan());
                                session.set_nama(response.body().getPelanggan().getNama());
                                session.set_TELPON(response.body().getPelanggan().gettelepon());
                                session.set_ID_JENIS_KELAMIN(response.body().getPelanggan().getid_jenis_kelamin());
                                session.set_is_loggedin();
                                cek();
                            } else {
                                zeeLoader.setVisibility(View.GONE);
                                Toast.makeText(SplashLoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SplashLoginActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                } else {
                    zeeLoader.setVisibility(View.GONE);
                    String message = response.body().getMessage();
                    Intent intent = new Intent(SplashLoginActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(SplashLoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetAuth> call, Throwable t) {
                Log.e("Retrofit Gets", t.toString());

                Intent intent = new Intent(SplashLoginActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void cek() {
        if (session.is_loggedin()) {
            Intent intent = new Intent(SplashLoginActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
        }
    }
}
