package com.example.clientforbehance.dagger2;

import com.example.data.repository.CommentDBRepository;
import com.example.data.repository.CommentServerRepository;
import com.example.data.repository.ProjectDBRepository;
import com.example.data.repository.ProjectServerRepository;
import com.example.data.repository.UserDBRepository;
import com.example.data.repository.UserServerRepository;
import com.example.domain.repository.ICommentDBRepository;
import com.example.domain.repository.ICommentServerRepository;
import com.example.domain.repository.IProjectDBRepository;
import com.example.domain.repository.IProjectServerRepository;
import com.example.domain.repository.IUserDBRepository;
import com.example.domain.repository.IUserServerRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class RepositoryModule {

    @Provides
    @Singleton
    IProjectServerRepository provideProjectServerRepository(ProjectServerRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    IProjectDBRepository provideProjectDBRepository(ProjectDBRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    IUserServerRepository provideUserServerRepository(UserServerRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    IUserDBRepository provideUserDBRepository(UserDBRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    ICommentServerRepository provideCommentServerRepository(CommentServerRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    ICommentDBRepository provideCommentDBRepository(CommentDBRepository repository) {
        return repository;
    }
}
