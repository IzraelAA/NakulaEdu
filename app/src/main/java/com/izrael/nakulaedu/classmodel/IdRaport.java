package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.IdDataRaport;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.model.Status;

import java.util.List;

public class IdRaport {
    @SerializedName("status")
    @Expose
    private Status             status;
    @SerializedName("data")
    @Expose
    private List<IdDataRaport> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<IdDataRaport> getData() {
        return data;
    }

    public void setData(List<IdDataRaport> data) {
        this.data = data;
    }

}