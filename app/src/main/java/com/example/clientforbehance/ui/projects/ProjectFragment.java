package com.example.clientforbehance.ui.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.R;
import com.example.clientforbehance.common.PresenterFragment;
import com.example.clientforbehance.common.RefreshOwner;
import com.example.clientforbehance.common.Refreshable;
import com.example.clientforbehance.dagger2.ProjectModule;
import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.ui.comments.CommentsActivity;
import com.example.clientforbehance.ui.user.UserActivity;

import java.util.List;

import javax.inject.Inject;

public class ProjectFragment extends PresenterFragment<ProjectsPresenter>
        implements ProjectsView, Refreshable, ProjectsAdapter.OnItemClickListener {

    public static final String USERNAME_KEY = "username";
    public static final String ARGS_KEY = "user args";
    public static final String PROJECT_ID = "project_id";

    private String mQuery = "";

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private ProjectsAdapter mProjectsAdapter;
    @Inject
    ProjectsPresenter mProjectsPresenter;


    public static ProjectFragment newInstance() {

        Bundle args = new Bundle();

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RefreshOwner) {
            mRefreshOwner = (RefreshOwner) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        AppDelegate.getAppComponent().createProjectComponent(new ProjectModule(this)).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_projects);
        mErrorView = view.findViewById(R.id.errorView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            getActivity().setTitle(R.string.projects);
        }

        mProjectsAdapter = new ProjectsAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mProjectsAdapter);

        onRefreshData();
    }

    @Override
    public void onDetach() {
        mRefreshOwner = null;
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        SearchView searchView;

        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                mQuery = s;
                mProjectsPresenter.getProjects(mQuery);
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onRefreshData() {
        mProjectsPresenter.getProjects(mQuery);
    }

    @Override
    protected ProjectsPresenter getPresenter() {
        return mProjectsPresenter;
    }

    @Override
    public void showProjects(List<Project> projects) {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mProjectsAdapter.addData(projects, true);
    }

    @Override
    public void openUserFragment(String username) {
        Intent userIntent = new Intent(getActivity(), UserActivity.class);
        Bundle args = new Bundle();
        args.putString(USERNAME_KEY, username);
        userIntent.putExtra(ARGS_KEY, args);
        startActivity(userIntent);
    }

    @Override
    public void openCommentsFragment(int projectId) {
        Intent commentsIntent = new Intent(getActivity(), CommentsActivity.class);
        Bundle args = new Bundle();
        args.putInt(PROJECT_ID, projectId);
        commentsIntent.putExtra(ARGS_KEY, args);
        startActivity(commentsIntent);
    }

    @Override
    public void showRefresh() {
        mRefreshOwner.setRefreshState(true);
    }

    @Override
    public void hideRefresh() {
        mRefreshOwner.setRefreshState(false);
    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onAuthorClick(String username) {
        mProjectsPresenter.openUserFragment(username);
    }

    @Override
    public void onCommentsClick(int projectId) {
        mProjectsPresenter.openCommentsFragment(projectId);
    }
}
