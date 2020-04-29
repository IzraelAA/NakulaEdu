package com.izrael.nakulaedu.rest;


import com.izrael.nakulaedu.model.DefaultResponse;
import com.izrael.nakulaedu.model.GetAuth;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("auth/register")
    Call<DefaultResponse> register_pelanggan(
            @Field("nik") String nik,
            @Field("nama") String nama,
            @Field("gender") String gender,
            @Field("kontak") String kontak,
            @Field("password") String password
    );

    @Multipart
    @POST("upload.php")
    Call<RequestBody> uploadGambar(@Part MultipartBody.Part body);

    @FormUrlEncoded
    @POST("api.php")
    Call<GetAuth> getPelanggan(@Field("nis") String nis, @Field("password") String password);

    @FormUrlEncoded
    @POST("profile/change_password")
    Call<DefaultResponse> changePasswowd(
            @Field("id_pelanggan") String id_pelanggan,
            @Field("nik") String nik,
            @Field("password") String password,
            @Field("new_password") String new_password
    );

    @FormUrlEncoded
    @POST("auth/konfirmasi_pendaftaran")
    Call<DefaultResponse> konfirmasi_pendaftaran(@Field("kontak") String kontak, @Field("kode") String kode);

    @FormUrlEncoded
    @POST("profile/get_saldo")
    Call<DefaultResponse> get_balance(@Field("id_pelanggan") String id_pelanggan, @Field("nik") String nik);
}
