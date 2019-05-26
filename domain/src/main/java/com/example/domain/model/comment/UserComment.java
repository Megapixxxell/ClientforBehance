package com.example.domain.model.comment;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.domain.model.user.Image;
import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = @ForeignKey(
        entity = Comment.class,
        parentColumns = "id",
        childColumns = "comment_id"
))
public class UserComment {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int mId;

    @ColumnInfo(name = "username")
    @SerializedName("username")
    private String mUsername;

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

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
    }

    public long getCommentId() {
        return mCommentId;
    }

    public void setCommentId(long commentId) {
        mCommentId = commentId;
    }

}
