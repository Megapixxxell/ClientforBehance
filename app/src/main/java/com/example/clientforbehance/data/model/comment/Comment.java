package com.example.clientforbehance.data.model.comment;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.clientforbehance.data.model.user.User;
import com.google.gson.annotations.SerializedName;

@Entity
public class Comment {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int mId;

    @SerializedName("user")
    @Embedded
    private User mUser;

    @SerializedName("comment")
    @ColumnInfo(name = "comment")
    private String mComment;

    @SerializedName("comment_created_on")
    @ColumnInfo(name = "created_on")
    private int mCreatedOn;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
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
}
