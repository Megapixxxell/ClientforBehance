package com.example.clientforbehance.ui.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.user.User;
import com.example.clientforbehance.data.model.user.UserResponse;
import com.example.clientforbehance.utils.ApiUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private Disposable mDisposable;
    private Storage mStorage;
    private String mUsername;

    private LiveData<User> mUser;

    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::updateUserInfo;

    public UserViewModel(Storage storage, String username) {
        mStorage = storage;
        mUsername = username;
        mUser = mStorage.getLiveUserByName(username);
        updateUserInfo();
    }

    private void updateUserInfo() {
        mDisposable = ApiUtils.getApiService().getUserInfo(mUsername)
                .map(UserResponse::getUser)
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .doOnSuccess(userResponse -> mIsErrorVisible.postValue(false))
                .subscribeOn(Schedulers.io())
                .subscribe(response -> mStorage.insertUser(response),
                        throwable -> mIsErrorVisible.postValue(mStorage.getLiveUserByName(mUsername).getValue() == null));
    }

    @Override
    protected void onCleared() {
        mStorage = null;
        if (mDisposable != null) mDisposable.dispose();
    }

    public LiveData<User> getUser() {
        return mUser;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
}
