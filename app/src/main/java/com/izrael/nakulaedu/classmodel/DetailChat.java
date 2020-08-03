package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.DetailChatStatus;
import com.izrael.nakulaedu.model.Status;

import java.util.List;

public class DetailChat {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private List<DetailChatStatus> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<DetailChatStatus> getData() {
        return data;
    }

    public void setData(List<DetailChatStatus> data) {
        this.data = data;
    }

}