package com.example.clientforbehance.dagger2;

import com.example.data.repository.CommentDBRepository;
import com.example.data.repository.CommentServerRepository;
import com.example.data.repository.ProjectDBRepository;
import com.example.data.repository.ProjectServerRepository;
import com.example.data.repository.UserDBRepository;
import com.example.data.repository.UserServerRepository;
import com.example.domain.repository.CommentRepository;
import com.example.domain.repository.ProjectRepository;
import com.example.domain.repository.UserRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    @Named(ProjectRepository.SERVER)
    ProjectRepository provideProjectServerRepository(ProjectServerRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    @Named(ProjectRepository.DB)
    ProjectRepository provideProjectDBRepository(ProjectDBRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    @Named(UserRepository.SERVER)
    UserRepository provideUserServerRepository() {
        return new UserServerRepository();
    }

    @Provides
    @Singleton
    @Named(UserRepository.DB)
    UserRepository provideUserDBRepository() {
        return new UserDBRepository();
    }

    @Provides
    @Singleton
    @Named(CommentRepository.SERVER)
    CommentRepository provideCommentServerRepository() {
        return new CommentServerRepository();
    }

    @Provides
    @Singleton
    @Named(CommentRepository.DB)
    CommentRepository provideCommentDBRepository() {
        return new CommentDBRepository();
    }

}
