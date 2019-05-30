package com.example.clientforbehance.dagger2;

import com.example.domain.service.CommentService;
import com.example.domain.service.CommentServiceImpl;
import com.example.domain.service.ProjectService;
import com.example.domain.service.ProjectServiceImpl;
import com.example.domain.service.UserService;
import com.example.domain.service.UserServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ServiceModule {

    @Provides
    @Singleton
    ProjectService provideProjectService(ProjectServiceImpl projectService) {
        return projectService;
    }

    @Provides
    @Singleton
    UserService provideUserService(UserServiceImpl userService) {
        return userService;
    }

    @Provides
    @Singleton
    CommentService provideCommentService(CommentServiceImpl commentService) {
        return commentService;
    }
}
