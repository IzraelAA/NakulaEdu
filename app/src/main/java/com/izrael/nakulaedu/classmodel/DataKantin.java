package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.Status;
import com.izrael.nakulaedu.model.kantin;

import java.util.List;

public class DataKantin {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private List<kantin> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<kantin> getData() {
        return data;
    }

    public void setData(List<kantin> data) {
        this.data = data;
    }

}