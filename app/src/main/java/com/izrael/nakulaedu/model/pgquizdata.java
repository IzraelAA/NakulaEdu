package com.izrael.nakulaedu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.classmodel.pgquiz;

public class pgquizdata {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private pgquiz data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public pgquiz getData() {
        return data;
    }

    public void pgquizdata(pgquiz data) {
        this.data = data;
    }

}