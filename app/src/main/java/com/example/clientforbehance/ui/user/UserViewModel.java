package com.example.clientforbehance.ui.user;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.clientforbehance.data.api.BehanceApi;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.user.User;
import com.example.clientforbehance.utils.ApiUtils;
import com.example.clientforbehance.utils.DateUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel {

    private Disposable mDisposable;
    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    private String mUsernameStr;

    private ObservableField<String> mUserImageUrl = new ObservableField<>();
    private ObservableField<String> mUsername = new ObservableField<>();
    private ObservableField<String> mUserCreatedOn = new ObservableField<>();
    private ObservableField<String> mUserLocation = new ObservableField<>();
    private ObservableField<String> mName = new ObservableField<>();

    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadUser(mUsernameStr);
        }
    };

    UserViewModel() {
    }

    void loadUser(String usernameStr) {
        mDisposable = mApi.getUserInfo(usernameStr)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(userResponse -> mStorage.insertUser(userResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getUser(mUsernameStr) : null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(userResponse -> {
                    mIsErrorVisible.set(false);
                    mUsernameStr = usernameStr;
                    getUserInfo(userResponse.getUser());
                }, throwable -> mIsErrorVisible.set(true));
    }

    private void getUserInfo(User user) {
        mUserImageUrl.set(user.getImage().getPhotoUrl());
        mUsername.set(user.getUsername());
        mUserCreatedOn.set(DateUtils.format(user.getCreatedOn()));
        mUserLocation.set(user.getLocation());
        mName.set(user.getDisplayName());
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

    public ObservableField<String> getUserImageUrl() {
        return mUserImageUrl;
    }

    public ObservableField<String> getUsername() {
        return mUsername;
    }

    public ObservableField<String> getUserCreatedOn() {
        return mUserCreatedOn;
    }

    public ObservableField<String> getUserLocation() {
        return mUserLocation;
    }

    public ObservableField<String> getName() {
        return mName;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
}
