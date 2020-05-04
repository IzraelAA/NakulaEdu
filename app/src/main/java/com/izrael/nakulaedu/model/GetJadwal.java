package com.izrael.nakulaedu.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetJadwal {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<JadwalResult> result ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<JadwalResult> getResult() {
        return result;
    }

    public void setResult(List<JadwalResult> result) {
        this.result = result;
    }

}
