package com.example.clientforbehance.ui.comments;

import com.example.clientforbehance.data.model.comment.Comment;

public class CommentListItemViewModel {

    private String mImageUrl;
    private String mCommentText;
    private String mUsername;

    public CommentListItemViewModel (Comment comment) {
        mImageUrl = comment.getUser().getImage().getPhotoUrl();
        mCommentText = comment.getComment();
        mUsername = comment.getUser().getUsername();
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCommentText() {
        return mCommentText;
    }

    public String getUsername() {
        return mUsername;
    }
}
