package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataRaport {
    @SerializedName("id_datarapot")
    @Expose
    private Integer idDatarapot;
    @SerializedName("id_siswa")
    @Expose
    private Integer idSiswa;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("id_sekolah")
    @Expose
    private Integer idSekolah;
    @SerializedName("nama_kelas")
    @Expose
    private String namaKelas;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("nama_siswa")
    @Expose
    private String namaSiswa;
    @SerializedName("nis")
    @Expose
    private String nis;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("nama_sekolah")
    @Expose
    private String namaSekolah;
    @SerializedName("logo")
    @Expose
    private String logo;

    public Integer getIdDatarapot() {
        return idDatarapot;
    }

    public void setIdDatarapot(Integer idDatarapot) {
        this.idDatarapot = idDatarapot;
    }

    public Integer getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(Integer idSiswa) {
        this.idSiswa = idSiswa;
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

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
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

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
}
