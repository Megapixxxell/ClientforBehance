package com.example.clientforbehance.ui.projects;

import android.view.View;

import com.example.clientforbehance.common.BasePresenter;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProjectsPresenter extends BasePresenter {

    private ProjectsView mView;
    private Storage mStorage;

    public ProjectsPresenter(ProjectsView view, Storage storage) {
        mView = view;
        mStorage = storage;
    }

    public void getProjects(String query) {

        mCompositeDisposable.add(ApiUtils.getApiService().getProjects(query)
                .doOnSuccess(projectResponse -> mStorage.insertProjectsToBaseFromResponse(projectResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getProjectResponseFromStorage() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(response -> {
                            mView.showProjects(response.getProjects());
                        },
                        throwable -> {
                            mView.showError();
                        }));
    }

    public void openUserFragment(String username) {
        mView.openUserFragment(username);
    }
    public void openCommentsFragment(int projectId){
        mView.openCommentsFragment(projectId);
    }
}
