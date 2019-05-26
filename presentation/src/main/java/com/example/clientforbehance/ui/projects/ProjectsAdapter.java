package com.example.clientforbehance.ui.projects;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.R;
import com.example.domain.model.project.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsHolder> {

    private final List<Project> mProjects = new ArrayList<>();
    private final OnItemClickListener mOnItemClickListener;

    ProjectsAdapter(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ProjectsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.li_projects, viewGroup, false);
        return new ProjectsHolder(view);
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

    void addData(List<Project> projects, boolean isRefreshed) {
        if (isRefreshed) {
            mProjects.clear();
        }
        mProjects.addAll(projects);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onAuthorClick(String username);
        void onCommentsClick(int projectId);
    }
}
