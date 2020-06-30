package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.DataDetail;
import com.izrael.nakulaedu.model.Status;

import java.util.List;

public class DetialMapel {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private List<DataDetail> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<DataDetail> getData() {
        return data;
    }

    public void setData(List<DataDetail> data) {
        this.data = data;
    }

}
