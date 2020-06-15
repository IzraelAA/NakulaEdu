package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataQuizUjian {
    @SerializedName("id_dataquiz")
    @Expose
    private Integer idDataquiz;
    @SerializedName("id_mapel")
    @Expose
    private Integer idMapel;
    @SerializedName("id_sekolah")
    @Expose
    private Integer idSekolah;
    @SerializedName("id_guru")
    @Expose
    private Integer idGuru;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("nama_quiz")
    @Expose
    private String namaQuiz;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("nama_pelajaran")
    @Expose
    private String namaPelajaran;
    @SerializedName("nama_guru")
    @Expose
    private String namaGuru;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("picture")
    @Expose
    private String picture;

    public Integer getIdDataquiz() {
        return idDataquiz;
    }

    public void setIdDataquiz(Integer idDataquiz) {
        this.idDataquiz = idDataquiz;
    }

    public Integer getIdMapel() {
        return idMapel;
    }

    public void setIdMapel(Integer idMapel) {
        this.idMapel = idMapel;
    }

    public Integer getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
    }

    public Integer getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(Integer idGuru) {
        this.idGuru = idGuru;
    }

    public Integer getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaQuiz() {
        return namaQuiz;
    }

    public void setNamaQuiz(String namaQuiz) {
        this.namaQuiz = namaQuiz;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNamaPelajaran() {
        return namaPelajaran;
    }

    public void setNamaPelajaran(String namaPelajaran) {
        this.namaPelajaran = namaPelajaran;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}