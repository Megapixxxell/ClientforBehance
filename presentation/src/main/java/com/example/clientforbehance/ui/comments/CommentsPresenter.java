package com.example.clientforbehance.ui.comments;

import com.arellomobile.mvp.InjectViewState;
import com.example.clientforbehance.common.BasePresenter;
import com.example.domain.ApiUtils;
import com.example.data.api.BehanceApi;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CommentsPresenter extends BasePresenter<CommentsView> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    @Inject
    CommentsView mCommentsView;
    @Inject
    CommentsPresenter() {
    }

    void getComments (int projectId) {
        mCompositeDisposable.add(mApi.getProjectComments(projectId)
                .doOnSuccess(commentResponse -> mStorage.insertCommentsToBaseFromResponse(commentResponse, projectId))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getCommentResponseFromStorage(projectId) : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mCommentsView.showRefresh())
                .doFinally(() -> mCommentsView.hideRefresh())
                .subscribe(response -> mCommentsView.showComments(response.getComments()),
                        throwable -> mCommentsView.showError()));
    }
}
