package com.example.clientforbehance.data.model.project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(
        entity = Project.class,
        parentColumns = "id",
        childColumns = "project_id"
))
public class Stats implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "views")
    @SerializedName("views")
    private int mViews;

    @ColumnInfo(name = "appreciations")
    @SerializedName("appreciations")
    private int mAppreciations;

    @ColumnInfo(name = "comments")
    @SerializedName("comments")
    private int mComments;

    @ColumnInfo(name = "project_id")
    private int mProjectId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }


    public int getViews() {
        return mViews;
    }

    public void setViews(int views) {
        mViews = views;
    }

    public int getAppreciations() {
        return mAppreciations;
    }

    public void setAppreciations(int appreciations) {
        mAppreciations = appreciations;
    }

    public int getComments() {
        return mComments;
    }

    public void setComments(int comments) {
        mComments = comments;
    }

    public int getProjectId() {
        return mProjectId;
    }

    public void setProjectId(int projectId) {
        mProjectId = projectId;
    }
}
