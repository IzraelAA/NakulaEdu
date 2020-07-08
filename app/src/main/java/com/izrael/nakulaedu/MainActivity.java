package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
//package com.izrael.nakulaedu;
//
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.core.app.ActivityCompat;
//        import androidx.core.content.ContextCompat;
//
//        import android.app.Activity;
//        import android.app.AlertDialog;
//        import android.content.DialogInterface;
//        import android.content.Intent;
//        import android.content.pm.PackageManager;
//        import android.graphics.Bitmap;
//        import android.graphics.BitmapFactory;
//        import android.net.Uri;
//        import android.os.Bundle;
//        import android.os.Environment;
//        import android.provider.MediaStore;
//        import android.util.Base64;
//        import android.util.Log;
//        import android.view.View;
//        import android.widget.*;
//
//        import java.io.ByteArrayInputStream;
//        import java.io.ByteArrayOutputStream;
//        import java.io.File;
//        import java.io.IOException;
//        import java.text.SimpleDateFormat;
//        import java.util.Date;
//        import java.util.Locale;
//
//        import okhttp3.Callback;
//        import okhttp3.MediaType;
//        import okhttp3.MultipartBody;
//        import okhttp3.RequestBody;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//
//    private static final int PICK_IMAGE = 1;
//    private static final int PERMISSION_REQUEST_STORAGE = 2;
//
//    private static final String TYPE_1 = "multipart";
//    private static final String TYPE_2 = "base64";
//
//    private ImageView imgThumb;
//
//    private Button btnChoose;
//    private Button btnUpload1;
//    private Button btnUpload2;
//
//    private UploadService uploadService;
//    private Uri uri;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        imgThumb = (ImageView) findViewById(R.id.img_thumb);
//        btnChoose = (Button) findViewById(R.id.btn_choose);
//        btnUpload1 = (Button) findViewById(R.id.btn_upload_1);
//        btnUpload2 = (Button) findViewById(R.id.btn_upload_2);
//
//        btnChoose.setOnClickListener(this);
//        btnUpload1.setOnClickListener(this);
//        btnUpload2.setOnClickListener(this);
//    }
//
//    private void choosePhoto() {
//        openGallery();
//    }
//
//    public void openGallery() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
//    }
//
//    @Override
//    public void onClick(View view) {
//        if(view == btnChoose) {
//            choosePhoto();
//        }else if(view == btnUpload1) {
//            if(uri != null) {
//                File file = FileUtils.getFile(this, uri);
//                uploadMultipart(file);
//            }else{
//                Toast.makeText(this, "You must choose the image", Toast.LENGTH_SHORT).show();
//            }
//        }else if(view == btnUpload2) {
//            if(uri != null) {
//                Bitmap bitmap = null;
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                String encoded = bitmapToBase64String(bitmap, 100);
//                uploadBase64(encoded);
//            }else{
//                Toast.makeText(this, "You must choose the image", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    public static String bitmapToBase64String(Bitmap bmp, int quality) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//        byte[] bytes = baos.toByteArray();
//        return Base64.encodeToString(bytes, Base64.DEFAULT);
//    }
//    private void uploadMultipart(File file) {
//        RequestBody photoBody = RequestBody.create(MediaType.parse("image/*"), file);
//        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("photo",
//                file.getName(), photoBody);
//
//        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), TYPE_1);
//
//        uploadService = new UploadService();
//        uploadService.uploadPhotoMultipart(action, photoPart, new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                BaseResponse baseResponse = (BaseResponse) response.body();
//
//                if(baseResponse != null) {
//                    Toast.makeText(MainActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//
//    private void uploadBase64(String imgBase64) {
//        uploadService = new UploadService();
//        uploadService.uploadPhotoBase64(TYPE_2, imgBase64, new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                BaseResponse baseResponse = (BaseResponse) response.body();
//
//                if(baseResponse != null) {
//                    Toast.makeText(MainActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
//            if(data != null) {
//                uri = data.getData();
//
//                imgThumb.setImageURI(uri);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_STORAGE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    openGallery();
//                }
//
//                return;
//            }
//        }
//    }
//
//}
