package com.izrael.nakulaedu.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetJadwal {
    @SerializedName("status")
    @Expose
    private Status             status;
    @SerializedName("data")
    @Expose
    private List<JadwalResult> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<JadwalResult> getData() {
        return data;
    }

    public void setData(List<JadwalResult> data) {
        this.data = data;
    }

}
