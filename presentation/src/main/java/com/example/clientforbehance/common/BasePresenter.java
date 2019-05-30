package com.example.clientforbehance.common;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends BaseView> extends MvpPresenter<V> {

    public BasePresenter() {
    }
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void disposeAll () {
        mCompositeDisposable.clear();
    }
}
