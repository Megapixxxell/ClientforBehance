package com.example.clientforbehance.ui.comments;

import com.arellomobile.mvp.InjectViewState;
import com.example.clientforbehance.common.BasePresenter;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CommentsPresenter extends BasePresenter<CommentsView> {

    private Storage mStorage;

    public CommentsPresenter(Storage storage) {
        mStorage = storage;
    }

    void getComments (int projectId) {
        mCompositeDisposable.add(ApiUtils.getApiService().getProjectComments(projectId)
                .doOnSuccess(commentResponse -> mStorage.insertCommentsToBaseFromResponse(commentResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getCommentResponseFromStorage() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() -> getViewState().hideRefresh())
                .subscribe(response -> getViewState().showComments(response.getComments()),
                        throwable -> getViewState().showError()));
    }
}
