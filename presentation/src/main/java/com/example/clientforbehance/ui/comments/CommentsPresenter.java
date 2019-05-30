package com.example.clientforbehance.ui.comments;

import com.arellomobile.mvp.InjectViewState;
import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.common.BasePresenter;
import com.example.domain.service.CommentService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CommentsPresenter extends BasePresenter<CommentsView> {

    private CommentService mCommentService = AppDelegate.getAppComponent().getCommentService();

    CommentsPresenter() {
    }

    void getComments(int projectId) {
        mCompositeDisposable.add(mCommentService.getComments(projectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() -> getViewState().hideRefresh())
                .subscribe(response -> getViewState().showComments(response),
                        throwable -> getViewState().showError()));
    }
}
