package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdDataRaport {

    @SerializedName("id_nialiraport")
    @Expose
    private Integer idNialiraport;
    @SerializedName("id_dataraport")
    @Expose
    private Integer idDataraport;
    @SerializedName("nilai")
    @Expose
    private String nilai;
    @SerializedName("tipe")
    @Expose
    private String tipe;
    @SerializedName("id_mapel")
    @Expose
    private Integer idMapel;
    @SerializedName("nama_pelajaran")
    @Expose
    private String namaPelajaran;
    @SerializedName("id_guru")
    @Expose
    private Integer idGuru;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("id_kelas")
    @Expose
    private Integer idKelas;

    public Integer getIdNialiraport() {
        return idNialiraport;
    }

    public void setIdNialiraport(Integer idNialiraport) {
        this.idNialiraport = idNialiraport;
    }

    public Integer getIdDataraport() {
        return idDataraport;
    }

    public void setIdDataraport(Integer idDataraport) {
        this.idDataraport = idDataraport;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Integer idKelas) {
        this.idKelas = idKelas;
    }

}