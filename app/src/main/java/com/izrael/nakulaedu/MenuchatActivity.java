package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.izrael.nakulaedu.fragment.chatFragment;
import com.izrael.nakulaedu.fragment.usersFragment;
import com.izrael.nakulaedu.session.SessionManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuchatActivity extends AppCompatActivity {
    TextView textView, toolbartext;
    SessionManager    session;
//    GoogleSignInClient mGoogleSignInClient;
    DatabaseReference reference;
//    FirebaseUser       firebaseUser;
    CircleImageView   profile_image;
    Toolbar           toolbar;
    private long backprassedtime;
    TabLayout tabLayout;
    ViewPager viewPager;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuchat);
        toolbar = findViewById(R.id.toolbarmenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        session = new SessionManager(MenuchatActivity.this);

        textView = findViewById(R.id.text);
        toolbartext = findViewById(R.id.text_profile);
        profile_image = findViewById(R.id.profile_image);
        Glide.with(this).load("https://admin.nakula.co.id/assets/foto_siswa/" + session.get_Foto()).into(profile_image);

        viewPager = findViewById(R.id.ViewPager);
        tabLayout = findViewById(R.id.tablayout_menu);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new chatFragment(), "Chats");


                viewPagerAdapter.addFragment(new usersFragment(), "Kontak");
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
                int              unread           = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReciver().equals(session.get_ID_SISWA()) && !chat.getIsseen()) {
                        unread++;
                    }
                }
                if (unread == 0) {

                    viewPagerAdapter.addFragment(new chatFragment(), "Chats");
                } else {
                    viewPagerAdapter.addFragment(new chatFragment(), "(" + unread + ")Chats");
                }
                viewPagerAdapter.addFragment(new usersFragment(), "Kontak");
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;
        private ArrayList<String>   title;

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.title = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }

        public void addFragment(Fragment fragment, String titles) {
            fragments.add(fragment);
            title.add(titles);

        }
    }


}

