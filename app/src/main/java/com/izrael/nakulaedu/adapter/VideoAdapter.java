package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izrael.nakulaedu.DetailRaportActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.DataRaport;
import com.izrael.nakulaedu.model.VideioData;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    List<VideioData> list;
    Context context;
    SessionManager sessionManager;
    ApiInterface mApiInterface;
    public VideoAdapter(Context contextt, List<VideioData> list) {
        this.context = contextt;
        this.list = list;

    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.videolayout,parent,false);
        return new VideoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoAdapter.ViewHolder holder, int position) {
        final VideioData nilai = list.get(position);
        holder.hari.setText(nilai.getHari());
        holder.link.setText(nilai.getLink());
        holder.deskripsi.setText(nilai.getDeskripsivid());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(nilai.getLink()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView deskripsi,link,hari;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            link = itemView.findViewById(R.id.link);
            hari = itemView.findViewById(R.id.hari);
        }
    }
}
