package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.DataRaport;
import com.izrael.nakulaedu.model.Quiz;
import com.izrael.nakulaedu.model.Status;

import java.util.List;

public class Raport {
    @SerializedName("status")
    @Expose
    private Status           status;
    @SerializedName("data")
    @Expose
    private List<DataRaport> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<DataRaport> getData() {
        return data;
    }

    public void setData(List<DataRaport> data) {
        this.data = data;
    }

}
