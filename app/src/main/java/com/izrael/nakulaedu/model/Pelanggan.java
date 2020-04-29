package com.izrael.nakulaedu.model;

import com.google.gson.annotations.SerializedName;

public class Pelanggan {
    @SerializedName("id_siswa")
    private String id_siswa;
    @SerializedName("nik")
    private String nik;
    @SerializedName("nisn")
    private String NISN;
    @SerializedName("nama")
    private String nama;
    @SerializedName("telepon")
    private String telepon;
    @SerializedName("id_jenis_kelamin")
    private String id_jenis_kelamin;

    public Pelanggan(){}

    public Pelanggan(String id_siswa, String nik, String nama,String NISN, String telepon, String id_jenis_kelamin) {
        this.id_siswa = id_siswa;
        this.nik = nik;
        this.nama = nama;
        this.NISN = NISN;
        this.telepon = telepon;
        this.id_jenis_kelamin = id_jenis_kelamin;
    }

    public String getid_siswa() {
        return id_siswa;
    }

    public void setid_siswa(String id_siswa) {
        this.id_siswa = id_siswa;
    }
    public String getNISN() {
        return NISN;
    }

    public void setNISN(String NISN) {
        this.NISN = NISN;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String gettelepon() {
        return telepon;
    }

    public void settelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getid_jenis_kelamin() {
        return id_jenis_kelamin;
    }

    public void setid_jenis_kelamin(String id_jenis_kelamin) {
        this.id_jenis_kelamin = id_jenis_kelamin;
    }
}
