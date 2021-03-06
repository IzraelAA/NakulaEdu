package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.izrael.nakulaedu.classmodel.Login;
import com.izrael.nakulaedu.model.DefaultResponse;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilSiswaActivity extends AppCompatActivity {
    TextInputLayout name, nis, email, hp,inputPassword;
    SessionManager session;
    Button         Logout,update;
    DatabaseReference reference;
    CircleImageView circleImageView;
    String sname,snis,semail,shp,pass;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_siswa);
        session = new SessionManager(ProfilSiswaActivity.this);
        Logout = findViewById(R.id.logout);
        name = findViewById(R.id.name);
        nis = findViewById(R.id.nis);
        inputPassword = findViewById(R.id.inputPassword);
        email = findViewById(R.id.email);
        hp = findViewById(R.id.nohp);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        update = findViewById(R.id.update);
        circleImageView = findViewById(R.id.circleImageView);
        if (session.get_Foto().equals("default.png")){
            Glide.with(this).load("https://admin.nakula.co.id/assets/foto_siswa/"+session.get_Foto()).into(circleImageView);
        }else {
            Glide.with(this).load("https://admin.nakula.co.id/assets/foto_siswa/"+session.get_Foto()).into(circleImageView);
        }

        inputPassword.getEditText().setText(session.getpassword());
        name.getEditText().setText(session.get_nama());
        nis.getEditText().setText(session.get_NISN());
        hp.getEditText().setText(session.get_NoTelpon());
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.set_is_loggedout();
                Intent intent = (new Intent(ProfilSiswaActivity.this,LoginActivity.class));
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiUpdate();
            }

        });
    }
    private void ApiUpdate() {

        email.getEditText().setText(session.get_Email());
        sname = name.getEditText().getText().toString();
        snis = nis.getEditText().getText().toString();
        shp = hp.getEditText().getText().toString();
        semail = email.getEditText().getText().toString();
        pass = inputPassword.getEditText().getText().toString();
        Call<DefaultResponse> updatesiswa = mApiInterface.updatesiswa(semail,sname,shp,snis,pass);
        updatesiswa.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 200){
                        reference = FirebaseDatabase.getInstance().getReference("Users").child(session.get_ID_SISWA());
                        HashMap<String, String> hashmap = new HashMap<>();
                    hashmap.put("id",session.get_ID_SISWA());
                    hashmap.put("username",sname);
                        hashmap.put("search",sname.toLowerCase());
                    hashmap.put("imageUrl",  session.get_Foto());
                    hashmap.put("status", "offline");
                        reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    session.set_nama(sname);
                                    session.set_Email(snis);
                                    session.set_NoTelpon(semail);
                                    session.set_password(pass);
                                    Toast.makeText(ProfilSiswaActivity.this,"Berhasil di update",Toast.LENGTH_LONG).show();


                                } else {
                                    Toast.makeText(ProfilSiswaActivity.this,"Gagal update",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }else {
                    Toast.makeText(ProfilSiswaActivity.this,"Gagal update",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }
}