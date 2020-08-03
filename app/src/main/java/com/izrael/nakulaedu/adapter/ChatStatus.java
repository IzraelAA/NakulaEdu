package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.izrael.nakulaedu.NewDetailChat;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.classmodel.DetailChat;
import com.izrael.nakulaedu.classmodel.Idnilai;
import com.izrael.nakulaedu.model.DetailChatStatus;
import com.izrael.nakulaedu.model.Iddatanilai;
import com.izrael.nakulaedu.model.NewChat;
import com.izrael.nakulaedu.model.NewChatStatus;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatStatus extends RecyclerView.Adapter<ChatStatus.ViewHolder> {
    List<NewChatStatus> list;
    Context context;
    int test;
    IdChatStatus iddatanilaiadapter;
    SessionManager sessionManager;
    private List<DetailChatStatus> results;
    ApiInterface mApiInterface;
    public ChatStatus(Context contextt, List<NewChatStatus> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public ChatStatus.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.statuschatlist,parent,false);
        return new ChatStatus.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatStatus.ViewHolder holder, int position) {
        final NewChatStatus nilai = list.get(position);
        holder.chatdeskripsi.setText( nilai.getChatdeskripsi());
        holder.created_at.setText( nilai.getCreatedAt());
        Glide.with(holder.itemView).load("https://admin.nakula.co.id/assets/chat/" + nilai.getGambar()).into(holder.gambar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), NewDetailChat.class);
                intent.putExtra("id_status",  String.valueOf(nilai.getIdStatuschat()));
                intent.putExtra("getChatdeskripsi",  String.valueOf(nilai.getChatdeskripsi()));
                intent.putExtra("getNamaPelajaran",  String.valueOf(nilai.getNamaPelajaran()));
                intent.putExtra("getGambar",  String.valueOf(nilai.getGambar()));
                intent.putExtra("id_guru",  String.valueOf(nilai.getIdGuru()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(holder.itemView.getContext());
        test = nilai.getIdStatuschat();
        ApiList(holder,test);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView gambar;
        TextView chatdeskripsi,created_at;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            gambar = itemView.findViewById(R.id.gambar);
            recyclerView = itemView.findViewById(R.id.reclerview);
            chatdeskripsi = itemView.findViewById(R.id.chatdeskripsi);
            created_at = itemView.findViewById(R.id.created_at);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
    private void ApiList(@NonNull final ChatStatus.ViewHolder holder, final int datanilai)  {
        results = null;
        results = new ArrayList<>();
        results.clear();
        Call<DetailChat> uploadGambar = mApiInterface.chat_status(test);
        uploadGambar.enqueue(new Callback<DetailChat>() {
            @Override
            public void onResponse(Call<DetailChat> call, Response<DetailChat> response) {
                assert response.body() != null;

                if (response.code() == 200 ){

                    results.clear();
                    results.addAll(response.body().getData());
                    iddatanilaiadapter = new IdChatStatus(holder.itemView.getContext(),results,"depan");

                    holder.recyclerView.setAdapter(iddatanilaiadapter);
                    test = 0;
                }else {

                }

            }

            @Override
            public void onFailure(Call<DetailChat> call, Throwable t) {

                Log.e("TAG", "kkonResponse: " + t);
                Log.d("", "onResponse: " + t);
            }
        });
    }
}
