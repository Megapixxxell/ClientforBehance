package com.example.clientforbehance.ui.comments;

import com.example.clientforbehance.data.model.comment.Comment;

public class CommentListItemViewModel {

    private String mImageUrl;
    private String mCommentText;
    private String mUsername;

    CommentListItemViewModel(Comment comment) {
        if (comment.getUser() != null || comment.getUser().getImage() != null) {
            mUsername = comment.getUser().getUsername();
            mImageUrl = comment.getUser().getImage().getPhotoUrl();
        }
        mCommentText = comment.getComment();
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
