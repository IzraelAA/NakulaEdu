package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

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

public class LoginActivity extends AppCompatActivity {
    TextInputEditText inputNis, inputPassword;
    Button            Login;
    SessionManager    session;
    DatabaseReference reference;
    ApiInterface      mApiInterface;
    ZeeLoader         zeeLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = findViewById(R.id.buttonlogin);
        inputNis = findViewById(R.id.inputNis);
        inputPassword = findViewById(R.id.inputPassword);
        zeeLoader = findViewById(R.id.zee);
        session = new SessionManager(LoginActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        cek();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nis;
                String password;
                nis = inputNis.getText().toString();
                password = inputPassword.getText().toString();
                if (password.equals("")) {
                    inputPassword.setError("Jangan Di Kosongkan");
                } else if (nis.equals("")) {
                    inputNis.setError("Jangan Di Kosongkan");
                } else {
                    Login.setEnabled(false);
                    zeeLoader.setVisibility(View.VISIBLE);
                    send_login();
                }
            }
        });
    }

    private void cek() {
        if (session.is_loggedin()) {
            Intent intent = new Intent(LoginActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
        }
    }

    private void send_login() {

        Call<GetAuth> getPelanggan = mApiInterface.getPelanggan(inputNis.getText().toString(), inputPassword.getText().toString());
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
                                Login.setEnabled(true);
                                zeeLoader.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Masuk", Toast.LENGTH_SHORT).show();
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
                                Login.setEnabled(true);
                                zeeLoader.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Login.setEnabled(true);
                    zeeLoader.setVisibility(View.GONE);
                    String message = response.body().getMessage();
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetAuth> call, Throwable t) {
                Log.e("Retrofit Gets", t.toString());
            }
        });
    }
}
