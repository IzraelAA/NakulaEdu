
package com.izrael.nakulaedu.classmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.izrael.nakulaedu.model.MapelResult;
import com.izrael.nakulaedu.model.Status;

public class MapelClass {
    @SerializedName("status")
    @Expose
    private Status             status;
    @SerializedName("data")
    @Expose
    private List<MapelResult> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<MapelResult> getData() {
        return data;
    }

    public void setData(List<MapelResult> data) {
        this.data = data;
    }

}
