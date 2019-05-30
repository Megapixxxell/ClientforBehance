package com.example.domain.repository;

import com.example.domain.model.project.Project;

import java.util.List;

import io.reactivex.Single;

public interface IProjectServerRepository {

    Single<List<Project>> getProjects(String query);
}
