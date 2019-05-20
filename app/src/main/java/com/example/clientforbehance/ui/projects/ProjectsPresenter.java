package com.example.clientforbehance.ui.projects;

import com.example.clientforbehance.common.BasePresenter;
import com.example.clientforbehance.data.api.BehanceApi;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProjectsPresenter extends BasePresenter {

    @Inject
    ProjectsView mView;
    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;

    @Inject
    ProjectsPresenter() {
    }

    void getProjects(String query) {

        mCompositeDisposable.add(mApi.getProjects(query)
                .doOnSuccess(projectResponse -> mStorage.insertProjectsToBaseFromResponse(projectResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getProjectResponseFromStorage() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(response -> mView.showProjects(response.getProjects()),
                        throwable -> mView.showError()));
    }

    void openUserFragment(String username) {
        mView.openUserFragment(username);
    }

    void openCommentsFragment(int projectId) {
        mView.openCommentsFragment(projectId);
    }
}
