package com.example.clientforbehance.dagger2;

import com.example.data.repository.ProjectServerRepository;
import com.example.domain.repository.ProjectRepository;
import com.example.domain.service.ProjectService;
import com.example.domain.service.ProjectServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    ProjectService provideProjectService(ProjectServiceImpl projectService) {
        return projectService;
    }
}
