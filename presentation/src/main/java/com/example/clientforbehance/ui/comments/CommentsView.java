package com.example.clientforbehance.ui.comments;

import com.example.clientforbehance.common.BaseView;
import com.example.domain.model.comment.Comment;

import java.util.List;

public interface CommentsView extends BaseView {

    void showComments(List<Comment> comments);
}
