package com.example.clientforbehance.ui.comments;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommentsViewModel {

    private Disposable mDisposable;
    private Storage mStorage;
    private int mProjectId;

    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableArrayList<Comment> mComments = new ObservableArrayList<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadComments(mProjectId);
        }
    };

    CommentsViewModel(Storage storage) {
        mStorage = storage;
    }


    void loadComments(int projectId) {
        mDisposable = ApiUtils.getApiService().getProjectComments(projectId)
                .doOnSuccess(commentResponse -> mStorage.insertCommentsToBaseFromResponse(commentResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getCommentResponseFromStorage() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(response -> {
                            mIsErrorVisible.set(false);
                            mComments.clear();
                            mProjectId = projectId;
                            mComments.addAll(response.getComments());
                        },
                        throwable -> mIsErrorVisible.set(true));
    }

    void dispatchDetach() {
        mStorage = null;
        if (mDisposable != null) mDisposable.dispose();
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public ObservableBoolean getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public ObservableArrayList<Comment> getComments() {
        return mComments;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
}
