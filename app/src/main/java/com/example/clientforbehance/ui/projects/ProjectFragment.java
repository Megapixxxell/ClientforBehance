package com.example.clientforbehance.ui.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.BuildConfig;
import com.example.clientforbehance.R;
import com.example.clientforbehance.common.RefreshOwner;
import com.example.clientforbehance.common.Refreshable;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.ui.comments.CommentsActivity;
import com.example.clientforbehance.ui.user.UserActivity;
import com.example.clientforbehance.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectFragment extends Fragment implements Refreshable, ProjectsAdapter.OnItemClickListener {

    public static final String USERNAME_KEY = "username";
    public static final String ARGS_KEY = "user args";
    public static final String PROJECT_ID = "project_id";

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private Storage mStorage;
    private ProjectsAdapter mProjectsAdapter;
    private Disposable mDisposable;


    public static ProjectFragment newInstance() {

        Bundle args = new Bundle();

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            mStorage = ((Storage.StorageOwner) context).obtainStorage();
        }

        if (context instanceof RefreshOwner) {
            mRefreshOwner = (RefreshOwner) context;
        }
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
        mStorage = null;
        mRefreshOwner = null;
        if (mDisposable != null) mDisposable.dispose();
        super.onDetach();
    }

    @Override
    public void onRefreshData() {
        getProjects();
    }

    private void getProjects() {
        mDisposable = ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
                .doOnSuccess(projectResponse -> mStorage.insertProjectsToBaseFromResponse(projectResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getProjectResponseFromStorage() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mRefreshOwner.setRefreshState(false))
                .subscribe(response -> {
                            mErrorView.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                            mProjectsAdapter.addData(response.getProjects(), true);
                        },
                        throwable -> {
                            mErrorView.setVisibility(View.VISIBLE);
                            mRecyclerView.setVisibility(View.GONE);
                        });
    }


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
}
