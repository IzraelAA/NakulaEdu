package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pembayaran {
    @SerializedName("id_pembayaran")
    @Expose
    private Integer idPembayaran;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("id_siswa")
    @Expose
    private Integer idSiswa;
    @SerializedName("tanggal_pembayaran")
    @Expose
    private String tanggalPembayaran;
    @SerializedName("bulan_pembayaran")
    @Expose
    private String bulanPembayaran;
    @SerializedName("jumlah_pembayaran")
    @Expose
    private String jumlahPembayaran;

    public Integer getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(Integer idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public Integer getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
    }

    public Integer getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(Integer idSiswa) {
        this.idSiswa = idSiswa;
    }

    public String getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(String tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public String getBulanPembayaran() {
        return bulanPembayaran;
    }

    public void setBulanPembayaran(String bulanPembayaran) {
        this.bulanPembayaran = bulanPembayaran;
    }

    public String getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(String jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
    }

}