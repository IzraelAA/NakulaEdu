package com.izrael.nakulaedu.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.izrael.nakulaedu.Chat;
import com.izrael.nakulaedu.ChatActivity;
import com.izrael.nakulaedu.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context    context;
    private List<User> users;
    private boolean    ischat;
    String theLastMsg;

    public UserAdapter(Context contextt, List<User> userss, boolean ischat) {
        this.context = contextt;
        this.users = userss;
        this.ischat = ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_users, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = users.get(position);

        holder.username.setText(user.getUsername());
        Glide.with(holder.itemView.getContext()).load("https://admin.nakula.co.id/assets/foto_siswa/" + user.getImageUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("userid", user.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  TextView  username;
        public  ImageView imageView;
        private ImageView imgon, imgoff;
        private TextView lmg;

        private ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.textUsername);
            imageView = view.findViewById(R.id.userImageMenu);
            imgon = view.findViewById(R.id.imgeon);
            imgoff = view.findViewById(R.id.imgeoff);
            lmg = view.findViewById(R.id.lmg);

        }
    }

}
