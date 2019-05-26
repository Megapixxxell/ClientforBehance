package com.example.clientforbehance.dagger2;

import com.example.clientforbehance.ui.projects.ProjectsView;

import dagger.Module;
import dagger.Provides;

@Module
public class ProjectModule {

    private ProjectsView mView;

    public ProjectModule(ProjectsView view) {
        mView = view;
    }

    @Provides
    @PerFragment
    ProjectsView provideProjectView () {
        return mView;
    }

}
