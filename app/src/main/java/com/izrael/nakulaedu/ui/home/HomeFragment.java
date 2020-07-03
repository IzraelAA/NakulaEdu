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
import com.izrael.nakulaedu.DataJadwalActivity;
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
import com.izrael.nakulaedu.SettingActivity;
import com.izrael.nakulaedu.TagihanActivity;
import com.izrael.nakulaedu.adapter.DataQuizAdapter;
import com.izrael.nakulaedu.adapter.JadwalHari;
import com.izrael.nakulaedu.adapter.JadwalPelajaraan;
import com.izrael.nakulaedu.adapter.MapelAdapter;
import com.izrael.nakulaedu.adapter.MapelDsAdapater;
import com.izrael.nakulaedu.adapter.NotifikasiAdpater;
import com.izrael.nakulaedu.classmodel.DataNotifikasi;
import com.izrael.nakulaedu.classmodel.DataQuiz;
import com.izrael.nakulaedu.classmodel.MapelClass;
import com.izrael.nakulaedu.model.DataQuizUjian;
import com.izrael.nakulaedu.model.GetJadwal;
import com.izrael.nakulaedu.model.Jadwal;
import com.izrael.nakulaedu.model.JadwalResult;
import com.izrael.nakulaedu.model.MapelResult;
import com.izrael.nakulaedu.model.notifikasi;
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
    Button pelajaransiswa, mapel, chat, nilaiharian, videconverence, raport, kantin, tagihan;
    TextView nis, nama,kelas,notif;
    MapelDsAdapater mapeladapter;
    SessionManager  session;
    private DataQuizAdapter nilaiAdapter;
    private JadwalHari jadwalhari;
    String[] name;
    String[] guru;
    LinearLayout linearjadwal;
    private List<DataQuizUjian> results;
    private List<JadwalResult> jadwal;
    private List<com.izrael.nakulaedu.model.notifikasi> results2;
    CircleImageView circleImageView;
    RecyclerView    recyclermapel, recyclerjadwal;
    ImageView notifikasi,setting;
    ApiInterface   mApiInterface;
    public static final int REQUEST_IMAGE222 = 100;
    public static final int REQUEST_IMAGE111 = 120;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        raport = root.findViewById(R.id.raport);
        setting = root.findViewById(R.id.setting);
        notif = root.findViewById(R.id.notif);
        session = new SessionManager(getActivity());
        results = new ArrayList<>();
        linearjadwal = root.findViewById(R.id.linearjadwal);
        jadwal = new ArrayList<>();
        results2 = new ArrayList<>();
        recyclerjadwal = root.findViewById(R.id.jadwalrecyclerview);
        recyclerjadwal.setHasFixedSize(true);
        recyclerjadwal.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclermapel = root.findViewById(R.id.materirecyclerview);
        recyclermapel.setHasFixedSize(true);
        recyclermapel.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        inisial(root);
        sp();
        ApiHari();
        ApiJadwal();
        notif();
        permission();
        intent();
        return root;

    }

    private void ApiJadwal(){
        Call<GetJadwal> uploadGambar = mApiInterface.jadwalhari(Integer.parseInt(session.get_KODEKELAS()),"hari");
        uploadGambar.enqueue(new Callback<GetJadwal>() {
            @Override
            public void onResponse(Call<GetJadwal> call, Response<GetJadwal> response) {
                assert response.body() != null;

                if (response.code() == 200){

                    jadwal.addAll(response.body().getData());
                    if (jadwal.size() < 3){
                        linearjadwal.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        linearjadwal.requestLayout();
                    }
                    jadwalhari = new JadwalHari(getActivity(),jadwal);

                    recyclerjadwal.setAdapter(jadwalhari);

                    Log.e("TAG", "onkkoResponse: "+response.body().getData());
                    Log.d("", "onResponse: "+response.body());
                }
            }
            @Override
            public void onFailure(Call<GetJadwal> call, Throwable t) {

                Log.e("TAG", "kkonResponse: "+t);
                Log.d("", "onResponse: "+t);
            }
        });

    }

    private void ApiHari() {
        Call<DataQuiz> uploadGambar = mApiInterface.dataquiz(Integer.parseInt(session.get_KODEKELAS()));
        uploadGambar.enqueue(new Callback<DataQuiz>() {
            @Override
            public void onResponse(Call<DataQuiz> call, Response<DataQuiz> response) {
                assert response.body() != null;
                results.addAll(response.body().getData());

                nilaiAdapter = new DataQuizAdapter(getActivity(),results);

                recyclermapel.setAdapter(nilaiAdapter);

            }

            @Override
            public void onFailure(Call<DataQuiz> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });

    }


    private void notif(){
        Call<DataNotifikasi> uploadGambar = mApiInterface.notifikasi( session.get_KODEKELAS());
        uploadGambar.enqueue(new Callback<DataNotifikasi>() {
            @Override
            public void onResponse(Call<DataNotifikasi> call, Response<DataNotifikasi> response) {
                assert response.body() != null;

                results2.addAll(response.body().getData());
               String nomer = String.valueOf(results2.size());
                notif.setText(nomer);

            }
            @Override
            public void onFailure(Call<DataNotifikasi> call, Throwable t) {

                Log.e("TAG", "kkonResponse: "+t);
                Log.d("", "onResponse: "+t);
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
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), SettingActivity.class);
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
        mapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MapelActivity.class));
            }
        });
        pelajaransiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DataJadwalActivity
                        .class));
            }
        });
        nilaiharian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NilaiActivity.class));
            }
        });
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

        kelas = root.findViewById(R.id.kelas);
        kelas.setText(session.getNamakelas());
        nis = root.findViewById(R.id.nis);
        nama = root.findViewById(R.id.name);
        circleImageView = root.findViewById(R.id.username);
        kantin = root.findViewById(R.id.kantin);
        tagihan = root.findViewById(R.id.tagihan);
        notifikasi = root.findViewById(R.id.notifikasi);
        nama.setText(session.get_nama());
        videconverence = root.findViewById(R.id.vidconverence);
        nis.setText(session.get_NISN());
        mapel = root.findViewById(R.id.mapel);
        chat = root.findViewById(R.id.chat);
        pelajaransiswa = root.findViewById(R.id.pelajaransiswa);
        nilaiharian = root.findViewById(R.id.nilaiharian);
    }
}