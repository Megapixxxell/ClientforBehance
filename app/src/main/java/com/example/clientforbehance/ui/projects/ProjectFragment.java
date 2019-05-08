package com.example.clientforbehance.ui.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.R;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.databinding.ProjectsBinding;
import com.example.clientforbehance.ui.comments.CommentsActivity;
import com.example.clientforbehance.ui.user.UserActivity;

public class ProjectFragment extends Fragment {

    public static final String USERNAME_KEY = "username";
    public static final String ARGS_KEY = "user args";
    public static final String PROJECT_ID = "project_id";

    protected String mQuerry = "Android";

    private ProjectsViewModel mProjectsViewModel;

    private ProjectsAdapter.OnItemClickListener mOnItemClickListener = new ProjectsAdapter.OnItemClickListener() {
        @Override
        public void onAuthorClick(String username) {
            Intent userIntent = new Intent(getActivity(), UserActivity.class);
            Bundle args = new Bundle();
            args.putString(USERNAME_KEY, username);
            userIntent.putExtra(ARGS_KEY, args);
            startActivity(userIntent);
        }

        @Override
        public void onCommentsClick(int projectId) {
            Intent commentsIntent = new Intent(getActivity(), CommentsActivity.class);
            Bundle args = new Bundle();
            args.putInt(PROJECT_ID, projectId);
            commentsIntent.putExtra(ARGS_KEY, args);
            startActivity(commentsIntent);
        }
    };


    public static ProjectFragment newInstance() { return new ProjectFragment(); }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            mProjectsViewModel = new ProjectsViewModel(storage, mOnItemClickListener);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProjectsBinding binding = ProjectsBinding.inflate(inflater, container, false);
        binding.setVm(mProjectsViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            getActivity().setTitle(R.string.projects);
        }
        mProjectsViewModel.loadProjects(mQuerry);
    }

    @Override
    public void onDetach() {
        mProjectsViewModel.dispatchDetach();
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView;

        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                mQuerry = s;
                mProjectsViewModel.loadProjects(mQuerry);
                if (!searchView.isIconified()) {
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

}
