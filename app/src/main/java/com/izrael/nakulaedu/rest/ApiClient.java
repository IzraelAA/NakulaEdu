package com.izrael.nakulaedu.rest;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //    private static final String BASE_URL = "http://192.168.1.101/gumukrejo/api/";
    private static final String   BASE_URL = "https://testing.nakula.co.id/api/";
    private static       Retrofit retrofit = null;


    public ApiClient() {
    }

    public static Retrofit getClient()  {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
