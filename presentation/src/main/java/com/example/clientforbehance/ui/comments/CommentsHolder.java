package com.example.clientforbehance.ui.comments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clientforbehance.R;
import com.example.domain.model.comment.Comment;
import com.squareup.picasso.Picasso;

class CommentsHolder extends RecyclerView.ViewHolder {

    private ImageView mImage;
    private TextView mCommentText, mUsername;

    CommentsHolder(@NonNull View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.iv_comment);
        mCommentText = itemView.findViewById(R.id.text_comment);
        mUsername = itemView.findViewById(R.id.tv_comment_username);
    }

    void bind(Comment comment) {
        if (comment.getUser().getImage() != null && comment.getUser() != null) {
            Picasso.with(itemView.getContext())
                    .load(comment.getUser().getImage().getPhotoUrl())
                    .fit()
                    .into(mImage);
        }


        if (comment.getUser() != null) {
            mUsername.setText(comment.getUser().getUsername());
        }
        mCommentText.setText(comment.getComment());
    }


}
