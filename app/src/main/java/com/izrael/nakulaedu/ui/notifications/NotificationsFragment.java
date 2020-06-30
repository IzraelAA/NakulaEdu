package com.izrael.nakulaedu.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.agrawalsuneet.dotsloader.loaders.ZeeLoader;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.izrael.nakulaedu.LoginActivity;
import com.izrael.nakulaedu.ProfilSiswaActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.DefaultResponse;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    String sname, snis, semail, shp, pass;
    TextInputLayout name, nis, email, hp, inputPassword;
    SessionManager session;
    Button Logout, update;
    ApiInterface mApiInterface;
    ProgressBar zeeLoader;
    CircleImageView circleImageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        session = new SessionManager(getActivity());
        Logout = root.findViewById(R.id.logout);
        name = root.findViewById(R.id.name);
        nis = root.findViewById(R.id.nis);
        email = root.findViewById(R.id.email);
        zeeLoader = root.findViewById(R.id.zee);
        inputPassword = root.findViewById(R.id.password);
        update = root.findViewById(R.id.update);
        hp = root.findViewById(R.id.nohp);
        circleImageView = root.findViewById(R.id.circleImageView);
        if (session.get_Foto().equals("default.png")) {
            Glide.with(this).load("https://testing.nakula.co.id/assets/foto_siswa/" + session.get_Foto()).into(circleImageView);
        } else {
            Glide.with(this).load("https://testing.nakula.co.id/assets/foto_siswa/" + session.get_Foto()).into(circleImageView);
        }
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        inputPassword.getEditText().setText(session.getpassword());
        name.getEditText().setText(session.get_nama());
        nis.getEditText().setText(session.get_NISN());
        hp.getEditText().setText(session.get_NoTelpon());
        email.getEditText().setText(session.get_Email());
        zeeLoader.setVisibility(View.INVISIBLE

        );
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.set_is_loggedout();
                Intent intent = (new Intent(getActivity(), LoginActivity.class));
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeeLoader.setVisibility(View.VISIBLE);
                ApiUpdate();
            }

        });
        return root;

    }

    private void ApiUpdate() {

        sname = name.getEditText().getText().toString();
        snis = nis.getEditText().getText().toString();
        shp = hp.getEditText().getText().toString();
        semail = email.getEditText().getText().toString();
        pass = inputPassword.getEditText().getText().toString();
        Call<DefaultResponse> updatesiswa = mApiInterface.updatesiswa(semail, sname, shp, snis, pass);
        updatesiswa.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 200) {

                    session.set_nama(sname);
                    session.set_Email(semail);
                    session.set_NISN(snis);
                    session.set_NoTelpon(shp);
                    session.set_password(pass);
                    Toast.makeText(getActivity(), "Berhasil di update", Toast.LENGTH_LONG).show();
                    zeeLoader.setVisibility(View.INVISIBLE

                    );
                } else {
                    zeeLoader.setVisibility(View.INVISIBLE

                    );
                    Toast.makeText(getActivity(), "Gagal update", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }
}
