package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.izrael.nakulaedu.adapter.NilaiAdapter;
import com.izrael.nakulaedu.classmodel.Nilai;
import com.izrael.nakulaedu.fragment.Chatlist;
import com.izrael.nakulaedu.fragment.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class NilaiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Nilai>  list;
    private NilaiAdapter nilaiAdapter;
    String[] name;
    String[] guru;
    String[] nilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        recyclerView = findViewById(R.id.nilai);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(NilaiActivity.this));
        list = new ArrayList<>();
        AddList();
        ListAdd();
    }

    private void AddList() {
    name = getResources().getStringArray(R.array.data_nama);
    guru = getResources().getStringArray(R.array.data_guru);
    nilai = getResources().getStringArray(R.array.data_nilai);
    }

    private void ListAdd() {
        for (int i = 0; i < name.length; i++) {
            Nilai nilai2 = new Nilai();
            nilai2.setNama(name[i]);
            nilai2.setNilai(nilai[i]);
            nilai2.setPengajar(guru[i]);
            list.add(nilai2);
        }
        nilaiAdapter = new NilaiAdapter(NilaiActivity.this,list);

        recyclerView.setAdapter(nilaiAdapter);
    }



}
