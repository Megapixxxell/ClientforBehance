package com.example.clientforbehance.ui.comments;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.databinding.CommentBinding;

public class CommentsAdapter extends PagedListAdapter<Comment, CommentsHolder> {

    public CommentsAdapter() {
        super(CALLBACK);
    }

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CommentBinding binding = CommentBinding.inflate(inflater, viewGroup, false);
        return new CommentsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder commentsHolder, int i) {
        Comment comment = getItem(i);
        if (comment != null) commentsHolder.bind(comment);
    }

    private static final DiffUtil.ItemCallback<Comment> CALLBACK = new DiffUtil.ItemCallback<Comment>() {
        @Override
        public boolean areItemsTheSame(@NonNull Comment comment, @NonNull Comment t1) {
            return comment.getId() == t1.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Comment comment, @NonNull Comment t1) {
            return comment.equals(t1);
        }
    };
}
