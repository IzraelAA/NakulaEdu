package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.izrael.nakulaedu.adapter.IdDataQuizAdapter;
import com.izrael.nakulaedu.classmodel.QuizUjian;
import com.izrael.nakulaedu.classmodel.pgquiz;
import com.izrael.nakulaedu.model.pgquizdata;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PgActivity extends AppCompatActivity {
    String jawaban, cocok, r3, r4, r5, r6, r7, r8;
    int nomer = 0;
    int nomernext  ;
    int nomerbefore  ;
    int IdDataquiz,jumlah;
    TextView soal;
    pgquiz pgquiz1;
    Button next,before;
    SessionManager  sessionManager;
    ApiInterface   mApiInterface;
    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5, radioGroup6, radioGroup7, radioGroup8;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButtonbenar, radioButton7, radioButton8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg);

        radioGroup1 = findViewById(R.id.radio1);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Intent intent = getIntent();
        IdDataquiz = intent.getIntExtra("IdDataquiz", 0);
        jumlah = intent.getIntExtra("jumlah", 0);
        nomer = 1;
        radioButton1 = findViewById(R.id.iya1);
        radioButton2 = findViewById(R.id.iya2);
        soal = findViewById(R.id.soal);
        sessionManager = new SessionManager(PgActivity.this);
        radioButton3 = findViewById(R.id.iya3);
        next = findViewById(R.id.next);
        before = findViewById(R.id.before);
        radioButton4 = findViewById(R.id.iya4);
        radioButton5 = findViewById(R.id.iya5);
        ApiList(nomer);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId1 = radioGroup1.getCheckedRadioButtonId();
                radioButtonbenar = findViewById(radioId1);
                if (radioButtonbenar== null){
                    Toast.makeText(PgActivity.this,"Silakan isi terlebih dahulu",Toast.LENGTH_LONG).show();
                }else {
                    jawaban =  radioButtonbenar.getText().toString();
                    if (jawaban.equals(cocok)){
                        Benar();
                    }else {
                        Salah();
                    }
                }
            }
        });
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiList(nomerbefore);
            }
        });
    }

    private void Salah() {
        ApiList(nomernext);
        int salah = 0;
        Call<ResponseBody> jawaban_quiz = mApiInterface.jawaban_quiz(String.valueOf(IdDataquiz),sessionManager.get_KODEKELAS() , null, pgquiz1.getId().toString(), sessionManager.get_ID_SISWA(), String.valueOf(salah));
        jawaban_quiz.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("TAG", "onFailure: ",t );
            }
        });
    }

    private void Benar() {
        ApiList(nomernext);
        Call<ResponseBody> jawaban_quiz = mApiInterface.jawaban_quiz(String.valueOf(IdDataquiz),sessionManager.get_KODEKELAS() , null, pgquiz1.getId().toString(), sessionManager.get_ID_SISWA(),pgquiz1.getBobot());
        jawaban_quiz.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("TAG", "onFailure: ",t );
            }
        });
    }

    private void ApiList(int number) {
        if (number == 1){
            before.setVisibility(View.GONE);
        }else{

            before.setVisibility(View.VISIBLE);
        }

        Call<pgquizdata> uploadGambar = mApiInterface.soal_quizpgnomer(IdDataquiz,number);
        uploadGambar.enqueue(new Callback<pgquizdata>() {
            @Override
            public void onResponse(Call<pgquizdata> call, Response<pgquizdata> response) {
                assert response.body() != null;
                pgquiz1 = response.body().getData();
                String no = String.valueOf(pgquiz1.getNomer());
                nomernext = pgquiz1.getNomer().intValue() + 1;
                nomerbefore = pgquiz1.getNomer().intValue() - 1;
                if (pgquiz1.getNomer() == jumlah){
                    next.setText("Lihat Jawaban");
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
                soal.setText(no+". "+pgquiz1.getSoal());
                if (pgquiz1.getJawaban().equals("A")){
                    cocok = pgquiz1.getOpsiA();
                }else if (pgquiz1.getJawaban().equals("B")){

                    cocok = pgquiz1.getOpsiB();
                }else if (pgquiz1.getJawaban().equals("C")){

                    cocok = pgquiz1.getOpsiC();
                }else  if (pgquiz1.getJawaban().equals("D")){

                    cocok = pgquiz1.getOpsiD();
                }else  if (pgquiz1.getJawaban().equals("E")){

                    cocok = pgquiz1.getOpsiE();
                }
                radioButton1.setText(pgquiz1.getOpsiA());
                radioButton2.setText(pgquiz1.getOpsiB());
                radioButton3.setText(pgquiz1.getOpsiC());
                radioButton4.setText(pgquiz1.getOpsiD());
                radioButton5.setText(pgquiz1.getOpsiE());

            }

            @Override
            public void onFailure(Call<pgquizdata> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
}