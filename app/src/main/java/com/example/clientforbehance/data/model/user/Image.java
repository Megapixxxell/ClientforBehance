package com.example.clientforbehance.data.model.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Image implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "image_id")
    private int mId;

    @SerializedName("138")
    @ColumnInfo(name = "photo_url")
    private String mPhotoUrl;

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

}