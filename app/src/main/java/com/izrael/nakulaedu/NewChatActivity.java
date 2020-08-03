package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.izrael.nakulaedu.adapter.ChatStatus;
import com.izrael.nakulaedu.adapter.NilaiAdapter;
import com.izrael.nakulaedu.classmodel.DataNilai;
import com.izrael.nakulaedu.model.NewChat;
import com.izrael.nakulaedu.model.NewChatStatus;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ApiInterface   mApiInterface;
    SessionManager sessionManager;
    ChatStatus chatStatus;
    private List<NewChatStatus> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);
        recyclerView = findViewById(R.id.chat);
        recyclerView.setHasFixedSize(true);;
        recyclerView.setLayoutManager(new LinearLayoutManager(NewChatActivity.this));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(NewChatActivity.this);
        results = new ArrayList<>();
        ApiList();
    }
    private void ApiList() {

        Call<NewChat> uploadGambar = mApiInterface.chat(Integer.parseInt(sessionManager.get_KODEKELAS()));
        uploadGambar.enqueue(new Callback<NewChat>() {
            @Override
            public void onResponse(Call<NewChat> call, Response<NewChat> response) {
                assert response.body() != null;
                if (response.code() == 200){

                    Log.d("2", "onResponse: " + response.body());
                    results.addAll(response.body().getData());

                    chatStatus = new ChatStatus(NewChatActivity.this,results);

                    recyclerView.setAdapter(chatStatus);

                }

            }

            @Override
            public void onFailure(Call<NewChat> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
}