package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailChatStatus {

    @SerializedName("id_chat")
    @Expose
    private Integer idChat;
    @SerializedName("id_statuschat")
    @Expose
    private Integer idStatuschat;
    @SerializedName("id_guru")
    @Expose
    private Integer idGuru;
    @SerializedName("id_siswa")
    @Expose
    private Integer idSiswa;
    @SerializedName("massage")
    @Expose
    private String massage;
    @SerializedName("tipe")
    @Expose
    private String tipe;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("id_sekolah")
    @Expose
    private Integer idSekolah;
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
    private String agama;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("hoby")
    @Expose
    private Object hoby;
    @SerializedName("deskripsi")
    @Expose
    private Object deskripsi;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("created_atchat")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("statussiswa")
    @Expose
    private String statussiswa;
    @SerializedName("nama_siswa")
    @Expose
    private String namaSiswa;
    @SerializedName("nama_panggilan")
    @Expose
    private String namaPanggilan;
    @SerializedName("nis")
    @Expose
    private String nis;
    @SerializedName("foto")
    @Expose
    private String foto;

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public Integer getIdStatuschat() {
        return idStatuschat;
    }

    public void setIdStatuschat(Integer idStatuschat) {
        this.idStatuschat = idStatuschat;
    }

    public Integer getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(Integer idGuru) {
        this.idGuru = idGuru;
    }

    public Integer getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(Integer idSiswa) {
        this.idSiswa = idSiswa;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
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

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Object getHoby() {
        return hoby;
    }

    public void setHoby(Object hoby) {
        this.hoby = hoby;
    }

    public Object getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(Object deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
    }

    public String getStatussiswa() {
        return statussiswa;
    }

    public void setStatussiswa(String statussiswa) {
        this.statussiswa = statussiswa;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getNamaPanggilan() {
        return namaPanggilan;
    }

    public void setNamaPanggilan(String namaPanggilan) {
        this.namaPanggilan = namaPanggilan;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}