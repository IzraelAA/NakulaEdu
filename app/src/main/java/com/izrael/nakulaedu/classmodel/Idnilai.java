package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.Data;
import com.izrael.nakulaedu.model.Iddatanilai;
import com.izrael.nakulaedu.model.Status;

import java.util.List;

public class Idnilai {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private List<Iddatanilai> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Iddatanilai> getData() {
        return data;
    }

    public void setData(List<Iddatanilai> data) {
        this.data = data;
    }

}