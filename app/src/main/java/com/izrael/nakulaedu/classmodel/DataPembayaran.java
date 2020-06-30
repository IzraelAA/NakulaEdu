package com.izrael.nakulaedu.classmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.Nilai;
import com.izrael.nakulaedu.model.Pembayaran;
import com.izrael.nakulaedu.model.Status;

import java.util.List;

public class DataPembayaran {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private List<Pembayaran> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Pembayaran> getData() {
        return data;
    }

    public void setData(List<Pembayaran> data) {
        this.data = data;
    }

}

