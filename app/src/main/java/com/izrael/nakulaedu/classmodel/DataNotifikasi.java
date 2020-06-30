package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.model.Status;
import com.izrael.nakulaedu.model.notifikasi;

import java.util.List;

public class DataNotifikasi {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private List<notifikasi> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<notifikasi> getData() {
        return data;
    }

    public void setData(List<notifikasi> data) {
        this.data = data;
    }

}

