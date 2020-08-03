package com.izrael.nakulaedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.izrael.nakulaedu.model.NewChatStatus;
import com.izrael.nakulaedu.model.absen;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

import static com.izrael.nakulaedu.AbsensiActivity.mediaFile;

public class UploadActivity extends AppCompatActivity {

    SessionManager sessionManager;
    ApiInterface mApiInterface;
    Button upload, ambilgambar;
    ImageView imageView;
    EditText send;
    ProgressBar pg;
    File mediaStorageDir;
    String longtitude, latitude,fileUri,cek,kode,id_guru,massage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);
        pg = findViewById(R.id.pg);
        send = findViewById(R.id.send);
        Intent i = getIntent();
        longtitude = i.getStringExtra("longtitude");
        cek = i.getStringExtra("cek");
        kode = i.getStringExtra("kode");
        id_guru = i.getStringExtra("id_guru");
        latitude = i.getStringExtra("latitude");
        fileUri = i.getStringExtra("photo");
        upload = findViewById(R.id.upload);
        imageView = findViewById(R.id.gambar);

        imageView.setImageURI(Uri.parse(fileUri));
        if (cek.equals("absensi")){

            send.setVisibility(View.GONE);
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upload.setEnabled(false);
                    pg.setVisibility(View.VISIBLE);
                    try {
                        int MAX_HEIGHT = 1024;
                        int MAX_WIDTH = 1024;
                        final BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        InputStream imageStream = getContentResolver().openInputStream(Uri.parse(fileUri));
                        BitmapFactory.decodeStream(imageStream, null, options);
                        imageStream.close();

                        // Calculate inSampleSize
                        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);

                        // Decode bitmap with inSampleSize set
                        options.inJustDecodeBounds = false;
                        imageStream = getContentResolver().openInputStream(Uri.parse(fileUri));
                        Bitmap img = BitmapFactory.decodeStream(imageStream, null, options);

                        img = rotateImageIfRequired(UploadActivity.this, img, Uri.parse(fileUri));
                        uploadImage(img);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else{
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upload.setEnabled(false);
                    pg.setVisibility(View.VISIBLE);
                    try {
                        int MAX_HEIGHT = 1024;
                        int MAX_WIDTH = 1024;
                        final BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        InputStream imageStream = getContentResolver().openInputStream(Uri.parse(fileUri));
                        BitmapFactory.decodeStream(imageStream, null, options);
                        imageStream.close();

                        // Calculate inSampleSize
                        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);

                        // Decode bitmap with inSampleSize set
                        options.inJustDecodeBounds = false;
                        imageStream = getContentResolver().openInputStream(Uri.parse(fileUri));
                        Bitmap img = BitmapFactory.decodeStream(imageStream, null, options);

                        img = rotateImageIfRequired(UploadActivity.this, img, Uri.parse(fileUri));
                        uploadsoal(img);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private static Bitmap rotateImageIfRequired(Context context, Bitmap img, Uri selectedImage) throws IOException {

        InputStream input = context.getContentResolver().openInputStream(selectedImage);
        ExifInterface ei;
        if (Build.VERSION.SDK_INT > 23)
            ei = new ExifInterface(input);
        else
            ei = new ExifInterface(selectedImage.getPath());

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }
    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }
    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    private File createTempFile(Bitmap bitmap) {
        File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() + "_image.webp");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.WEBP, 0, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }
    public void uploadImage(Bitmap gambarbitmap) {
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("id_siswa", createPartFromString(sessionManager.get_ID_SISWA()));
        map.put("id_kelas", createPartFromString(sessionManager.get_KODEKELAS()));
        map.put("longtitude", createPartFromString(longtitude));
        map.put("latitude", createPartFromString(latitude));
//convert gambar jadi File terlebih dahulu dengan memanggil createTempFile yang di atas tadi.
        final File file = createTempFile(gambarbitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);

// finally, kirim map dan body pada param interface retrofit
        Call<absen> call = mApiInterface.uploadabasen(body, map);
        call.enqueue(new Callback<absen>() {
            @Override
            public void onResponse(Call<absen> call, Response<absen> response) {
                if (response.code() == 200) {

                    Toast.makeText(UploadActivity.this, response.body().getStatus().getMessage(), Toast.LENGTH_LONG).show();

                    Intent i = new Intent(UploadActivity.this,NewActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(UploadActivity.this, "Belum Ada Jadwal", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(UploadActivity.this,NewActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<absen> call, Throwable t) {

            }
        });
    }

    public void uploadsoal(Bitmap gambarbitmap) {
        massage = send.getText().toString();
                HashMap<String, RequestBody> map = new HashMap<>();
        map.put("id_siswa", createPartFromString(sessionManager.get_ID_SISWA()));
        map.put("id_kelas", createPartFromString(sessionManager.get_KODEKELAS()));
        map.put("id_statuschat", createPartFromString(kode));
        map.put("massage", createPartFromString(massage));
        map.put("id_guru", createPartFromString(id_guru));
//convert gambar jadi File terlebih dahulu dengan memanggil createTempFile yang di atas tadi.
        final File file = createTempFile(gambarbitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);

// finally, kirim map dan body pada param interface retrofit
        Call<absen> call = mApiInterface.balas_chatfile(body, map);
        call.enqueue(new Callback<absen>() {
            @Override
            public void onResponse(Call<absen> call, Response<absen> response) {
                if (response.code() == 200) {

                    Toast.makeText(UploadActivity.this, response.body().getStatus().getMessage(), Toast.LENGTH_LONG).show();

                    Intent i = new Intent(UploadActivity.this, NewChatActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(UploadActivity.this, "Belum Ada Jadwal", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(UploadActivity.this,NewActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<absen> call, Throwable t) {

            }
        });
    }

    void uploadFile() {

        Call<absen> uploadGambar = mApiInterface.uploadGambar(sessionManager.get_ID_SISWA(), sessionManager.get_KODEKELAS(), longtitude, latitude, fileUri);
        uploadGambar.enqueue(new Callback<absen>() {
            @Override
            public void onResponse(Call<absen> call, Response<absen> response) {
                pg.setVisibility(View.GONE);
                if (response.code() == 200) {
                    Toast.makeText(UploadActivity.this, response.body().getStatus().getMessage(), Toast.LENGTH_LONG).show();

                    Intent i = new Intent(UploadActivity.this,NewActivity.class);
                    startActivity(i);
                     } else {

                    Toast.makeText(UploadActivity.this, "Belum Ada Jadwal", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(UploadActivity.this,NewActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<absen> call, Throwable t) {
                pg.setVisibility(View.GONE);
            }
        });

    }

}
