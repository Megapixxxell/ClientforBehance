package com.example.data.repository;

import com.example.data.api.BehanceApi;
import com.example.domain.model.project.Project;
import com.example.domain.model.project.ProjectResponse;
import com.example.domain.repository.IProjectServerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class ProjectServerRepository implements IProjectServerRepository {

    @Inject
    BehanceApi mBehanceApi;

    @Inject
    ProjectServerRepository() {
    }

    @Override
    public Single<List<Project>> getProjects(String query) {
        return mBehanceApi.getProjects(query).map(new Function<ProjectResponse, List<Project>>() {
            @Override
            public List<Project> apply(ProjectResponse projectResponse) {
                return projectResponse.getProjects();
            }
        });
    }
}
