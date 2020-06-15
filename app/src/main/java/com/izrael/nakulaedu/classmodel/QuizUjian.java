
package com.izrael.nakulaedu.classmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.Quiz;
import com.izrael.nakulaedu.model.Status;

public class QuizUjian {

    @SerializedName("status")
    @Expose
    private Status     status;
    @SerializedName("data")
    @Expose
    private List<Quiz> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Quiz> getData() {
        return data;
    }

    public void setData(List<Quiz> data) {
        this.data = data;
    }

}
