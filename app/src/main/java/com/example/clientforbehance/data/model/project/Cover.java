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
public class Cover implements Serializable {
    /**
     * 115 : http://behance.vo.llnwd.net/profiles3/129052/projects/4138003/115x5fbeddb9956459a3fb7300f78faf0f6e.jpg
     * 202 : http://behance.vo.llnwd.net/profiles3/129052/projects/4138003/5fbeddb9956459a3fb7300f78faf0f6e.jpg
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "photo_url")
    @SerializedName("202")
    private String mPhotoUrl;

    @ColumnInfo(name = "project_id")
    private int mProjectId;

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

    public int getProjectId() {
        return mProjectId;
    }

    public void setProjectId(int projectId) {
        mProjectId = projectId;
    }
}
