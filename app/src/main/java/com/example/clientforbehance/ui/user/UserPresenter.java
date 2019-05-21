package com.example.clientforbehance.ui.user;

import com.arellomobile.mvp.InjectViewState;
import com.example.clientforbehance.common.BasePresenter;
import com.example.clientforbehance.data.api.BehanceApi;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class UserPresenter extends BasePresenter<UserView> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    @Inject
    UserView mUserView;

    @Inject
    UserPresenter() {
    }

    void getUser(String username) {
        mCompositeDisposable.add(mApi.getUserInfo(username)
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