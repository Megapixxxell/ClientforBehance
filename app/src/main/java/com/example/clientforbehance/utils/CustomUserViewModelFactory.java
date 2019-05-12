package com.example.clientforbehance.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.ui.comments.CommentsViewModel;
import com.example.clientforbehance.ui.user.UserViewModel;

public class CustomUserViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Storage mStorage;
    String mUsername;

    public CustomUserViewModelFactory(Storage storage, String username) {
        mStorage = storage;
        mUsername = username;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserViewModel(mStorage, mUsername);
    }

}
