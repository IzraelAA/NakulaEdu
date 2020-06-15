package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Iddatanilai {
    @SerializedName("id_nilai")
    @Expose
    private Integer idNilai;
    @SerializedName("id_datanilai")
    @Expose
    private Integer idDatanilai;
    @SerializedName("id_siswa")
    @Expose
    private Integer idSiswa;
    @SerializedName("nilai")
    @Expose
    private String  nilai;
    @SerializedName("nama_nilai")
    @Expose
    private String  namaNilai;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;
    @SerializedName("id_mapel")
    @Expose
    private Integer idMapel;
    @SerializedName("id_sekolah")
    @Expose
    private Integer idSekolah;
    @SerializedName("id_guru")
    @Expose
    private Integer idGuru;
    @SerializedName("created_at")
    @Expose
    private String  createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object  updatedAt;

    public Integer getIdNilai() {
        return idNilai;
    }

    public void setIdNilai(Integer idNilai) {
        this.idNilai = idNilai;
    }

    public Integer getIdDatanilai() {
        return idDatanilai;
    }

    public void setIdDatanilai(Integer idDatanilai) {
        this.idDatanilai = idDatanilai;
    }

    public Integer getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(Integer idSiswa) {
        this.idSiswa = idSiswa;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getNamaNilai() {
        return namaNilai;
    }

    public void setNamaNilai(String namaNilai) {
        this.namaNilai = namaNilai;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

}