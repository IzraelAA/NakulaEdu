package com.izrael.nakulaedu.model;

import com.google.gson.annotations.SerializedName;

public class GetAuth {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private Pelanggan pelanggan;
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Pelanggan getPelanggan() {
        return pelanggan;
    }
    public void set_pelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    @Override
    public String toString() {
        return "GetAuth{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", pelanggan=" + pelanggan +
                '}';
    }
}
