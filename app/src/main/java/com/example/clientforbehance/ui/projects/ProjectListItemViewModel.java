package com.example.clientforbehance.ui.projects;

import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.utils.DateUtils;

public class ProjectListItemViewModel {

    private String mCoverImageUrl;
    private String mUserImageUrl;
    private String mName;
    private String mUsername;
    private String mPublishedOn;
    private String mLikes;
    private String mViews;
    private String mComments;
    private int mProjectId;

    public ProjectListItemViewModel (Project project) {

        mCoverImageUrl = project.getCover().getPhotoUrl();
        mUserImageUrl = project.getOwners().get(0).getImage().getPhotoUrl();
        mName = project.getName();
        mUsername = project.getOwners().get(0).getUsername();
        mPublishedOn = DateUtils.format(project.getPublishedOn());
        mLikes = String.valueOf(project.getStats().getAppreciations());
        mViews = String.valueOf(project.getStats().getViews());
        mComments = String.valueOf(project.getStats().getComments());
        mProjectId = project.getId();

    }

    public int getProjectId() {
        return mProjectId;
    }

    public String getCoverImageUrl() {
        return mCoverImageUrl;
    }

    public String getUserImageUrl() {
        return mUserImageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPublishedOn() {
        return mPublishedOn;
    }

    public String getLikes() {
        return mLikes;
    }

    public String getViews() {
        return mViews;
    }

    public String getComments() {
        return mComments;
    }
}
