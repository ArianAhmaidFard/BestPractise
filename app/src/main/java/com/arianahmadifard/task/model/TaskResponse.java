package com.arianahmadifard.task.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskResponse {
    @SerializedName("data")
    @Expose
    private List<TaskResponseItem> data = null;

    public List<TaskResponseItem> getData() {
        return data;
    }

    public void setData(List<TaskResponseItem> data) {
        this.data = data;
    }
}
