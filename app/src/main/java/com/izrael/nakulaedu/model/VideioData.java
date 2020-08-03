package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideioData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("deskripsivid")
    @Expose
    private String deskripsivid;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("hari")
    @Expose
    private String hari;
    @SerializedName("id_mapel")
    @Expose
    private Integer idMapel;
    @SerializedName("nama_kelas")
    @Expose
    private String namaKelas;
    @SerializedName("id_sekolah")
    @Expose
    private Integer idSekolah;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("nama_pelajaran")
    @Expose
    private String namaPelajaran;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("id_guru")
    @Expose
    private Integer idGuru;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeskripsivid() {
        return deskripsivid;
    }

    public void setDeskripsivid(String deskripsivid) {
        this.deskripsivid = deskripsivid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Integer getIdMapel() {
        return idMapel;
    }

    public void setIdMapel(Integer idMapel) {
        this.idMapel = idMapel;
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

    public Integer getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(Integer idGuru) {
        this.idGuru = idGuru;
    }

}