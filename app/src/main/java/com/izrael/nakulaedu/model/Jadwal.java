package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jadwal  {

    @SerializedName("kodejdwl")
    @Expose
    private String kodejdwl;
    @SerializedName("id_tahun_akademik")
    @Expose
    private String idTahunAkademik;
    @SerializedName("kode_kelas")
    @Expose
    private String kodeKelas;
    @SerializedName("kode_pelajaran")
    @Expose
    private String kodePelajaran;
    @SerializedName("kode_ruangan")
    @Expose
    private String kodeRuangan;
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("paralel")
    @Expose
    private String paralel;
    @SerializedName("jadwal_serial")
    @Expose
    private String jadwalSerial;
    @SerializedName("jam_mulai")
    @Expose
    private String jamMulai;
    @SerializedName("jam_selesai")
    @Expose
    private String jamSelesai;
    @SerializedName("hari")
    @Expose
    private String hari;
    @SerializedName("aktif")
    @Expose
    private String aktif;
    @SerializedName("nama_kelas")
    @Expose
    private String namaKelas;
    @SerializedName("namamatapelajaran")
    @Expose
    private String namamatapelajaran;
    @SerializedName("nama_guru")
    @Expose
    private String namaGuru;
    @SerializedName("nama_ruangan")
    @Expose
    private String namaRuangan;

    public String getKodejdwl() {
        return kodejdwl;
    }

    public void setKodejdwl(String kodejdwl) {
        this.kodejdwl = kodejdwl;
    }

    public String getIdTahunAkademik() {
        return idTahunAkademik;
    }

    public void setIdTahunAkademik(String idTahunAkademik) {
        this.idTahunAkademik = idTahunAkademik;
    }

    public String getKodeKelas() {
        return kodeKelas;
    }

    public void setKodeKelas(String kodeKelas) {
        this.kodeKelas = kodeKelas;
    }

    public String getKodePelajaran() {
        return kodePelajaran;
    }

    public void setKodePelajaran(String kodePelajaran) {
        this.kodePelajaran = kodePelajaran;
    }

    public String getKodeRuangan() {
        return kodeRuangan;
    }

    public void setKodeRuangan(String kodeRuangan) {
        this.kodeRuangan = kodeRuangan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getParalel() {
        return paralel;
    }

    public void setParalel(String paralel) {
        this.paralel = paralel;
    }

    public String getJadwalSerial() {
        return jadwalSerial;
    }

    public void setJadwalSerial(String jadwalSerial) {
        this.jadwalSerial = jadwalSerial;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getNamamatapelajaran() {
        return namamatapelajaran;
    }

    public void setNamamatapelajaran(String namamatapelajaran) {
        this.namamatapelajaran = namamatapelajaran;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

}
