package com.example.clientforbehance.ui.projects;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.clientforbehance.data.api.BehanceApi;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectsViewModel extends ViewModel {

    private Disposable mDisposable;
    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;
    private String mQuery;

    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableArrayList<Project> mProjects = new ObservableArrayList<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadProjects(mQuery);
        }
    };

    @Inject
    ProjectsViewModel() {
    }

    void loadProjects(String querry) {
        mDisposable = mApi.getProjects(querry)
                .doOnSuccess(projectResponse -> mStorage.insertProjectsToBaseFromResponse(projectResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getProjectResponseFromStorage() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(response -> {
                            mIsErrorVisible.set(false);
                            mQuery = querry;
                            mProjects.clear();
                            mProjects.addAll(response.getProjects());
                        },
                        throwable -> mIsErrorVisible.set(true));
    }

    void dispatchDetach() {
        mStorage = null;
        if (mDisposable != null) mDisposable.dispose();
    }

    public void setOnItemClickListener(ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public ObservableBoolean getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public ObservableArrayList<Project> getProjects() {
        return mProjects;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
}
