package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.izrael.nakulaedu.classmodel.Raport;
import com.izrael.nakulaedu.session.SessionManager;

import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.EasyPermissions;

public class Dashboard extends AppCompatActivity {
    Button profilsiswa, absensi, pelajaransiswa, tugas, mapel, chat, nilaiharian, videconverence,raport,kantin,tagihan,notifikasi;
    TextView nis, nama;
    SessionManager session;
    CircleImageView circleImageView;
    public static final int REQUEST_IMAGE222 = 100;
    public static final int REQUEST_IMAGE111 = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

    }
}
