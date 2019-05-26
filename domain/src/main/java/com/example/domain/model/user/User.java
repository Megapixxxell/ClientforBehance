package com.example.domain.model.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int mId;

    @ColumnInfo(name = "username")
    @SerializedName("username")
    private String mUsername;

    @ColumnInfo(name = "country")
    @SerializedName("country")
    private String mCountry;

    @ColumnInfo(name = "location")
    @SerializedName("location")
    private String mLocation;

    @ColumnInfo(name = "created_on")
    @SerializedName("created_on")
    private long mCreatedOn;

    @ColumnInfo(name = "display_name")
    @SerializedName("display_name")
    private String mDisplayName;

    @SerializedName("images")
    @Ignore
    private Image mImage;

    @ColumnInfo(name = "comment_id")
    private long mCommentId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
    }

    public long getCreatedOn() {
        return mCreatedOn;
    }

    public void setCreatedOn(long createdOn) {
        mCreatedOn = createdOn;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public long getCommentId() {
        return mCommentId;
    }

    public void setCommentId(long commentId) {
        mCommentId = commentId;
    }
}