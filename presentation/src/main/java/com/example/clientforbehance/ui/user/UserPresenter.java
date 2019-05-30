package com.example.clientforbehance.ui.user;

import com.arellomobile.mvp.InjectViewState;
import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.common.BasePresenter;
import com.example.domain.service.UserService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class UserPresenter extends BasePresenter<UserView> {

    private UserService mUserService = AppDelegate.getAppComponent().getUserService();

    UserPresenter() {
    }

    void getUser(String username) {
        mCompositeDisposable.add(mUserService.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() -> getViewState().hideRefresh())
                .subscribe(userResponse -> getViewState().showUser(userResponse),
                        throwable -> getViewState().showError()));
    }
}