package com.example.domain.model.comment;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.domain.model.project.Project;
import com.example.domain.model.user.User;
import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = @ForeignKey(
        entity = Project.class,
        parentColumns = "id",
        childColumns = "project_id"
))
public class Comment {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int mId;

    @SerializedName("user")
    @Ignore
    private UserComment mUser;

    @SerializedName("comment")
    @ColumnInfo(name = "comment")
    private String mComment;

    @SerializedName("created_on")
    @ColumnInfo(name = "created_on")
    private int mCreatedOn;

    @ColumnInfo(name = "project_id")
    private int projectId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setUser(UserComment user) {
        mUser = user;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public int getCreatedOn() {
        return mCreatedOn;
    }

    public void setCreatedOn(int createdOn) {
        mCreatedOn = createdOn;
    }

    public UserComment getUser() {
        return mUser;
    }
}
