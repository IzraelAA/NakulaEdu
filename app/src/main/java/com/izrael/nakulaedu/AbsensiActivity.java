package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
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
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.*;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.izrael.nakulaedu.model.PhotoUploadModel;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AbsensiActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";
    private static File mediaStorageDir;
    private Uri imageUrl;
    private StorageTask uploadTask;
    private static final String TAG = AbsensiActivity.class.getSimpleName();
    private static final int CAMERA_REQUEST_CODE = 7777;
    CircleImageView imageView;
    int bitmap_size = 20;
    static File mediaFile;
    Bitmap bitmap, decoded;
    Uri fileUri;
    String longtitude;
    int max_resolution_image = 200;
    Button upload, ambilgambar;
    public final static String BASE_URL = "http://siakad.nakula.co.id/";
    public static final int REQUEST_IMAGE = 100;
    public static final int REQUEST_IMAGE111 = 100;
    public static final int REQUEST_IMAGE222 = 100;
    public final int REQUEST_CAMERA = 0;
    ProgressBar pg;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);
        upload = findViewById(R.id.upload);
        imageView = findViewById(R.id.imgphoto);
        ambilgambar = findViewById(R.id.ambilgambar);
        pg = findViewById(R.id.pg);

        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            upload.setEnabled(true);
        } else {
            EasyPermissions.requestPermissions(this, "Izinkan Aplikasi Mengakses Storage?", REQUEST_IMAGE, Manifest.permission.CAMERA);
            upload.setEnabled(false);
        }
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            upload.setEnabled(true);
        } else {
            EasyPermissions.requestPermissions(this, "Izinkan Aplikasi Mengakses Storage?", REQUEST_IMAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
            upload.setEnabled(false);
        }
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            upload.setEnabled(true);
        } else {
            EasyPermissions.requestPermissions(this, "Izinkan Aplikasi Mengakses Storage?", REQUEST_IMAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            upload.setEnabled(false);
        }
        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            upload.setEnabled(true);
        } else {
            EasyPermissions.requestPermissions(this, "Izinkan Aplikasi Mengakses Storage?", REQUEST_IMAGE111, Manifest.permission.ACCESS_FINE_LOCATION);
            upload.setEnabled(false);
        }

        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            upload.setEnabled(true);
        } else {
            EasyPermissions.requestPermissions(this, "Izinkan Aplikasi Mengakses Storage?", REQUEST_IMAGE222, Manifest.permission.ACCESS_COARSE_LOCATION);
            upload.setEnabled(false);
        }
        sessionManager = new SessionManager(AbsensiActivity.this);
        StorageRef = FirebaseStorage.getInstance().getReference("uploads");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(this, ALLOW_KEY)) {
                showSettingsAlert();
            } else if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {
                    showAlert();
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }
        }
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(AbsensiActivity.this);
        mFusedLocation.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            longtitude = String.valueOf(location.getLongitude() + location.getLatitude());
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
                fileUri = FileProvider.getUriForFile(AbsensiActivity.this, BuildConfig.APPLICATION_ID + ".provider", getOutputMediaFile());
//                fileUri = getOutputMediaFileUri();
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });
    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {

        // External sdcard locationthis.getContentResolver().
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

        Cursor cursor = this.getContentResolver().query(contentURI, null, null, null, null);
        String result;

        // for API 19 and above
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {


            cursor.moveToFirst();
            String image_id = cursor.getString(0);
            image_id = image_id.substring(image_id.lastIndexOf(":") + 1);
            cursor.close();

            cursor = this.getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);

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
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<ResponseBody> uploadGambar = service.uploadGambar(sessionManager.get_NISN(), longtitude, file.getName(), "h");
        uploadGambar.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                upload.setEnabled(true);
                pg.setVisibility(View.GONE);
                Toast.makeText(AbsensiActivity.this, "Sukses..!!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AbsensiActivity.this, Dashboard.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                upload.setEnabled(true);
                pg.setVisibility(View.GONE);
                Log.d(TAG, "onFailure: " + t);
                Toast.makeText(AbsensiActivity.this, "Terabsen..!!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AbsensiActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }

    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(AbsensiActivity.this).create();
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
                        startInstalledAppDetailsActivity(AbsensiActivity.this);
                    }
                });

        alertDialog.show();
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
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
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(fileUri));
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
        AlertDialog alertDialog = new AlertDialog.Builder(AbsensiActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(AbsensiActivity.this,
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
                                        this, permission);

                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            // user denied flagging NEVER ASK AGAIN
                            // you can either enable some fall back,
                            // disable features of your app
                            // or open another dialog explaining
                            // again the permission and directing to
                            // the app setting
                            saveToPreferences(AbsensiActivity.this, ALLOW_KEY, true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
