package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDetail {
    @SerializedName("id_materi")
    @Expose
    private Integer idMateri;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("id_guru")
    @Expose
    private Integer idGuru;
    @SerializedName("id_sekolah")
    @Expose
    private Integer idSekolah;
    @SerializedName("nama_materi")
    @Expose
    private String namaMateri;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("jam_mulai")
    @Expose
    private String jamMulai;
    @SerializedName("jam_selesai")
    @Expose
    private String jamSelesai;
    @SerializedName("hari")
    @Expose
    private String hari;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("id_mapel")
    @Expose
    private Integer idMapel;
    @SerializedName("nama_pelajaran")
    @Expose
    private String namaPelajaran;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("nama_guru")
    @Expose
    private String namaGuru;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("no_telphone")
    @Expose
    private String noTelphone;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("agama")
    @Expose
    private Object agama;
    @SerializedName("jenis_kelamin")
    @Expose
    private Object jenisKelamin;
    @SerializedName("hoby")
    @Expose
    private String hoby;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("picture")
    @Expose
    private String picture;

    public Integer getIdMateri() {
        return idMateri;
    }

    public void setIdMateri(Integer idMateri) {
        this.idMateri = idMateri;
    }

    public Integer getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
    }

    public Integer getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(Integer idGuru) {
        this.idGuru = idGuru;
    }

    public Integer getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
    }

    public String getNamaMateri() {
        return namaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        this.namaMateri = namaMateri;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public Integer getIdMapel() {
        return idMapel;
    }

    public void setIdMapel(Integer idMapel) {
        this.idMapel = idMapel;
    }

    public String getNamaPelajaran() {
        return namaPelajaran;
    }

    public void setNamaPelajaran(String namaPelajaran) {
        this.namaPelajaran = namaPelajaran;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelphone() {
        return noTelphone;
    }

    public void setNoTelphone(String noTelphone) {
        this.noTelphone = noTelphone;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Object getAgama() {
        return agama;
    }

    public void setAgama(Object agama) {
        this.agama = agama;
    }

    public Object getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Object jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getHoby() {
        return hoby;
    }

    public void setHoby(String hoby) {
        this.hoby = hoby;
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
