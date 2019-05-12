package com.example.clientforbehance.ui.comments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.data.model.comment.CommentResponse;
import com.example.clientforbehance.utils.ApiUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommentsViewModel extends ViewModel {

    private Disposable mDisposable;
    private Storage mStorage;
    private int mProjectId;

    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    private LiveData<PagedList<Comment>> mComments;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::updateComments;

    public CommentsViewModel(Storage storage, int projectId) {
        mStorage = storage;
        mProjectId = projectId;
        mComments = mStorage.getPagedComments();
        updateComments();
    }

    private void updateComments() {
        mDisposable = ApiUtils.getApiService().getProjectComments(mProjectId)
                .map(CommentResponse::getComments)
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .doOnSuccess(commentResponse -> mIsErrorVisible.postValue(false))
                .subscribeOn(Schedulers.io())
                .subscribe(response -> mStorage.insertCommentsToBaseFromList(response),
                        throwable -> mIsErrorVisible.postValue(mComments.getValue() == null || mComments.getValue().size() == 0));
    }

    @Override
    protected void onCleared() {
        mStorage = null;
        if (mDisposable != null) mDisposable.dispose();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public LiveData<PagedList<Comment>> getComments() {
        return mComments;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
}
