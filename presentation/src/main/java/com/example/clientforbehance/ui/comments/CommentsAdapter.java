package com.example.clientforbehance.ui.comments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.R;
import com.example.domain.model.comment.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsHolder> {

    private List<Comment> mComments = new ArrayList<>();

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.li_comment, viewGroup, false);
        return new CommentsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder commentsHolder, int i) {
        Comment comment = mComments.get(i);
        commentsHolder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    void addData (List<Comment> comments, boolean isRefreshed) {
        if (isRefreshed) {
            mComments.clear();
        }

        mComments.addAll(comments);
        notifyDataSetChanged();
    }
}
