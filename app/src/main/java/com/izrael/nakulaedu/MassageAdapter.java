package com.izrael.nakulaedu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.izrael.nakulaedu.session.SessionManager;

import java.util.List;

public class MassageAdapter extends RecyclerView.Adapter<MassageAdapter.ViewHolder> {
    private             Context    context;
    public static final int        MSG_LEFT  = 0;
    public static final int        MSG_RIGHT = 1;
    private             List<Chat> chats;
    private             String     imageurl;
    SessionManager session;

    public MassageAdapter(Context contextt, List<Chat> chats, String imageurl) {
        this.context = contextt;
        this.chats = chats;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MassageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_RIGHT) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent, false);
            return new MassageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false);
            return new MassageAdapter.ViewHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull MassageAdapter.ViewHolder holder, int position) {
        Chat chat = chats.get(position);
            holder.showMassage.setText(chat.getMessage());
//            if (imageurl.equals("default")){
//                holder.imageView.setImageResource(R.mipmap.ic_launcher);
//            }else {
//                Glide.with(context).load(imageurl).into(holder.imageView);
//            }
            if (position == chats.size()-1){
                if (chat.getIsseen()){
                    holder.textseen.setText("Seen");
                }else {
                    holder.textseen.setText("Delivered");
                }
            }else {
                holder.textseen.setVisibility(View.GONE);
            }

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  showMassage,textseen;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            showMassage = view.findViewById(R.id.showmassage);
            imageView = view.findViewById(R.id.imageprofilchat);
            textseen = view.findViewById(R.id.textStatus);
        }
    }

    @Override
    public int getItemViewType(int position) {
        session = new SessionManager(context);
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (chats.get(position).getSender().equals(session.get_ID_SISWA())) {
            return MSG_RIGHT;
        } else {
            return MSG_LEFT;
        }
    }
}