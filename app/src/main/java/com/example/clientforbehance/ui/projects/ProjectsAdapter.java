package com.example.clientforbehance.ui.projects;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clientforbehance.data.model.project.RichProject;
import com.example.clientforbehance.databinding.ProjectBinding;

public class ProjectsAdapter extends PagedListAdapter<RichProject, ProjectsHolder> {

    private final OnItemClickListener mOnItemClickListener;

    public ProjectsAdapter(OnItemClickListener onItemClickListener) {
        super(CALLBACK);
        mOnItemClickListener = onItemClickListener;
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
        RichProject project = getItem(i);
        if (project != null)
            viewHolder.bind(project, mOnItemClickListener);
    }

    public interface OnItemClickListener {
        void onAuthorClick(Context context, String username);

        void onCommentsClick(int projectId);
    }

    private static final DiffUtil.ItemCallback<RichProject> CALLBACK = new DiffUtil.ItemCallback<RichProject>() {
        @Override
        public boolean areItemsTheSame(RichProject oldItem, RichProject newItem) {
            return oldItem.mProject.getId() == newItem.mProject.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(RichProject oldItem, @NonNull RichProject newItem) {
            return oldItem.equals(newItem);
        }
    };
}
