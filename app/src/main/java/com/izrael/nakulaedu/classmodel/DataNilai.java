package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.DataQuizUjian;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.model.Status;

import java.util.List;

public class DataNilai {
    @SerializedName("status")
    @Expose
    private Status      status;
    @SerializedName("data")
    @Expose
    private List<Nilai> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Nilai> getData() {
        return data;
    }

    public void setData(List<Nilai> data) {
        this.data = data;
    }

}

