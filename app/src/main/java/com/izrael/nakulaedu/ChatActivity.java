package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.izrael.nakulaedu.fragment.User;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {
    CircleImageView    circleImageView;
    TextView           textView;
    EditText           editText;
    DatabaseReference  reference;
    List<Chat>         chats;
    RecyclerView       recyclerView;
    Intent             intent;
    MassageAdapter     massageAdapter;
    ImageButton        imageButton;
    SessionManager     session;
    ValueEventListener seenListener;
    String             userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        session = new SessionManager(ChatActivity.this);

        Toolbar toolbar = findViewById(R.id.toolbarmassage);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.reclerViewmasssage);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        textView = findViewById(R.id.text_profilemassage);
        editText = findViewById(R.id.edit_send);
        imageButton = findViewById(R.id.btn_send);
        intent = getIntent();
        userid = intent.getStringExtra("userid");
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (!msg.equals("")) {
                    sendMessage(session.get_ID_SISWA(), userid, msg);
                } else {
                    Toast.makeText(ChatActivity.this, "Don't Send Empety Message", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");
            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                textView.setText(user.getUsername());

                readMassage(session.get_ID_SISWA(), userid, user.getImageUrl());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        seenMessage(userid);
    }

    private void seenMessage(final String userid) {
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        seenListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReciver().equals(session.get_ID_SISWA()) && chat.getSender().equals(userid)) {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("isseen", true);
                        snapshot.getRef().updateChildren(hashMap);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendMessage(String sender, final String reciver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("reciver", reciver);
        hashMap.put("message", message);
        hashMap.put("isseen", false);
        reference.child("Chats").push().setValue(hashMap);
        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chatlist")
                .child(session.get_ID_SISWA())
                .child(userid);
        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    chatRef.child("id").setValue(userid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final DatabaseReference chatRef1 = FirebaseDatabase.getInstance().getReference("Chatlist")
                .child(userid)
                .child(session.get_ID_SISWA());
        chatRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    chatRef1.child("id").setValue(session.get_ID_SISWA());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void readMassage(final String myid, final String userid, final String imageurl) {
        chats = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chats.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReciver().equals(myid) && chat.getSender().equals(userid) ||
                            chat.getReciver().equals(userid) && chat.getSender().equals(myid)) {
                        chats.add(chat);
                    }
                    massageAdapter = new MassageAdapter(ChatActivity.this, chats, imageurl);
                    recyclerView.setAdapter(massageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void status(String status) {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(session.get_ID_SISWA());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);
        reference.updateChildren(hashMap);
    }
}