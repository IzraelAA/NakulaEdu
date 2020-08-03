package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.izrael.nakulaedu.IdQuizActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.DataQuizUjian;
import com.izrael.nakulaedu.model.Quiz;
import com.izrael.nakulaedu.model.absen;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdDataQuizAdapter extends RecyclerView.Adapter<IdDataQuizAdapter.ViewHolder> {
    List<Quiz> list;
    Context context;
    int list1;
    ApiInterface mApiInterface;
    String kelas;
    String siswa;

    public IdDataQuizAdapter(Context contextt, List<Quiz> list,String kelas,String siswa) {
        this.context = contextt;
        this.list = list;
        this.siswa = siswa;
        this.kelas = kelas;

    }

    @NonNull
    @Override
    public IdDataQuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.iddataquiz, parent, false);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        return new IdDataQuizAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final IdDataQuizAdapter.ViewHolder holder, int position) {
        final Quiz quizUjian = list.get(position);
        if (quizUjian.getTipe().equals("gambar")){
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load("https://admin.nakula.co.id/assets/soal/" + quizUjian.getFile()).into(holder.imageView);

        }
        holder.namaquiz.setText(quizUjian.getQuiz());
        holder.nomer.setText(quizUjian.getNomer().toString());
        holder.simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String jawaban = holder.dataquiz.getText().toString();
                Call<ResponseBody> jawaban_quiz = mApiInterface.jawaban_quiz(quizUjian.getIdDataquiz().toString(), kelas, jawaban, quizUjian.getIdQuiz().toString(), siswa,null);
                jawaban_quiz.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.code() == 200) {
                            holder.simpan.setText("TerSimpan");
                            holder.simpan.setBackground(ContextCompat.getDrawable(context, R.drawable.buttonb));
                        }else {

                            holder.simpan.setBackground(ContextCompat.getDrawable(context, R.drawable.buttona));
                            holder.simpan.setText("Coba Ulang");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        holder.simpan.setText("Coba Ulang");
                        Log.e("TAG", "onFailure: ",t );
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView guru, nomer, namaquiz;
        EditText dataquiz;
        ImageView imageView;
        Button simpan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            dataquiz = itemView.findViewById(R.id.dataquiz);
            simpan = itemView.findViewById(R.id.simpan);
            namaquiz = itemView.findViewById(R.id.namaquiz);
            nomer = itemView.findViewById(R.id.nomer2);
        }

    }
}
