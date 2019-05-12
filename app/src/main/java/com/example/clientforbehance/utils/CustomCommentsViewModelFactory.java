package com.example.clientforbehance.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.ui.comments.CommentsViewModel;

public class CustomCommentsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Storage mStorage;
    private int mProjectId;

    public CustomCommentsViewModelFactory(Storage storage, int projectId) {
        mStorage = storage;
        mProjectId = projectId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CommentsViewModel(mStorage, mProjectId);
    }
}
