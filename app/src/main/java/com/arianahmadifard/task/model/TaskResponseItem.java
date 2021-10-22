package com.arianahmadifard.task.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Task")
public class TaskResponseItem {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("guid")
    @Expose
    @ColumnInfo(name = "guid")
    private String guid;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "country")
    @SerializedName("country")
    @Expose
    private String country;

    @ColumnInfo(name = "portrait")
    @SerializedName("portrait")
    @Expose
    private String portrait;

    @ColumnInfo(name = "landscape")
    @SerializedName("landscape")
    @Expose
    private String landscape;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    private String description;

    @ColumnInfo(name = "likeStatus")
    @SerializedName("likeStatus")
    @Expose
    private Boolean likeStatus;

    @ColumnInfo(name = "updatedDate")
    @SerializedName("updatedDate")
    @Expose
    private Long updatedDate;


    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

}
