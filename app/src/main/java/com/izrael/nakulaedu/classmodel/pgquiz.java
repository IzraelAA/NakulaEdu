package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class pgquiz {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_dataquiz")
    @Expose
    private Integer idDataquiz;
    @SerializedName("bobot")
    @Expose
    private String bobot;
    @SerializedName("nomer")
    @Expose
    private Integer nomer;
    @SerializedName("soal")
    @Expose
    private String soal;
    @SerializedName("opsi_a")
    @Expose
    private String opsiA;
    @SerializedName("opsi_b")
    @Expose
    private String opsiB;
    @SerializedName("opsi_c")
    @Expose
    private String opsiC;
    @SerializedName("opsi_d")
    @Expose
    private String opsiD;
    @SerializedName("opsi_e")
    @Expose
    private String opsiE;
    @SerializedName("jawaban")
    @Expose
    private String jawaban;
    @SerializedName("file")
    @Expose
    private Object file;
    @SerializedName("tipe")
    @Expose
    private Object tipe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDataquiz() {
        return idDataquiz;
    }

    public void setIdDataquiz(Integer idDataquiz) {
        this.idDataquiz = idDataquiz;
    }

    public String getBobot() {
        return bobot;
    }

    public void setBobot(String bobot) {
        this.bobot = bobot;
    }

    public Integer getNomer() {
        return nomer;
    }

    public void setNomer(Integer nomer) {
        this.nomer = nomer;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getOpsiA() {
        return opsiA;
    }

    public void setOpsiA(String opsiA) {
        this.opsiA = opsiA;
    }

    public String getOpsiB() {
        return opsiB;
    }

    public void setOpsiB(String opsiB) {
        this.opsiB = opsiB;
    }

    public String getOpsiC() {
        return opsiC;
    }

    public void setOpsiC(String opsiC) {
        this.opsiC = opsiC;
    }

    public String getOpsiD() {
        return opsiD;
    }

    public void setOpsiD(String opsiD) {
        this.opsiD = opsiD;
    }

    public String getOpsiE() {
        return opsiE;
    }

    public void setOpsiE(String opsiE) {
        this.opsiE = opsiE;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

    public Object getTipe() {
        return tipe;
    }

    public void setTipe(Object tipe) {
        this.tipe = tipe;
    }

}