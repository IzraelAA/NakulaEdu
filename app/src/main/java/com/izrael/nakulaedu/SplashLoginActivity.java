package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.ZeeLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.izrael.nakulaedu.classmodel.Login;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import org.jetbrains.annotations.NotNull;

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
        zeeLoader = findViewById(R.id.zee);
        session = new SessionManager(SplashLoginActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        send_login();
    }

    private void send_login() {
        Log.d("", "send_login: me");
//        Call<GetAuth> getPelanggan = mApiInterface.getPelanggan(nis, pass);
        Call<Login> loginCall = mApiInterface.getLogin(nis,pass);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NotNull Call<Login> call, @NotNull final Response<Login> response) {
                assert response.body() != null;

                Log.d("www", "onResponse: "+response.code());
                if (response.code() == 200) {
                    reference = FirebaseDatabase.getInstance().getReference("Users").child(response.body().getData().getIdSiswa().toString());
                    HashMap<String, String> hashmap = new HashMap<>();
                    hashmap.put("id", response.body().getData().getIdSiswa().toString());
                    hashmap.put("username", response.body().getData().getNamaSiswa());
                    hashmap.put("imageUrl",  response.body().getData().getFoto());
                    hashmap.put("status", "offline");
                    hashmap.put("search", response.body().getData().getNamaSiswa().toLowerCase());
                    reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                session.set_ID_SISWA(response.body().getData().getIdSiswa().toString());
                                session.set_KODEKELAS(response.body().getData().getIdKelas().toString());
                                session.set_NISN(response.body().getData().getNis());
                                session.set_Foto(response.body().getData().getFoto());
                                session.set_Namakelas(response.body().getData().getNamaKelas());
                                session.set_password(response.body().getData().getPassword());
                                session.set_LOGO(response.body().getData().getLogo());
                                session.set_IDSEKOLAH(response.body().getData().getIdSekolah().toString());
                                session.set_nama(response.body().getData().getNamaSiswa());
                                session.set_Email(response.body().getData().getEmail());
                                session.set_NoTelpon(response.body().getData().getNoTelphone());
                                session.set_is_loggedin();
                                cek();

                                Intent intent = new Intent(SplashLoginActivity.this, NewActivity.class);
                                startActivity(intent);
                            } else {

                                Intent intent = new Intent(SplashLoginActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(SplashLoginActivity.this, "Nama/Password Salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Intent intent = new Intent(SplashLoginActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(SplashLoginActivity.this, "Nama/Password Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.e("", "onFailure: ",t );
                Log.e("", "onFailure: ppp",t );
            }
        });
    }


    private void cek() {
        if (session.is_loggedin()) {
            Intent intent = new Intent(SplashLoginActivity.this, NewActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
