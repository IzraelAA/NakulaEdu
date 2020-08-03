package com.izrael.nakulaedu.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.agrawalsuneet.dotsloader.loaders.ZeeLoader;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.izrael.nakulaedu.LoginActivity;
import com.izrael.nakulaedu.ProfilSiswaActivity;
import com.izrael.nakulaedu.R;
import com.izrael.nakulaedu.UploadActivity;
import com.izrael.nakulaedu.model.DefaultResponse;
import com.izrael.nakulaedu.rest.ApiClient;
import com.izrael.nakulaedu.rest.ApiInterface;
import com.izrael.nakulaedu.session.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class NotificationsFragment extends Fragment {

    String sname, snis, semail, shp, pass;
    TextInputLayout name, nis, email, hp, inputPassword;
    TextView name2, kelas;
    SessionManager session;
    Button Logout, update;
    private static final int PICK_IMAGE = 1;
    private static final int PERMISSION_REQUEST_STORAGE = 2;

    DatabaseReference reference;
    private Uri uri;
    private static final String TYPE_1 = "multipart";
    private static final String TYPE_2 = "base64";
    ApiInterface mApiInterface;
    ProgressBar zeeLoader;
    CircleImageView circleImageView, upload;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        session = new SessionManager(getActivity());
        Logout = root.findViewById(R.id.logout);
        name2 = root.findViewById(R.id.name2);
        kelas = root.findViewById(R.id.kelas);
        name = root.findViewById(R.id.name);
        nis = root.findViewById(R.id.nis);
        email = root.findViewById(R.id.email);
        zeeLoader = root.findViewById(R.id.zee);
        inputPassword = root.findViewById(R.id.password);
        update = root.findViewById(R.id.update);
        hp = root.findViewById(R.id.nohp);
        circleImageView = root.findViewById(R.id.circleImageView);
        upload = root.findViewById(R.id.upload);
        if (session.get_Foto().equals("default.png")) {
            Glide.with(this).load("https://admin.nakula.co.id/assets/foto_siswa/" + session.get_Foto()).into(circleImageView);
        } else {
            Glide.with(this).load("https://admin.nakula.co.id/assets/foto_siswa/" + session.get_Foto()).into(circleImageView);
        }
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        inputPassword.getEditText().setText(session.getpassword());
        name.getEditText().setText(session.get_nama());
        nis.getEditText().setText(session.get_NISN());
        hp.getEditText().setText(session.get_NoTelpon());
        name2.setText(session.get_nama());
        email.getEditText().setText(session.get_Email());
        zeeLoader.setVisibility(View.INVISIBLE);
        kelas.setText(session.getNamakelas());
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.set_is_loggedout();
                Intent intent = (new Intent(getActivity(), LoginActivity.class));
                startActivity(intent);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGallery();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeeLoader.setVisibility(View.VISIBLE);
                ApiUpdate();
            }

        });
        return root;

    }


    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                uri = data.getData();

                circleImageView.setImageURI(uri);
                if (uri != null) {
                    Bitmap bitmap = null;
                    try {
                        int MAX_HEIGHT = 1024;
                        int MAX_WIDTH = 1024;
                        final BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        InputStream imageStream = getActivity().getContentResolver().openInputStream(uri);
                        BitmapFactory.decodeStream(imageStream, null, options);
                        imageStream.close();

                        // Calculate inSampleSize
                        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);

                        // Decode bitmap with inSampleSize set
                        options.inJustDecodeBounds = false;
                        imageStream = getActivity().getContentResolver().openInputStream(uri);
                        Bitmap img = BitmapFactory.decodeStream(imageStream, null, options);

                        img = rotateImageIfRequired(getActivity(), img, uri);
                        uploadImage(img);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "You must choose the image", Toast.LENGTH_SHORT).show();
                }
            }
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
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    openGallery();
                }

                return;
            }
        }
    }

    private File createTempFile(Bitmap bitmap) {
        File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
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
        map.put("nis", createPartFromString(session.get_NISN()));
//convert gambar jadi File terlebih dahulu dengan memanggil createTempFile yang di atas tadi.
        final File file = createTempFile(gambarbitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);

// finally, kirim map dan body pada param interface retrofit
        Call<ResponseBody> call = mApiInterface.uploadgambar(body, map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    reference = FirebaseDatabase.getInstance().getReference("Users").child(session.get_ID_SISWA());
                    HashMap<String, String> hashmap = new HashMap<>();
                    hashmap.put("id",session.get_ID_SISWA());
                    hashmap.put("username",session.get_nama());
                    hashmap.put("search",session.get_nama().toLowerCase());
                    hashmap.put("imageUrl", file.getName());
                    hashmap.put("status", "offline");
                    reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                session.set_Foto(file.getName());
                                Toast.makeText(getActivity(), "Sukses Mengupload Gambar", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void ApiUpdate() {

        sname = name.getEditText().getText().toString();
        snis = nis.getEditText().getText().toString();
        shp = hp.getEditText().getText().toString();
        semail = email.getEditText().getText().toString();
        pass = inputPassword.getEditText().getText().toString();
        Call<DefaultResponse> updatesiswa = mApiInterface.updatesiswa(semail, sname, shp, snis, pass);
        updatesiswa.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 200) {
                    reference = FirebaseDatabase.getInstance().getReference("Users").child(session.get_ID_SISWA());
                    HashMap<String, String> hashmap = new HashMap<>();
                    hashmap.put("id",session.get_ID_SISWA());
                    hashmap.put("username",sname);
                    hashmap.put("search",sname.toLowerCase());
                    hashmap.put("imageUrl",  session.get_Foto());
                    hashmap.put("status", "offline");
                    reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                name2.setText(sname);
                                session.set_nama(sname);
                                session.set_Email(semail);
                                session.set_NISN(snis);
                                session.set_NoTelpon(shp);
                                session.set_password(pass);
                                Toast.makeText(getActivity(), "Berhasil di update", Toast.LENGTH_LONG).show();
                                zeeLoader.setVisibility(View.INVISIBLE

                                );
                            } else {

                                zeeLoader.setVisibility(View.INVISIBLE

                                );
                                Toast.makeText(getActivity(), "Gagal update", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    zeeLoader.setVisibility(View.INVISIBLE

                    );
                    Toast.makeText(getActivity(), "Gagal update", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }
}
