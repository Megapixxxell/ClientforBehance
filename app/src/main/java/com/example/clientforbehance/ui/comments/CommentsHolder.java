package com.example.clientforbehance.ui.comments;

import android.support.v7.widget.RecyclerView;

import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.databinding.CommentBinding;

public class CommentsHolder extends RecyclerView.ViewHolder {

    CommentBinding mCommentBinding;

    public CommentsHolder(CommentBinding binding) {
        super(binding.getRoot());
        mCommentBinding = binding;
    }

    void bind(Comment comment) {
        mCommentBinding.setComment(new CommentListItemViewModel(comment));
        mCommentBinding.executePendingBindings();
    }
}
