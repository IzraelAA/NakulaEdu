package com.izrael.nakulaedu.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.izrael.nakulaedu.LoginActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.session.SessionManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationsFragment extends Fragment {

    TextInputLayout name, nis, email, hp;
    SessionManager  session;
    Button          Logout;
    CircleImageView circleImageView,logo;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View           root     = inflater.inflate(R.layout.fragment_notifications, container, false);
        session = new SessionManager(getActivity());
        Logout = root.findViewById(R.id.logout);
        name = root.findViewById(R.id.name);
        nis = root.findViewById(R.id.nis);
        email = root.findViewById(R.id.email);
        hp = root.findViewById(R.id.nohp);
        circleImageView = root.findViewById(R.id.circleImageView);
        logo = root.findViewById(R.id.gambarsekolah);
        if (session.get_Foto().equals("default.png")){
            Glide.with(this).load("https://testing.nakula.co.id/assets/foto_siswa/"+session.get_Foto()).into(circleImageView);
        }else {
            Glide.with(this).load("https://testing.nakula.co.id/assets/foto_siswa/"+session.get_Foto()).into(circleImageView);
        }

        name.getEditText().setText(session.get_nama());
        nis.getEditText().setText(session.get_NISN());
        hp.getEditText().setEnabled(false);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.set_is_loggedout();
                Intent intent = (new Intent(getActivity(), LoginActivity.class));
                startActivity(intent);
            }
        });
        return root;
    }
}