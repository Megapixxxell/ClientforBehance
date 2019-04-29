package com.example.clientforbehance.ui.user;

import android.view.View;

import com.example.clientforbehance.common.BasePresenter;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends BasePresenter {

    private Storage mStorage;
    private UserView mUserView;

    public UserPresenter(Storage storage, UserView userView) {
        mStorage = storage;
        mUserView = userView;
    }

    public void getUser(String username) {
        mCompositeDisposable.add(ApiUtils.getApiService().getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(userResponse -> mStorage.insertUser(userResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getUser(username) : null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mUserView.showRefresh())
                .doFinally(() -> mUserView.hideRefresh())
                .subscribe(userResponse -> mUserView.showUser(userResponse.getUser()),
                        throwable -> mUserView.showError()));
    }
}