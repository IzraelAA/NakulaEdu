package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.izrael.nakulaedu.session.SessionManager;

public class Dashboard extends AppCompatActivity {
    Button profilsiswa, absensi, pelajaransiswa, tugas, mapel,chat,nilaiharian;
TextView nis,nama;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        profilsiswa = findViewById(R.id.profilsiswa);
        absensi = findViewById(R.id.absensi);
        session =  new SessionManager(Dashboard.this);
        nis = findViewById(R.id.nis);
        nama = findViewById(R.id.name);
        nama.setText(session.get_nama());
        nis.setText(session.get_NISN());
        tugas = findViewById(R.id.tugas);
        mapel = findViewById(R.id.mapel);
        chat = findViewById(R.id.chat);
        nilaiharian = findViewById(R.id.nilaiharian);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,MenuchatActivity.class));
            }
        });
        pelajaransiswa = findViewById(R.id.pelajaransiswa);
        mapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, MapelActivity.class));
            }
        });
        pelajaransiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, JadwalPelajaran.class));
            }
        });
        nilaiharian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,NilaiActivity.class));
            }
        });
        tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Dashboard.this, MainActivity.class));
            }
        });
        profilsiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, ProfilSiswaActivity.class));
            }
        });
        absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AbsensiActivity.class));
            }
        });
    }
}
