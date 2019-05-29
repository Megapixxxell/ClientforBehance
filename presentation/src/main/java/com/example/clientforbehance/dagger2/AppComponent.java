package com.example.clientforbehance.dagger2;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {AppModule.class, NetworkModule.class, RepositoryModule.class, ServiceModule.class})
public interface AppComponent {
    ProjectComponent createProjectComponent(ProjectModule module);
    UserComponent createUserComponent(UserModule module);
    CommentComponent createCommentComponent(CommentModule module);
}
