package com.example.clientforbehance.ui.projects;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clientforbehance.R;
import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.utils.DateUtils;
import com.squareup.picasso.Picasso;

class ProjectsHolder extends RecyclerView.ViewHolder {

    private ImageView mCoverImage, mCommentsImage, mUserImage;
    private TextView mName, mUsername, mPublishedOn, mLikes, mViews, mComments;

    ProjectsHolder(@NonNull View itemView) {
        super(itemView);
        mCoverImage = itemView.findViewById(R.id.cover_image);
        mCommentsImage = itemView.findViewById(R.id.comments_image);
        mUserImage = itemView.findViewById(R.id.user_image);
        mName = itemView.findViewById(R.id.tv_name);
        mUsername = itemView.findViewById(R.id.tv_username);
        mPublishedOn = itemView.findViewById(R.id.tv_published);
        mLikes = itemView.findViewById(R.id.tv_like);
        mViews = itemView.findViewById(R.id.tv_views);
        mComments = itemView.findViewById(R.id.tv_comments);
    }

    void bind(Project project, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        Picasso.with(itemView.getContext())
                .load(project.getCover().getPhotoUrl())
                .fit()
                .into(mCoverImage);

        Picasso.with(itemView.getContext())
                .load(project.getOwners().get(0).getImage().getPhotoUrl())
                .fit()
                .into(mUserImage);

        String username = project.getOwners().get(0).getUsername();
        int projectId = project.getId();

        mUsername.setText(username);
        mName.setText(project.getName());
        mPublishedOn.setText(DateUtils.format(project.getPublishedOn()));
        mLikes.setText(String.valueOf(project.getStats().getAppreciations()));
        mViews.setText(String.valueOf(project.getStats().getViews()));
        mComments.setText(String.valueOf(project.getStats().getComments()));

        mUserImage.setOnClickListener((v -> {onItemClickListener.onAuthorClick(username);}));
        mCommentsImage.setOnClickListener((v -> onItemClickListener.onCommentsClick(projectId)));

    }
}
