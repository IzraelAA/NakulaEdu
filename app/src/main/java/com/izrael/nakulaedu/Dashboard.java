package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Dashboard extends AppCompatActivity {
    Button profilsiswa,absensi,pelajaransiswa,tugas,mapel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        profilsiswa = findViewById(R.id.profilsiswa);
        absensi = findViewById(R.id.absensi);
        tugas = findViewById(R.id.tugas);
        mapel = findViewById(R.id.mapel);
        pelajaransiswa = findViewById(R.id.pelajaransiswa);
        pelajaransiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,ProfilSiswaActivity.class));
            }
        });
        tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
