package com.example.clientforbehance.ui.projects;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.project.ProjectResponse;
import com.example.clientforbehance.data.model.project.RichProject;
import com.example.clientforbehance.utils.ApiUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectsViewModel extends ViewModel {

    private Disposable mDisposable;
    private Storage mStorage;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;
    private String mQuery = "Android";

    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    private LiveData<PagedList<RichProject>> mProjects;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> updateProjects(mQuery);

    public ProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mStorage = storage;
        mOnItemClickListener = onItemClickListener;
        mProjects = mStorage.getPagedProjects();
        updateProjects(mQuery);
    }

    void updateProjects(String querry) {
        mDisposable = ApiUtils.getApiService().getProjects(querry)
                .map(ProjectResponse::getProjects)
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .doOnSuccess(projectResponse -> mIsErrorVisible.postValue(false))
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                            mStorage.insertProjectsToBaseFromList(response);
                            mQuery = querry;
                        },
                        throwable -> mIsErrorVisible.postValue(mProjects.getValue() == null || mProjects.getValue().size() == 0));
    }

    @Override
    protected void onCleared() {
        mStorage = null;
        if (mDisposable != null) mDisposable.dispose();
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public LiveData<PagedList<RichProject>> getProjects() {
        return mProjects;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
}
