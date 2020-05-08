package com.izrael.nakulaedu.rest;


import com.izrael.nakulaedu.classmodel.MapelClass;
import com.izrael.nakulaedu.classmodel.QuizUjian;
import com.izrael.nakulaedu.model.Bahan;
import com.izrael.nakulaedu.model.DefaultResponse;
import com.izrael.nakulaedu.model.GetAuth;
import com.izrael.nakulaedu.model.GetJadwal;
import com.izrael.nakulaedu.model.GetTahun;
import com.izrael.nakulaedu.model.PhotoUploadModel;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

    @FormUrlEncoded
    @POST("upload.php")
    Call<ResponseBody> uploadGambar(
            @Field("nis") String nis,
            @Field("location") String location,
            @Field("url_gambar") String url_gambar,
            @Field("kode_kehadiran") String kode_kehadiran
    );

    @FormUrlEncoded
    @POST("api/api_tahun.php")
    Call<GetTahun> api_tahun(
            @Field("tahun") String tahun
    );

    @FormUrlEncoded
    @POST("api/api_list_tugas.php")
    Call<QuizUjian> quiz(
            @Field("kode_kelas") String kode_kelas
    );

    @FormUrlEncoded
    @POST("api/api_jadwal.php")
    Call<GetJadwal> jadwal(
            @Field("nis") String nis,
            @Field("kode_kelas") String kode_kelas,
            @Field("tahun") String tahun
    );

    @FormUrlEncoded
    @POST("api/api_bahan_tugasview.php")
    Call<MapelClass> mapel(
            @Field("nis") String nis,
            @Field("kode_kelas") String kode_kelas,
            @Field("kode_kurikulum") String kode_kurikulum,
            @Field("tahun") String tahun
    );

    @FormUrlEncoded
    @POST("api/api_bahan_tugas.php")
    Call<Bahan> bahan(
            @Field("nis") String nis,
            @Field("kodejdwl") String kodejdwl
    );

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
