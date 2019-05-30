package com.example.clientforbehance.ui.projects;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.clientforbehance.common.BaseView;
import com.example.clientforbehance.data.model.project.Project;

import java.util.List;

public interface ProjectsView extends BaseView {

    void showProjects(List<Project> projects);

    @StateStrategyType(SkipStrategy.class)
    void openUserFragment(String username);
    @StateStrategyType(SkipStrategy.class)
    void openCommentsFragment(int projectId);
}
