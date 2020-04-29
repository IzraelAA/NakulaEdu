package com.izrael.nakulaedu.model;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
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

    @Override
    public String toString() {
        return "DefaultResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}