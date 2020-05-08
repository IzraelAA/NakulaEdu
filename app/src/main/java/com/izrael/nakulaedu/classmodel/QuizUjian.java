
package com.izrael.nakulaedu.classmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizUjian {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<UjianResult> result = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UjianResult> getResult() {
        return result;
    }

    public void setResult(List<UjianResult> result) {
        this.result = result;
    }

}
