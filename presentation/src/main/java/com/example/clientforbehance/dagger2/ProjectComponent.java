package com.example.clientforbehance.dagger2;

import com.example.clientforbehance.ui.projects.ProjectFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {ProjectModule.class})
public interface ProjectComponent {
    void inject (ProjectFragment fragment);
}
