package com.example.clientforbehance.ui.projects;

import android.support.v7.widget.RecyclerView;

import com.example.clientforbehance.data.model.project.RichProject;
import com.example.clientforbehance.databinding.ProjectBinding;

class ProjectsHolder extends RecyclerView.ViewHolder {

    private ProjectBinding mBinding;

    ProjectsHolder(ProjectBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    void bind(RichProject project, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mBinding.setProject(new ProjectListItemViewModel(project));
        mBinding.setOnItemClickListener(onItemClickListener);
        mBinding.executePendingBindings();
    }
}
