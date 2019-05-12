package com.example.clientforbehance.ui.projects;

import com.example.clientforbehance.data.model.project.RichProject;
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

    ProjectListItemViewModel(RichProject project) {

        if (project.mOwners != null && project.mOwners.size() != 0) {
            mUserImageUrl = project.mOwners.get(0).getImage().getPhotoUrl();
            mUsername = project.mOwners.get(0).getUsername();
        }
        mCoverImageUrl = project.mProject.getCover().getPhotoUrl();
        mName = project.mProject.getName();
        mPublishedOn = DateUtils.format(project.mProject.getPublishedOn());
        mLikes = String.valueOf(project.mProject.getStats().getAppreciations());
        mViews = String.valueOf(project.mProject.getStats().getViews());
        mComments = String.valueOf(project.mProject.getStats().getComments());
        mProjectId = project.mProject.getId();

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
