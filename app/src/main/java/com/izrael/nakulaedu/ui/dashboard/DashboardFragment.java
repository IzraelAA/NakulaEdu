package com.izrael.nakulaedu.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.izrael.nakulaedu.AbsensiActivity;
import com.izrael.nakulaedu.BuildConfig;
import com.izrael.nakulaedu.Dashboard;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.model.absen;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    public static final    int         MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final  String      ALLOW_KEY                     = "ALLOWED";
    public static final  String      CAMERA_PREF                   = "camera_pref";
    private static       File        mediaStorageDir;
    private              Uri         imageUrl;
    private              StorageTask uploadTask;
    private static final String      TAG                           = AbsensiActivity.class.getSimpleName();
    private static final int         CAMERA_REQUEST_CODE           = 7777;
    CircleImageView imageView;
    int             bitmap_size = 20;
    static File mediaFile;
    Bitmap bitmap, decoded;
    Uri          fileUri;
    ApiInterface mApiInterface;
    String       longtitude,latitude;
    int    max_resolution_image = 200;
    Button upload, ambilgambar;
    public final static String BASE_URL         = "http://siakad.nakula.co.id/";
    public static final int    REQUEST_IMAGE    = 120;
    public static final int    REQUEST_IMAGE1    = 1210;
    public static final int    REQUEST_IMAGE111 = 1330;
    public static final int    REQUEST_IMAGE222 = 1450;
    public static final int    REQUEST_IMAGE2222 = 14520;
    public final        int    REQUEST_CAMERA   = 0;
    ProgressBar    pg;
    SessionManager sessionManager;
    private StorageReference StorageRef;

    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }

    public static void startInstalledAppDetailsActivity(final Activity context) {
        if (context == null) {
            return;
        }

        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }

    public static void saveToPreferences(Context context, String key, Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View           root     = inflater.inflate(R.layout.fragment_dashboard, container, false);

    upload = root.findViewById(R.id.upload);
    imageView = root.findViewById(R.id.imgphoto);
    ambilgambar = root.findViewById(R.id.ambilgambar);
    pg = root.findViewById(R.id.pg);

    mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    sessionManager = new SessionManager(getActivity());
    StorageRef = FirebaseStorage.getInstance().getReference("uploads");
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        if (getFromPref(getActivity(), ALLOW_KEY)) {
            showSettingsAlert();
        } else if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (
                    ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.CAMERA)) {
                showAlert();
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }
    }
    cek5();
    FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocation.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            // Got last known location. In some rare situations getActivity() can be null.
            if (location != null) {
                longtitude = String.valueOf(location.getLongitude() );
                latitude = String.valueOf(location.getLatitude());
                // Logic to handle location object
            }
        }
    });
        upload.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            upload.setEnabled(false);
            pg.setVisibility(View.VISIBLE);
            uploadFile(decoded);
        }
    });
        ambilgambar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", getOutputMediaFile());
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    });
        return root;
} 
 
    private void cek5() {
        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            upload.setEnabled(true);
        } else {
            EasyPermissions.requestPermissions(getActivity(), "Izinkan Aplikasi Mengakses Storage?", REQUEST_IMAGE1, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            upload.setEnabled(false);
        }
        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            upload.setEnabled(true);
        } else {
            EasyPermissions.requestPermissions(getActivity(), "Izinkan Aplikasi Mengakses Storage?", REQUEST_IMAGE2222, Manifest.permission.READ_EXTERNAL_STORAGE);
            upload.setEnabled(false);
        }



    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {

        // External sdcard locationgetActivity().getContentResolver().
        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "nakula");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "nakula" + timeStamp + ".jpg");

        return mediaFile;
    }

    private String getRealPathFromURIPath(Uri contentURI) {

        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        String result;

        // for API 19 and above
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {


            cursor.moveToFirst();
            String image_id = cursor.getString(0);
            image_id = image_id.substring(image_id.lastIndexOf(":") + 1);
            cursor.close();

            cursor = getActivity().getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);

        }
        if (cursor != null && cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex("ContactNumber"));
            cursor.close();
        }
        result = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return result;
    }

    void uploadFile(Bitmap gambarbitmap) {

        File file = mediaFile;


        Log.d("File", "" + file.getName());
        Call<absen> uploadGambar = mApiInterface.uploadGambar(sessionManager.get_ID_SISWA(),sessionManager.get_KODEKELAS(), longtitude,latitude, file.getName());
        uploadGambar.enqueue(new Callback<absen>() {
            @Override
            public void onResponse(Call<absen> call, Response<absen> response) {
                upload.setEnabled(true);
                pg.setVisibility(View.GONE);
                Log.d(TAG, "onResponse: " +response);
                Toast.makeText(getActivity(),response.body().getStatus().getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<absen> call, Throwable t) {
                upload.setEnabled(true);
                pg.setVisibility(View.GONE);
                Log.d(TAG, "onFailure: " + t);
                Toast.makeText(getActivity(), "gagal", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startInstalledAppDetailsActivity(getActivity());
                    }
                });

        alertDialog.show();
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width  = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    Log.d("CAMERACAMERA", fileUri.getPath());
                    bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(fileUri));
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap(decoded);
    }

    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getActivity().finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];

                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean
                                showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        getActivity(), permission);

                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            saveToPreferences(getActivity(), ALLOW_KEY, true);
                        }
                    }
                }
            }
        }
    }

}
