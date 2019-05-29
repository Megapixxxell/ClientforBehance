package com.example.domain.service;

import com.example.domain.model.project.Project;

import java.util.List;

import io.reactivex.Single;

public interface ProjectService {

    Single<List<Project>> getProjects();
    void insertProjects(List<Project> projects);
}
