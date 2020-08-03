package com.izrael.nakulaedu.rest;


import android.provider.MediaStore;

import com.izrael.nakulaedu.classmodel.DataKantin;
import com.izrael.nakulaedu.classmodel.DataNilai;
import com.izrael.nakulaedu.classmodel.DataNotifikasi;
import com.izrael.nakulaedu.classmodel.DataPembayaran;
import com.izrael.nakulaedu.classmodel.DataQuiz;
import com.izrael.nakulaedu.classmodel.DetailChat;
import com.izrael.nakulaedu.classmodel.DetialMapel;
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
import com.izrael.nakulaedu.model.NewChat;
import com.izrael.nakulaedu.model.NewChatStatus;
import com.izrael.nakulaedu.model.absen;
import com.izrael.nakulaedu.model.pgquizdata;
import com.izrael.nakulaedu.model.video;

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


    @Multipart
    @POST("uploadabsensi")
    Call<absen> uploadabasen(@Part MultipartBody.Part photo,
                             @PartMap Map<String,RequestBody> text);
    @Multipart
    @POST("balas_chatfile")
    Call<absen> balas_chatfile(@Part MultipartBody.Part photo,
                             @PartMap Map<String,RequestBody> text);
    @FormUrlEncoded
    @POST("balas_chat")
    Call<ResponseBody> chatkirim(
            @Field("id_siswa") String id_siswa,
            @Field("id_kelas") String id_kelas,
            @Field("id_guru") String id_guru,
            @Field("massage") String massage,
            @Field("id_statuschat") String id_statuschat
    );
    @FormUrlEncoded
    @POST("absensi")
    Call<absen> uploadGambar(
            @Field("id_siswa") String nis,
            @Field("id_kelas") String id_kelas,
            @Field("longtitude") String location,
            @Field("latitude") String latitude,
            @Field("gambar") String gambar
    );


    @FormUrlEncoded
    @POST("jawaban_quiz")
    Call<ResponseBody> jawaban_quiz(
            @Field("id_dataquiz") String id_dataquiz,
            @Field("id_kelas") String id_kelas,
            @Field("jawaban") String jawaban,
            @Field("id_quiz") String id_quiz,
            @Field("id_siswa") String id_siswa,
            @Field("nilai") String nilai
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
    @POST("chat")
    Call<NewChat> chat(
            @Field("id_kelas") int id_kelas
    );
    @FormUrlEncoded
    @POST("chat_status")
    Call<DetailChat> chat_status(
            @Field("id_statuschat") int id_statuschat
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
    @POST("soal_quizpgnomer")
    Call<pgquizdata> soal_quizpgnomer(
            @Field("id_dataquiz") int id_dataquiz,
            @Field("nomer") int nomer
    );

    @FormUrlEncoded
    @POST("jadwal")
    Call<GetJadwal> jadwal(
            @Field("id_kelas") int id_kelas
    );
    @FormUrlEncoded
    @POST("jadwalhari")
    Call<GetJadwal> jadwalhari(
            @Field("id_kelas") int id_kelas,
            @Field("hari") String hari
    );
    @FormUrlEncoded
    @POST("online")
    Call<ResponseBody> online(
            @Field("nis") String nis,
            @Field("statussiswa") String statussiswa
    );
    @FormUrlEncoded
    @POST("pembayaran")
    Call<DataPembayaran> pembayaran(
            @Field("id_siswa") String id_siswa
    );
    @FormUrlEncoded
    @POST("notifikasi")
    Call<DataNotifikasi> notifikasi(
            @Field("id_kelas") String id_siswa
    );
    @FormUrlEncoded
    @POST("kantin")
    Call<DataKantin> kantin(
            @Field("id_sekolah") String id_sekolah
    );
    @FormUrlEncoded
    @POST("raport")
    Call<Raport> raport(
            @Field("id_siswa") int id_siswa
    );
    @Multipart
    @POST("uploadgambar")
    Call<ResponseBody> uploadgambar(@Part MultipartBody.Part photo,
                                    @PartMap Map<String,RequestBody> text);
    @FormUrlEncoded
    @POST("detailraport")
    Call<IdRaport> detailraport(
            @Field("id_nialiraport") String id_nialiraport
    );

    @FormUrlEncoded
    @POST("mapel")
    Call<MapelClass> mapel(
            @Field("id_kelas") int id_kelas
    );
    @FormUrlEncoded
    @POST("materi")
    Call<DetialMapel> materi(
            @Field("id_mapel") String id_mapel
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
    @POST("video")
    Call<video> video(
            @Field("id_kelas") int id_kelas
    );

    @FormUrlEncoded
    @POST("login")
    Call<Login> getLogin(@Field("nama_siswa") String nama_siswa, @Field("password") String password);

    @FormUrlEncoded
    @POST("update")
    Call<DefaultResponse> updatesiswa(@Field("email") String email, @Field("nama_siswa") String nama_siswa, @Field("no_telphone") String no_telphone, @Field("nis") String nis, @Field("password") String password);


}
