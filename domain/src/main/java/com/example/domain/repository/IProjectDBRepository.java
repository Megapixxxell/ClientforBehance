package com.example.domain.repository;

import com.example.domain.model.project.Project;

import java.util.List;

public interface IProjectDBRepository {

    List<Project> getProjects();
    void insertProjects(List<Project> projects);
}
