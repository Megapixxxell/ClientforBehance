package com.example.clientforbehance.data.model.comment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentResponse {

    @SerializedName("comments")
    private List<Comment> mComments;

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }
}