package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewChatStatus {
    @SerializedName("id_statuschat")
    @Expose
    private Integer idStatuschat;
    @SerializedName("chatdeskripsi")
    @Expose
    private String chatdeskripsi;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("id_mapel")
    @Expose
    private Integer idMapel;
    @SerializedName("id_guru")
    @Expose
    private Integer idGuru;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("nama_kelas")
    @Expose
    private String namaKelas;
    @SerializedName("id_sekolah")
    @Expose
    private Integer idSekolah;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_atstatus")
    @Expose
    private String createdAt;
    @SerializedName("nama_pelajaran")
    @Expose
    private String namaPelajaran;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    public Integer getIdStatuschat() {
        return idStatuschat;
    }

    public void setIdStatuschat(Integer idStatuschat) {
        this.idStatuschat = idStatuschat;
    }

    public String getChatdeskripsi() {
        return chatdeskripsi;
    }

    public void setChatdeskripsi(String chatdeskripsi) {
        this.chatdeskripsi = chatdeskripsi;
    }

    public Integer getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
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

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public Integer getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

}