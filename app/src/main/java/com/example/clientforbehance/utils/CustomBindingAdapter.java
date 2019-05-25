package com.example.clientforbehance.utils;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.ui.comments.CommentsAdapter;
import com.example.clientforbehance.ui.projects.ProjectsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import toothpick.Toothpick;

public class CustomBindingAdapter {


    @BindingAdapter("bind:imageUrl")
    public static void loadCoverImage(ImageView imageView, String urlImage) {
        Picasso.with(imageView.getContext()).load(urlImage).fit().into(imageView);
    }

    @BindingAdapter({"bind:data", "bind:clickHandler"})
    public static void configureRecyclerView (RecyclerView recyclerView, List<Project> projects,
                                              ProjectsAdapter.OnItemClickListener listener) {
        ProjectsAdapter adapter = new ProjectsAdapter(projects, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @BindingAdapter({"bind:data"})
    public static void configureCommentsRecyclerView (RecyclerView recyclerView, List<Comment> comments) {
        CommentsAdapter adapter = new CommentsAdapter(comments);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @BindingAdapter({"bind:refreshState", "bind:onRefresh"})
    public static void configureSwipeRefreshLayout (SwipeRefreshLayout layout, boolean isLoading,
                                                    SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
        layout.post(() -> layout.setRefreshing(isLoading));
    }

}
