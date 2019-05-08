package com.example.clientforbehance.ui.projects;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.databinding.ProjectBinding;

import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsHolder> {

    private final List<Project> mProjects;
    private final OnItemClickListener mOnItemClickListener;

    public ProjectsAdapter(List<Project> projects, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        mProjects = projects;
    }

    @NonNull
    @Override
    public ProjectsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ProjectBinding binding = ProjectBinding.inflate(inflater, viewGroup, false);

        return new ProjectsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsHolder viewHolder, int i) {
        Project project = mProjects.get(i);
        viewHolder.bind(project, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    public interface OnItemClickListener {
        void onAuthorClick(String username);
        void onCommentsClick(int projectId);
    }
}
