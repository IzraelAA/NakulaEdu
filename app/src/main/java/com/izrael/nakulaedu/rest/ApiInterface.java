package com.izrael.nakulaedu.rest;


import com.izrael.nakulaedu.classmodel.DataNilai;
import com.izrael.nakulaedu.classmodel.DataQuiz;
import com.izrael.nakulaedu.classmodel.IdRaport;
import com.izrael.nakulaedu.classmodel.Idnilai;
import com.izrael.nakulaedu.classmodel.Login;
import com.izrael.nakulaedu.classmodel.MapelClass;
import com.izrael.nakulaedu.classmodel.QuizUjian;
import com.izrael.nakulaedu.classmodel.Raport;
import com.izrael.nakulaedu.model.Bahan;
import com.izrael.nakulaedu.model.DefaultResponse;
import com.izrael.nakulaedu.model.GetAuth;
import com.izrael.nakulaedu.model.GetJadwal;
import com.izrael.nakulaedu.model.GetTahun;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("absensi")
    Call<ResponseBody> uploadGambar(
            @Field("id_siswa") String nis,
            @Field("longtitude") String location,
            @Field("latitude") String latitude,
            @Field("gambar") String gambar
    );

    @FormUrlEncoded
    @POST("api/api_tahun.php")
    Call<GetTahun> api_tahun(
            @Field("tahun") String tahun
    );

    @FormUrlEncoded
    @POST("data-nilai")
    Call<DataNilai> quiz(
            @Field("id_kelas") int id_kelas
    );

    @FormUrlEncoded
    @POST("data-quiz")
    Call<DataQuiz> dataquiz(
            @Field("id_kelas") int id_kelas
    );

    @FormUrlEncoded
    @POST("soal-quiz")
    Call<QuizUjian> soalquiz(
            @Field("id_dataquiz") int id_dataquiz
    );

    @FormUrlEncoded
    @POST("jadwal")
    Call<GetJadwal> jadwal(
            @Field("id_kelas") int id_kelas
    );
    @FormUrlEncoded
    @POST("raport")
    Call<Raport> raport(
            @Field("id_siswa") int id_siswa
    );
    @FormUrlEncoded
    @POST("detailraport")
    Call<IdRaport> detailraport(
            @Field("id_nialiraport") int id_nialiraport
    );

    @FormUrlEncoded
    @POST("mapel")
    Call<MapelClass> mapel(
            @Field("id_kelas") int id_kelas
    );

    @FormUrlEncoded
    @POST("api/api_bahan_tugas.php")
    Call<Bahan> bahan(
            @Field("nis") String nis,
            @Field("kodejdwl") String kodejdwl
    );
    @FormUrlEncoded
    @POST("nilai")
    Call<Idnilai> nilai(
            @Field("id_datanilai") int id_datanilai,
            @Field("id_siswa") int id_siswa
    );

    @FormUrlEncoded
    @POST("login")
    Call<Login> getLogin(@Field("nama_siswa") String nama_siswa, @Field("password") String password);


}
