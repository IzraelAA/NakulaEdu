package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class notifikasi {
    @SerializedName("id_notifikasi")
    @Expose
    private Integer idNotifikasi;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("tanggal_mulai")
    @Expose
    private String tanggalMulai;
    @SerializedName("tanggal_selesai")
    @Expose
    private String tanggalSelesai;

    public Integer getIdNotifikasi() {
        return idNotifikasi;
    }

    public void setIdNotifikasi(Integer idNotifikasi) {
        this.idNotifikasi = idNotifikasi;
    }

    public Integer getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(String tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

}