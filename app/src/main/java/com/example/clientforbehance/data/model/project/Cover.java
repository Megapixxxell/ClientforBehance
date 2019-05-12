package com.example.clientforbehance.data.model.project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Cover implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "cover_id")
    private int mId;

    @ColumnInfo(name = "photo_url")
    @SerializedName("202")
    private String mPhotoUrl;

    @ColumnInfo(name = "mini_photo_url")
    @SerializedName("115")
    private String mMiniPhotoUrl;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getMiniPhotoUrl() {
        return mMiniPhotoUrl;
    }

    public void setMiniPhotoUrl(String miniPhotoUrl) {
        mMiniPhotoUrl = miniPhotoUrl;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        mPhotoUrl = photoUrl;
    }

}
