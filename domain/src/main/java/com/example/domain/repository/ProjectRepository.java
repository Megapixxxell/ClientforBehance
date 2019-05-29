package com.example.domain.repository;

import com.example.domain.model.project.Project;

import java.util.List;

import io.reactivex.Single;

public interface ProjectRepository {

    String SERVER = "SERVER";
    String DB = "DB";

    Single<List<Project>> getProjects();
    void insertProjects(List<Project> projects);
}
