package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.izrael.nakulaedu.session.SessionManager;

import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.EasyPermissions;

public class Dashboard extends AppCompatActivity {
    Button profilsiswa, absensi, pelajaransiswa, tugas, mapel, chat, nilaiharian, videconverence,raport,kantin,tagihan,notifikasi;
    TextView nis, nama;
    SessionManager session;
    CircleImageView circleImageView;
    public static final int REQUEST_IMAGE222 = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        profilsiswa = findViewById(R.id.profilsiswa);
        absensi = findViewById(R.id.absensi);
        raport = findViewById(R.id.raport);
        session = new SessionManager(Dashboard.this);
        nis = findViewById(R.id.nis);
        nama = findViewById(R.id.name);
        circleImageView = findViewById(R.id.fotousername);
        kantin = findViewById(R.id.kantin);
        tagihan = findViewById(R.id.tagihan);
        notifikasi = findViewById(R.id.notifikasi);
        if (session.get_Foto().equals("")){

        }else {
            Glide.with(this).load("http://siakad.nakula.co.id/foto_siswa/"+session.get_Foto()).into(circleImageView);
        }
        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

        } else {
            EasyPermissions.requestPermissions(this, "Izinkan Aplikasi Mengakses Storage?", REQUEST_IMAGE222, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        raport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Dashboard.this, TugasActivity.class);
                in.putExtra("Url","http://siakad.nakula.co.id/");
                startActivity(in);
            }
        });
        kantin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,KantinActivity.class));
            }
        });
        tagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,TagihanActivity.class));
            }
        });
        notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,NotifikasiActivity.class));
            }
        });
        nama.setText(session.get_nama());
        videconverence = findViewById(R.id.vidconverence);
        nis.setText(session.get_NISN());
        tugas = findViewById(R.id.tugas);
        mapel = findViewById(R.id.mapel);
        chat = findViewById(R.id.chat);
        nilaiharian = findViewById(R.id.nilaiharian);
        videconverence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPackageManager().getLaunchIntentForPackage("app.cybrook.teamlink")==null){
                    Toast.makeText(Dashboard.this, "Download Aplikasi TeamLink", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("app.cybrook.teamlink");
                    startActivity(intent);
                }
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, MenuchatActivity.class));
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
                startActivity(new Intent(Dashboard.this, NilaiActivity.class));
            }
        });
        tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Dashboard.this, TugasActivity.class);
                in.putExtra("Url","http://siakad.nakula.co.id/");
                startActivity(in);
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
