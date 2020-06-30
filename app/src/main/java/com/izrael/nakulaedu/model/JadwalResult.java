package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadwalResult {
    @SerializedName("id_jadwal")
    @Expose
    private Integer idJadwal;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("id_sekolah")
    @Expose
    private Integer idSekolah;
    @SerializedName("id_mapel")
    @Expose
    private Integer idMapel;
    @SerializedName("id_guru")
    @Expose
    private Integer idGuru;
    @SerializedName("hari")
    @Expose
    private String hari;
    @SerializedName("masuk")
    @Expose
    private String masuk;
    @SerializedName("keluar")
    @Expose
    private String keluar;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("nama_kelas")
    @Expose
    private String namaKelas;
    @SerializedName("nama_sekolah")
    @Expose
    private String namaSekolah;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("nama_pelajaran")
    @Expose
    private String namaPelajaran;
    @SerializedName("deskripsi")
    @Expose
    private Object deskripsi;
    @SerializedName("nama_guru")
    @Expose
    private String namaGuru;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("no_telphone")
    @Expose
    private Object noTelphone;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("alamat")
    @Expose
    private Object alamat;
    @SerializedName("agama")
    @Expose
    private Object agama;
    @SerializedName("jenis_kelamin")
    @Expose
    private Object jenisKelamin;
    @SerializedName("hoby")
    @Expose
    private Object hoby;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("picture")
    @Expose
    private String picture;

    public Integer getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(Integer idJadwal) {
        this.idJadwal = idJadwal;
    }

    public Integer getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
    }

    public Integer getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
    }

    public Integer getIdMapel() {
        return idMapel;
    }

    public void setIdMapel(Integer idMapel) {
        this.idMapel = idMapel;
    }

    public Integer getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(Integer idGuru) {
        this.idGuru = idGuru;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getMasuk() {
        return masuk;
    }

    public void setMasuk(String masuk) {
        this.masuk = masuk;
    }

    public String getKeluar() {
        return keluar;
    }

    public void setKeluar(String keluar) {
        this.keluar = keluar;
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

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNamaPelajaran() {
        return namaPelajaran;
    }

    public void setNamaPelajaran(String namaPelajaran) {
        this.namaPelajaran = namaPelajaran;
    }

    public Object getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(Object deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getNoTelphone() {
        return noTelphone;
    }

    public void setNoTelphone(Object noTelphone) {
        this.noTelphone = noTelphone;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Object getAlamat() {
        return alamat;
    }

    public void setAlamat(Object alamat) {
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

    public Object getHoby() {
        return hoby;
    }

    public void setHoby(Object hoby) {
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