package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quiz {

    @SerializedName("id_quiz")
    @Expose
    private Integer idQuiz;
    @SerializedName("id_dataquiz")
    @Expose
    private Integer idDataquiz;
    @SerializedName("quiz")
    @Expose
    private String quiz;
    @SerializedName("file")
    @Expose
    private Object file;
    @SerializedName("tipe")
    @Expose
    private Object tipe;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("nomer")
    @Expose
    private Integer nomer;

    public Integer getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Integer idQuiz) {
        this.idQuiz = idQuiz;
    }

    public Integer getIdDataquiz() {
        return idDataquiz;
    }

    public void setIdDataquiz(Integer idDataquiz) {
        this.idDataquiz = idDataquiz;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getNomer() {
        return nomer;
    }

    public void setNomer(Integer nomer) {
        this.nomer = nomer;
    }

}