package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
public class PelajaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelajaran);
        Intent i = getIntent();
       String jdwl = i.getStringExtra("kodejadwal");
       TextView piew = findViewById(R.id.pass);
       piew.setText(jdwl);
    }
}
