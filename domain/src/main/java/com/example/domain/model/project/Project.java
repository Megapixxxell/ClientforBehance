package com.example.domain.model.project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity
public class Project implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int mId;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String mName;

    @ColumnInfo(name = "published_on")
    @SerializedName("published_on")
    private int mPublishedOn;

    @ColumnInfo(name = "created_on")
    @SerializedName("created_on")
    private int mCreatedOn;

    @SerializedName("covers")
    @Ignore
    private Cover mCover;

    @SerializedName("owners")
    @Ignore
    private List<Owner> mOwners;

    @SerializedName("stats")
    @Ignore
    private Stats mStats;

    @SerializedName("fields")
    @Ignore
    private List<String> mFields;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPublishedOn() {
        return mPublishedOn;
    }

    public void setPublishedOn(int publishedOn) {
        mPublishedOn = publishedOn;
    }

    public int getCreatedOn() {
        return mCreatedOn;
    }

    public void setCreatedOn(int createdOn) {
        mCreatedOn = createdOn;
    }

    public Cover getCover() {
        return mCover;
    }

    public void setCover(Cover cover) {
        mCover = cover;
    }

    public List<Owner> getOwners() {
        return mOwners;
    }

    public void setOwners(List<Owner> owners) {
        mOwners = owners;
    }

    public Stats getStats() {
        return mStats;
    }

    public void setStats(Stats stats) {
        mStats = stats;
    }

    public List<String> getFields() {
        return mFields;
    }

    public void setFields(List<String> fields) {
        mFields = fields;
    }
}