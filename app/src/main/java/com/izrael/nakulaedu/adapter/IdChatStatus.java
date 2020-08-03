package com.izrael.nakulaedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.DetailChatStatus;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IdChatStatus  extends RecyclerView.Adapter<IdChatStatus.ViewHolder> {
    List<DetailChatStatus> list;
    String cek;
    Context context;
    public IdChatStatus(Context contextt, List<DetailChatStatus> list,String cek) {
        this.context = contextt;
        this.list = list;
        this.cek = cek;
    }

    @NonNull
    @Override
    public IdChatStatus.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.idchatstatus,parent,false);
        return new IdChatStatus.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final IdChatStatus.ViewHolder holder, int position) {
        final DetailChatStatus MapelResult = list.get(position);
        if (cek.equals("depan")){
            holder.gambarmassage.setVisibility(View.GONE);
            holder.gambar.setVisibility(View.GONE);
        }else {
            if (MapelResult.getTipe().equals("gambar")){
                holder.gambarmassage.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView).load("https://admin.nakula.co.id/assets/chat/" + MapelResult.getFile()).into(holder.gambarmassage);
            }else {

                holder.gambarmassage.setVisibility(View.GONE);
            }
        }
        if (MapelResult.getIdSiswa() == 439){
            holder.nama.setText(MapelResult.getNamaGuru());
            Glide.with(holder.itemView).load("https://admin.nakula.co.id/assets/foto_siswa/" + MapelResult.getPicture()).into(holder.gambar);
        }else {
            holder.nama.setText(MapelResult.getNamaSiswa());
            Glide.with(holder.itemView).load("https://admin.nakula.co.id/assets/foto_siswa/" + MapelResult.getFoto()).into(holder.gambar);
        }
        holder.created_at.setText(MapelResult.getCreatedAt());
        holder.massage.setText(MapelResult.getMassage());
    }

    @Override
    public int getItemCount() {
        if (cek.equals("depan")){
            if (list.size() >2){
                return 2;
            }else {
                return  list.size();
            }
        }else {
            return list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView massage;
        TextView nama;
        TextView created_at;
        CircleImageView gambar;
        ImageView gambarmassage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            created_at = itemView.findViewById(R.id.created_at);
            massage = itemView.findViewById(R.id.massage);
            nama = itemView.findViewById(R.id.nama);
            gambar = itemView.findViewById(R.id.gambar);
            gambarmassage = itemView.findViewById(R.id.gambarmassage);
        }
    }
}
