package com.example.clientforbehance.utils;

import android.arch.paging.PagedList;
import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.data.model.project.RichProject;
import com.example.clientforbehance.ui.comments.CommentsAdapter;
import com.example.clientforbehance.ui.projects.ProjectListItemViewModel;
import com.example.clientforbehance.ui.projects.ProjectsAdapter;
import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void loadCoverImage(ImageView imageView, String urlImage) {
        Picasso.with(imageView.getContext()).load(urlImage).fit().into(imageView);
    }

    @BindingAdapter({"bind:data", "bind:clickHandler"})
    public static void configureRecyclerView (RecyclerView recyclerView, PagedList<RichProject> projects,
                                              ProjectsAdapter.OnItemClickListener listener) {
        ProjectsAdapter adapter = new ProjectsAdapter(listener);
        adapter.submitList(projects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @BindingAdapter({"bind:data"})
    public static void configureCommentsRecyclerView (RecyclerView recyclerView, PagedList<Comment> comments) {
        CommentsAdapter adapter = new CommentsAdapter();
        adapter.submitList(comments);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @BindingAdapter({"bind:refreshState", "bind:onRefresh"})
    public static void configureSwipeRefreshLayout (SwipeRefreshLayout layout, boolean isLoading,
                                                    SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
        layout.post(() -> layout.setRefreshing(isLoading));
    }

    @BindingAdapter({"bind:username", "bind:clickListener"})
    public static void configureOnClick(ImageView imageView, String username, ProjectsAdapter.OnItemClickListener listener) {
        imageView.setOnClickListener(view -> listener.onAuthorClick(view.getContext(), username));
    }

}
