package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.izrael.nakulaedu.classmodel.Login;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText inputNis, inputPassword;
    Button            Login;
    SessionManager    session;
    DatabaseReference reference;
    ApiInterface      mApiInterface;
    public static final int    REQUEST_IMAGE    = 1210;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = findViewById(R.id.buttonlogin);
        inputNis = findViewById(R.id.inputNis);
        inputPassword = findViewById(R.id.inputPassword);
        session = new SessionManager(LoginActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        cek();
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
        } else {
            EasyPermissions.requestPermissions(this, "Izinkan Aplikasi Mengakses Camera?", REQUEST_IMAGE, Manifest.permission.CAMERA);
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nis;
                String password;
                nis = inputNis.getText().toString();
                password = inputPassword.getText().toString();
                if (nis.equals("")) {
                    inputNis.setError("Jangan Di Kosongkan");
                } else if (password.equals("")) {
                    inputPassword.setError("Jangan Di Kosongkan");
                } else {
                    send_login();
                }
            }
        });
    }

    private void cek() {
        if (session.is_loggedin()) {
            Intent intent = new Intent(LoginActivity.this, NewActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void send_login() {
        Intent intent = new Intent(LoginActivity.this, SplashLoginActivity.class);
        intent.putExtra("nis", inputNis.getText().toString());
        intent.putExtra("pass", inputPassword.getText().toString());
        startActivity(intent);
    }

}
