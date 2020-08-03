package com.izrael.nakulaedu.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.izrael.nakulaedu.AbsensiActivity;
import com.izrael.nakulaedu.BuildConfig;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.absen;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CekDashboard extends Fragment {
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    private static final String TAG = AbsensiActivity.class.getSimpleName();
    CircleImageView imageView;
    static File mediaFile;
    Bitmap bitmap, decoded;
    Uri fileUri;
    ApiInterface mApiInterface;
    SessionManager sessionManager;
    String longtitude, latitude;
    Button upload, ambilgambar;
    public static final int REQUEST_IMAGE1 = 1210;
    public static final int REQUEST_IMAGE2222 = 14520;
    public final int REQUEST_CAMERA = 0;
    ProgressBar pg;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        upload = root.findViewById(R.id.upload);
        imageView = root.findViewById(R.id.imgphoto);
        ambilgambar = root.findViewById(R.id.ambilgambar);
        pg = root.findViewById(R.id.pg);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(getActivity());

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload.setEnabled(false);
                pg.setVisibility(View.VISIBLE);
            }
        });
        ambilgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

ambilfoto();
            }
        });

        if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ambilgambar.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
        return root;
    }

    private void ambilfoto() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = Uri.fromFile(getOututMediaFile());
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private File getOututMediaFile() {
        File mediaStorage = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES),"Nakula"
        );
        if (!mediaStorage.exists()){
            if (!mediaStorage.mkdirs()){
                return  null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        return new File(mediaStorage.getPath() + File.separator + "nakula" + timeStamp + ".jpg");


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
                    imageView.setImageURI(fileUri);
                    imageView.setVisibility(View.VISIBLE);


        }
    }
}
