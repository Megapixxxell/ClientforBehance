package com.example.clientforbehance.ui.projects;

import com.arellomobile.mvp.InjectViewState;
import com.example.clientforbehance.common.BasePresenter;
import com.example.domain.service.ProjectService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsView> {

    @Inject
    ProjectsView mView;
    @Inject
    ProjectService mProjectService;

    @Inject
    ProjectsPresenter() {
    }

    void getProjects(String query) {

        mCompositeDisposable.add(mProjectService.getProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(response -> mView.showProjects(response),
                        throwable -> mView.showError()));
    }

    void openUserFragment(String username) {
        mView.openUserFragment(username);
    }

    void openCommentsFragment(int projectId) {
        mView.openCommentsFragment(projectId);
    }
}
