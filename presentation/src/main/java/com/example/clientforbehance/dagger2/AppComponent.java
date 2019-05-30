package com.example.clientforbehance.dagger2;

import com.example.clientforbehance.ui.comments.CommentsFragment;
import com.example.clientforbehance.ui.projects.ProjectFragment;
import com.example.clientforbehance.ui.user.UserFragment;
import com.example.domain.service.CommentService;
import com.example.domain.service.ProjectService;
import com.example.domain.service.UserService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {AppModule.class, NetworkModule.class, RepositoryModule.class, ServiceModule.class})
public interface AppComponent {
    void injectProjectFragment(ProjectFragment fragment);
    ProjectService getProjectService();
    void injectCommentFragment(CommentsFragment fragment);
    CommentService getCommentService();
    void injectUserFragment(UserFragment fragment);
    UserService getUserService();
}
