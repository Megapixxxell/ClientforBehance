package com.example.clientforbehance.ui.projects;

import com.arellomobile.mvp.InjectViewState;
import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.common.BasePresenter;
import com.example.domain.service.ProjectService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsView> {

    private ProjectService mProjectService = AppDelegate.getAppComponent().getProjectService();

    ProjectsPresenter() {
    }

    void getProjects(String query) {
        mCompositeDisposable.add(mProjectService.getProjects(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() -> getViewState().hideRefresh())
                .subscribe(response -> getViewState().showProjects(response),
                        throwable -> getViewState().showError()));
    }

    void openUserFragment(String username) {
        getViewState().openUserFragment(username);
    }

    void openCommentsFragment(int projectId) {
        getViewState().openCommentsFragment(projectId);
    }
}
