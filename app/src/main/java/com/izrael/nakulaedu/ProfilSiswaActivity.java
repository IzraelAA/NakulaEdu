package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.material.textfield.TextInputLayout;
import com.izrael.nakulaedu.session.SessionManager;

public class ProfilSiswaActivity extends AppCompatActivity {
    TextInputLayout name, nis, email, hp;
    SessionManager session;
    Button         Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_siswa);
        session = new SessionManager(ProfilSiswaActivity.this);
        Logout = findViewById(R.id.logout);
        name = findViewById(R.id.name);
        nis = findViewById(R.id.nis);
        email = findViewById(R.id.email);
        hp = findViewById(R.id.nohp);
        name.getEditText().setText(session.get_nama());
        nis.getEditText().setText(session.get_NISN());
        hp.getEditText().setText(session.get_TELPON());
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.set_is_loggedout();
                Intent intent = (new Intent(ProfilSiswaActivity.this,LoginActivity.class));
                startActivity(intent);
            }
        });
    }
}