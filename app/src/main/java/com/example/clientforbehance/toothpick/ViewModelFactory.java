package com.example.clientforbehance.toothpick;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import toothpick.Toothpick;

@Singleton
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application mApplication;

    @Inject
    public ViewModelFactory (Application app) {
        mApplication = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) Toothpick.openScope(mApplication).getInstance(modelClass);
    }
}
