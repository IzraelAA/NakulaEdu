package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DataJadwalActivity extends AppCompatActivity {
ImageView senin,selasa,rabu,kamis,jumat,sabtu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_jadwal);
        senin = findViewById(R.id.senin);
        selasa = findViewById(R.id.selasa);
        rabu = findViewById(R.id.rabu);
        kamis = findViewById(R.id.kamis);
        jumat = findViewById(R.id.jumat);
        sabtu = findViewById(R.id.sabtu);
        senin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DataJadwalActivity.this, JadwalPelajaran.class);
                intent.putExtra("hari","Senin");
          startActivity(intent);
            }
        });
        selasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DataJadwalActivity.this, JadwalPelajaran.class);
                intent.putExtra("hari","Selasa");
          startActivity(intent);
            }
        });
        rabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DataJadwalActivity.this, JadwalPelajaran.class);
                intent.putExtra("hari","Rabu");
          startActivity(intent);
            }
        });
        kamis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DataJadwalActivity.this, JadwalPelajaran.class);
                intent.putExtra("hari","Kamis");
          startActivity(intent);
            }
        });
        jumat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DataJadwalActivity.this, JadwalPelajaran.class);
                intent.putExtra("hari","Jum'at");
          startActivity(intent);
            }
        });
        sabtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DataJadwalActivity.this, JadwalPelajaran.class);
                intent.putExtra("hari","Sabtu");
          startActivity(intent);
            }
        }); 
    }
}