package com.example.clientforbehance.data.model.project;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectResponse {

    @SerializedName("projects")
    private List<Project> mProjects;

    public List<Project> getProjects() {
        return mProjects;
    }

    public void setProjects (List<Project> projects) {
        mProjects = projects;
    }
}
