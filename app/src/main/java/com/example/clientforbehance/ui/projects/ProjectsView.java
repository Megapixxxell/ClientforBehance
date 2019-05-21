package com.example.clientforbehance.ui.projects;

import com.example.clientforbehance.common.BaseView;
import com.example.clientforbehance.data.model.project.Project;

import java.util.List;


public interface ProjectsView extends BaseView {

    void showProjects(List<Project> projects);

    void openUserFragment(String username);
    void openCommentsFragment(int projectId);


}
