package com.example.domain.service;

import com.example.domain.ApiUtils;
import com.example.domain.model.project.Project;
import com.example.domain.repository.IProjectDBRepository;
import com.example.domain.repository.IProjectServerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProjectServiceImpl implements ProjectService {

    @Inject
    IProjectServerRepository mServerRepository;

    @Inject
    IProjectDBRepository mDBRepository;

    @Inject
    ProjectServiceImpl() {
    }

    @Override
    public Single<List<Project>> getProjects(String query) {
        return mServerRepository.getProjects(query)
                .doOnSuccess(projectResponse -> mDBRepository.insertProjects(projectResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mDBRepository.getProjects() : null);
    }
}
