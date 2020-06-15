package com.izrael.nakulaedu.ui.home;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.izrael.nakulaedu.AbsensiActivity;
import com.izrael.nakulaedu.Dashboard;
import com.izrael.nakulaedu.JadwalPelajaran;
import com.izrael.nakulaedu.KantinActivity;
import com.izrael.nakulaedu.MapelActivity;
import com.izrael.nakulaedu.MenuchatActivity;
import com.izrael.nakulaedu.NilaiActivity;
import com.izrael.nakulaedu.NotifikasiActivity;
import com.izrael.nakulaedu.ProfilSiswaActivity;
import com.izrael.nakulaedu.QuizActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.RaportActivity;
import com.izrael.nakulaedu.TagihanActivity;
import com.izrael.nakulaedu.adapter.MapelAdapter;
import com.izrael.nakulaedu.adapter.MapelDsAdapater;
import com.izrael.nakulaedu.classmodel.MapelClass;
import com.izrael.nakulaedu.model.MapelResult;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button pelajaransiswa, tugas, chat, nilaiharian, videconverence, raport, kantin, tagihan;
    TextView nis, nama;
    MapelDsAdapater mapeladapter;
    SessionManager  session;
    private List<MapelResult> results;
    CircleImageView circleImageView;
    RecyclerView    recyclermapel, recyclerjadwal;
    ImageView notifikasi;
    ApiInterface   mApiInterface;
    public static final int REQUEST_IMAGE222 = 100;
    public static final int REQUEST_IMAGE111 = 120;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        raport = root.findViewById(R.id.raport);
        session = new SessionManager(getActivity());
        results = new ArrayList<>();
        recyclermapel = root.findViewById(R.id.materirecyclerview);
        recyclermapel.setHasFixedSize(true);
        recyclermapel.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        inisial(root);
        sp();
        ApiJadwal();
        permission();
        intent();
        return root;

    }
    private void ApiJadwal() {
        Call<MapelClass> uploadGambar = mApiInterface.mapel(Integer.parseInt(session.get_KODEKELAS()));
        uploadGambar.enqueue(new Callback<MapelClass>() {
            @Override
            public void onResponse(Call<MapelClass> call, Response<MapelClass> response) {
                assert response.body() != null;

                results.addAll(response.body().getData());

                mapeladapter = new MapelDsAdapater(getActivity(), results);

                recyclermapel.setAdapter(mapeladapter);

            }

            @Override
            public void onFailure(Call<MapelClass> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });

    }
    private void intent() {

        raport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), RaportActivity.class);
                startActivity(in);
            }
        });
        kantin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), KantinActivity.class));
            }
        });
        tagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TagihanActivity.class));
            }
        });
        notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NotifikasiActivity.class));
            }
        });
        videconverence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getPackageManager().getLaunchIntentForPackage("app.cybrook.teamlink") == null) {
                    Toast.makeText(getActivity(), "Download Aplikasi TeamLink", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("app.cybrook.teamlink");
                    startActivity(intent);
                }
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MenuchatActivity.class));
            }
        });
//        mapel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), MapelActivity.class));
//            }
//        });
        pelajaransiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), JadwalPelajaran.class));
            }
        });
        nilaiharian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NilaiActivity.class));
            }
        });
        tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), QuizActivity.class);
                in.putExtra("Url", "http://siakad.nakula.co.id/");
                startActivity(in);
            }
        });
//        profilsiswa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ProfilSiswaActivity.class));
//            }
//        });
//        absensi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), AbsensiActivity.class));
//            }
//        });
    }

    private void permission() {
        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {

        } else {
            EasyPermissions.requestPermissions(this, "Izinkan Aplikasi Mengakses Lokasi?", REQUEST_IMAGE222, Manifest.permission.ACCESS_COARSE_LOCATION);

        }
        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
        } else {
            EasyPermissions.requestPermissions(this, "Izinkan Aplikasi Mengakses Lokasi?", REQUEST_IMAGE111, Manifest.permission.ACCESS_FINE_LOCATION);

        }
    }

    private void sp() {
        if (session.get_Foto().equals("default.png")) {
            Glide.with(this).load("https://testing.nakula.co.id/assets/foto_siswa/" + session.get_Foto()).into(circleImageView);
        } else {
            Glide.with(this).load("https://testing.nakula.co.id/assets/foto_siswa/" + session.get_Foto()).into(circleImageView);
        }
    }

    private void inisial(View root) {

        nis = root.findViewById(R.id.nis);
        nama = root.findViewById(R.id.name);
        circleImageView = root.findViewById(R.id.username);
        kantin = root.findViewById(R.id.kantin);
        tagihan = root.findViewById(R.id.tagihan);
        notifikasi = root.findViewById(R.id.notifikasi);
        nama.setText(session.get_nama());
        videconverence = root.findViewById(R.id.vidconverence);
        nis.setText(session.get_NISN());
        tugas = root.findViewById(R.id.tugas);
        chat = root.findViewById(R.id.chat);
        pelajaransiswa = root.findViewById(R.id.pelajaransiswa);
        nilaiharian = root.findViewById(R.id.nilaiharian);
    }
}